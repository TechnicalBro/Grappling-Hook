package com.caved_in.grapplinghook.api;


import com.caved_in.commons.item.ItemBuilder;
import com.caved_in.commons.item.Items;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.inventory.ItemStack;

public final class HookAPI {

    private static ItemStack GRAPPLING_HOOK_ITEM = ItemBuilder.of(Material.FISHING_ROD)
            .name("&6Grappling Hook")
            .lore("Right click, launch, then pull")
            .unbreakable()
            .item();

    public static boolean isGrapplingHook(ItemStack is) {
        if (is.getType() != GRAPPLING_HOOK_ITEM.getType()) {
            return false;
        }

        if (!Items.loreContains(is, "Right click, launch, then pull")) {
            return false;
        }

        return true;
    }

    public static ItemStack createGrapplingHook() {
        return GRAPPLING_HOOK_ITEM.clone();
    }

    public static void playGrappleSound(Location loc) {
        loc.getWorld().playSound(loc, Sound.MAGMACUBE_JUMP, 10f, 1f);
    }
}
