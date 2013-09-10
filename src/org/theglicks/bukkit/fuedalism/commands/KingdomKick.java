package org.theglicks.bukkit.fuedalism.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.theglicks.bukkit.fuedalism.Vassal;

public class KingdomKick {
	public static void execute(CommandSender sender, String[] args){
		//Make sure command is being executed by a player
		if (!(sender instanceof Player)) return;
	
		Vassal v = new Vassal(args[1]);
		Vassal v1 = new Vassal(sender.getName());
		
		//Makes sure command sender is in the right kingdom
		if(!(v.getKingdom().getName().equals(v1.getKingdom().getName()))) return;
		
		if(!v1.isLeader()) return;
		
		v.removeKingdom();
	}
}
