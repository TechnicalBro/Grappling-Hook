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
			.item();
	
	public static boolean isGrapplingHook(ItemStack is) {
		return is.isSimilar(GRAPPLING_HOOK_ITEM);
	}
	
	public static ItemStack createGrapplingHook() {
		return GRAPPLING_HOOK_ITEM.clone();
	}
	
	public static void playGrappleSound(Location loc) {
		loc.getWorld().playSound(loc, Sound.MAGMACUBE_JUMP, 10f, 1f);
	}
}
