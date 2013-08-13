package org.theglicks.bukkit.fuedalism.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.theglicks.bukkit.fuedalism.Vassal;

public class KingdomDisband {
	public static void execute(CommandSender sender, String[] args){
		//Make sure command is being executed by a player
		if (!(sender instanceof Player)) return;
		
		Vassal v = new Vassal(sender.getName());
		
		//Makes sure the player is in a kingdom
		if(!v.hasKingdom()) return;
		
		//Makes sure the player is the leader of their kingdom
		if(!(v.getKingdom().getOwner() == v.getPlayer())) return;
		
		//Deletes the kingdom
		v.getKingdom().delete();
		v.setKingdom(null);
		v.setLeader(false);
	}
}
