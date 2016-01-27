package com.caved_in.grapplinghook;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

public class PlayerGrappleEvent extends Event implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private Player player;
    private Entity entity;
    private Location pullLocation;
    private ItemStack hookItem;
    private boolean cancelled = false;
    private boolean unhookAfter = true;

    public PlayerGrappleEvent(Player p, Entity e, Location l) {
        player = p;
        entity = e;
        pullLocation = l;
        hookItem = p.getItemInHand();
    }

    public Player getPlayer() {
        return player;
    }

    public Entity getPulledEntity() {
        return entity;
    }

    public Location getPullLocation() {
        return pullLocation;
    }

    public ItemStack getHookItem() {
        return hookItem;
    }

//	    public String getMessage(){
//	    	return message;
//	    }
//	    
//	    public void setMessage(String s){
//	    	message = s;
//	    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean set) {
        cancelled = set;
    }

    public void setUnhookAfterPull(boolean unhook) {
        this.unhookAfter = unhook;
    }

    public boolean unhookAfterPull() {
        return unhookAfter;
    }
}
