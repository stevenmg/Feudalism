package org.theglicks.bukkit.fuedalism.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.theglicks.bukkit.fuedalism.Kingdom;
import org.theglicks.bukkit.fuedalism.Messages;
import org.theglicks.bukkit.fuedalism.Vassal;

public class KingdomInvite {
	public static void execute(CommandSender sender, String[] args){
		//Make sure command is being executed by a player
		if (!(sender instanceof Player)) return;
		
		if(!(args.length == 2)){
			sender.sendMessage(Messages.getMessage("incorrectCmdUsage", null));
			return;
		}
		
		Vassal v = new Vassal(sender.getName());
		
		//Makes sure the player is in a kingdom
		if(!v.hasKingdom()){
			sender.sendMessage(Messages.getMessage("notInAnyKingdom", null));
			return;
		}
		
		//Makes sure the player is a leader in his kingdom
		if(!v.isLeader()){
			sender.sendMessage(Messages.getMessage("mustBeLeader", null));
			return;
		}
		
		Vassal vassal = new Vassal(args[1]);
		Kingdom k = v.getKingdom();
		k.invite(vassal);
		sender.sendMessage(Messages.getMessage("playerInvited", vassal.getName()));
	}
}
