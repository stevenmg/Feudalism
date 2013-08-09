package org.theglicks.bukkit.fuedalism.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.theglicks.bukkit.fuedalism.Vassal;
import org.theglicks.bukkit.fuedalism.kingdoms.Kingdom;

public class KingdomInvite {
	public static void execute(CommandSender sender, String[] args){
		//Make sure command is being executed by a player
		if (!(sender instanceof Player)) return;
		
		Vassal v = new Vassal(sender.getName());
		
		if(args.length == 2){
			if(v.isLeader()){
				Vassal vassal = new Vassal(args[1]);
				Kingdom k = v.getKingdom();
				k.invite(vassal);
			}
		}
	}
}
