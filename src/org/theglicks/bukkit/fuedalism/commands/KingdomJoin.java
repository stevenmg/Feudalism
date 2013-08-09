package org.theglicks.bukkit.fuedalism.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.theglicks.bukkit.fuedalism.Kingdom;
import org.theglicks.bukkit.fuedalism.Vassal;

public class KingdomJoin {
	public static void execute(CommandSender sender, String[] args){
		//Make sure command is being executed by a player
		if (!(sender instanceof Player)) return;
		
		Vassal v = new Vassal(sender.getName());
		
		//Makes sure the player isn't already in a kingdom
		if (v.hasKingdom()) return;
		
		if(args.length == 2){
			Kingdom k = new Kingdom(args[1]);
			if(v.hasInvite(k))
				v.setKingdom(k);
				v.setLeader(false);
		}
	}
}
