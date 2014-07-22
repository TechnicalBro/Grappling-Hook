package com.caved_in.grapplinghook.api;


import com.caved_in.commons.item.Items;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.inventory.ItemStack;

public final class HookAPI {
	
	public static boolean isGrapplingHook(ItemStack is) {
		return Items.isType(is,Material.FISHING_ROD) && Items.nameContains(is,"Grappling Hook");
	}
	
	public static ItemStack createGrapplingHook() {
		return Items.makeItem(Material.FISHING_ROD,ChatColor.GOLD + "Grappling Hook");
	}
	
	public static void playGrappleSound(Location loc) {
		loc.getWorld().playSound(loc, Sound.MAGMACUBE_JUMP, 10f, 1f);
	}
}
