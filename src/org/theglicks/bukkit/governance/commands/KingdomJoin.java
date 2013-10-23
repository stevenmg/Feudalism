package org.theglicks.bukkit.governance.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.theglicks.bukkit.governance.Kingdom;
import org.theglicks.bukkit.governance.Messages;
import org.theglicks.bukkit.governance.Vassal;

public class KingdomJoin {
	public static void execute(CommandSender sender, String[] args){
		//Make sure command is being executed by a player
		if (!(sender instanceof Player)) return;
		
		if(!(args.length == 2)){
			sender.sendMessage(Messages.getMessage("incorrectCmdUsage", null));
			return;
		}
		
		Vassal v = new Vassal(sender.getName());
		
		//Makes sure the player isn't already in a kingdom
		if (v.hasKingdom()){
			sender.sendMessage(Messages.getMessage("alreadyInKingdom", null));
			return;
		}
		
		Kingdom k = new Kingdom(args[1]);
		if(v.hasInvite(k)){
			v.setKingdom(k);
			v.setLeader(false);
			sender.sendMessage(Messages.getMessage("kingdomJoin", k.getName()));
		} else {
			sender.sendMessage(Messages.getMessage("noInvite", k.getName()));
		}
	}
}
