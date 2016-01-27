package com.caved_in.grapplinghook;

import com.caved_in.commons.Messages;
import com.caved_in.commons.block.Blocks;
import com.caved_in.commons.chat.Chat;
import com.caved_in.commons.entity.Entities;
import com.caved_in.commons.player.Players;
import com.caved_in.commons.plugin.Plugins;
import com.caved_in.commons.time.Cooldown;
import com.caved_in.grapplinghook.config.PluginConfig;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Fish;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import com.caved_in.grapplinghook.api.HookAPI;


public class GrapplingListener implements Listener {

    private Cooldown grappleCooldown = new Cooldown(1);

    @EventHandler
    public void onAttack(EntityDamageByEntityEvent event) {

        if (event.getDamager() instanceof Fish) {
            Fish hook = (Fish) event.getDamager();
            if (!(hook.getShooter() instanceof Player)) {
                return;
            }
            Player player = (Player) hook.getShooter();

            if (!HookAPI.isGrapplingHook(player.getItemInHand())) {
                return;
            }

            if (event.getEntity() instanceof Player) {
                Player hooked = (Player) event.getEntity();
                if (hooked.hasPermission("grapplinghook.player.nopull")) {
                    event.setCancelled(true);
                } else {
                    Chat.message(hooked, PluginMessages.hookedByPlayer(player));
                    Chat.message(player, PluginMessages.hookedPlayer(hooked));
                }
            } else {
                Chat.message(player, PluginMessages.hookedEntity(event.getEntity()));
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEntityDamageEvent(EntityDamageEvent event) {
        if (event.getCause() == DamageCause.FALL) {
            event.setCancelled(true);
        }
    }

    @EventHandler()
    public void onGrapple(PlayerGrappleEvent event) {
        if (event.isCancelled()) {
            return;
        }

        final Player player = event.getPlayer();

        if (grappleCooldown.isOnCooldown(player)) {
            return;
        }

//		event.getHookItem().setDurability((short) -10);

        Entity e = event.getPulledEntity();
        Location loc = event.getPullLocation();

        PluginConfig pluginConfig = GrapplingHook.getPluginConfig();

        if (player.equals(e)) { //the player is pulling themself to a location
            if (pluginConfig.isTeleportHooked()) {
                loc.setPitch(player.getLocation().getPitch());
                loc.setYaw(player.getLocation().getYaw());
                player.teleport(loc);
            } else {
                if (player.getLocation().distance(loc) < 3) //hook is too close to player
                {
                    pullPlayerSlightly(player, loc);
                } else {
                    Entities.pullEntityToLocation(player, loc);
                }
            }
        } else { //the player is pulling an entity to them
            if (pluginConfig.isTeleportHooked()) {
                e.teleport(loc);
            } else {
                Entities.pullEntityToLocation(e, loc);
                if (e instanceof Item) { //TODO Teleport the item to the player.
                    ItemStack is = ((Item) e).getItemStack();
                    String itemName = is.getType().toString().replace("_", " ").toLowerCase();
                    Chat.message(player, "&6You have hooked a stack of " + is.getAmount() + " " + itemName + "!");
                }
            }
        }

        HookAPI.playGrappleSound(player.getLocation());
        grappleCooldown.setOnCooldown(player);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void fishEvent(PlayerFishEvent event) //called before projectileLaunchEvent
    {
        Player player = event.getPlayer();

        if (!HookAPI.isGrapplingHook(player.getItemInHand())) {
            return;
        }

        PlayerFishEvent.State state = event.getState();

        switch (state) {
            case CAUGHT_FISH:
                event.setCancelled(true);
                break;
//			TODO Enable grappling players.
            case CAUGHT_ENTITY:
                event.setCancelled(true);
                PlayerGrappleEvent playerGrappleEntityEvent = new PlayerGrappleEvent(player, event.getCaught(), player.getLocation());
                Plugins.callEvent(playerGrappleEntityEvent);

                if (playerGrappleEntityEvent.unhookAfterPull()) {
                    event.getHook().eject();
                }
                break;
            case FAILED_ATTEMPT:
                Location hookLocation = event.getHook().getLocation();
                /*
                On a failed attempt, we want to check if the location of the hook
                is a solid block
                 */
                if (!Blocks.isSolid(hookLocation)) {
                    Chat.actionMessage(player, "&7Your grapple didn't hook anything");
                    break;
                }
            case IN_GROUND:
                Location groundLoc = event.getHook().getLocation();
                for (Entity ent : event.getHook().getNearbyEntities(1.5, 1, 1.5)) {
                    if (ent instanceof Item) {
                        PlayerGrappleEvent grappleItemEvent = new PlayerGrappleEvent(player, ent, player.getLocation());
                        Plugins.callEvent(grappleItemEvent);
                        return;
                    }
                }

                PlayerGrappleEvent playerGrappleLocEvent = new PlayerGrappleEvent(player, player, groundLoc);
                Plugins.callEvent(playerGrappleLocEvent);

                if (playerGrappleLocEvent.unhookAfterPull()) {
                    event.getHook().eject();
                }
                break;
            default:
                break;
        }
    }


    //For pulling a player slightly
    private void pullPlayerSlightly(Player p, Location loc) {
        if (loc.getY() > p.getLocation().getY()) {
            p.setVelocity(new Vector(0, 0.25, 0));
            return;
        }

        Location playerLoc = p.getLocation();

        Vector vector = loc.toVector().subtract(playerLoc.toVector());
        p.setVelocity(vector);
    }
}