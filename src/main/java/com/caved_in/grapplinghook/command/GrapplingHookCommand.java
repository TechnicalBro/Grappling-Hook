package com.caved_in.grapplinghook.command;

import com.caved_in.commons.Messages;
import com.caved_in.commons.command.Command;
import com.caved_in.commons.command.SubCommand;
import com.caved_in.commons.player.Players;
import com.caved_in.grapplinghook.api.HookAPI;
import org.bukkit.entity.Player;

public class GrapplingHookCommand {
	@Command(name = "gh", permission = "grapplinghook.command.give")
	public void onGrapplingHookCommand(Player player, String[] args) {
		if (args.length == 0) {
			Players.giveItem(player, HookAPI.createGrapplingHook());
		}
	}

	@SubCommand(name = "give", parent = "gh")
	public void onGrapplingHookGiveCommand(Player player, String[] args) {
		if (args.length < 2) {
			Players.sendMessage(player, Messages.invalidCommandUsage("player name"));
		}

		String playerName = args[1];

		if (!Players.isOnline(playerName)) {
			Players.sendMessage(player,Messages.playerOffline(playerName));
		}

		Player givePlayer = Players.getPlayer(playerName);
		Players.giveItem(givePlayer,HookAPI.createGrapplingHook());
	}
}
