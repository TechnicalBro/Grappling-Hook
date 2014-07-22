package com.caved_in.grapplinghook;

import com.caved_in.commons.entity.Entities;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class PluginMessages {

	public static String hookedPlayer(Player hooked) {
		return String.format("&6You've hooked &r%s&e!",hooked.getName());
	}

	public static String hookedByPlayer(Player hooker) {
		return String.format("&eYou've been hooked by &r%s&e!",hooker.getName());
	}

	public static String hookedEntity(Entity entity) {
		return String.format("&eYou've hooked a &r%s&e!", Entities.getDefaultName(entity.getType()));
	}
}
