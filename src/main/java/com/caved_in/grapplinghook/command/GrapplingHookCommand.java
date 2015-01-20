package com.caved_in.grapplinghook.command;

import com.caved_in.commons.Messages;
import com.caved_in.commons.command.Arg;
import com.caved_in.commons.command.Command;
import com.caved_in.commons.player.Players;
import com.caved_in.grapplinghook.api.HookAPI;
import org.bukkit.entity.Player;

public class GrapplingHookCommand {
	@Command(identifier = "gh", permissions = "grapplinghook.command.give")
	public void onGrapplingHookCommand(Player player) {
		Players.giveItem(player, HookAPI.createGrapplingHook());
	}

	@Command(identifier = "gh get",permissions = "grapplinghook.command.get")
	public void onGrapplingHookGetCommand(Player p) {
		Players.giveItem(p,HookAPI.createGrapplingHook());
	}

	@Command(identifier = "gh give")
	public void onGrapplingHookGiveCommand(Player player, @Arg(name = "player", def = "?sender")Player target) {
		Players.giveItem(target,HookAPI.createGrapplingHook());
	}
}
