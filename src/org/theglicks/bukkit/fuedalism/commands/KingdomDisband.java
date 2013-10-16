package org.theglicks.bukkit.fuedalism.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.theglicks.bukkit.fuedalism.Messages;
import org.theglicks.bukkit.fuedalism.Vassal;

public class KingdomDisband {
	public static void execute(CommandSender sender, String[] args){
		//Make sure command is being executed by a player
		if (!(sender instanceof Player)) return;
		
		Vassal v = new Vassal(sender.getName());
		
		//Makes sure the player is in a kingdom
		if(!v.hasKingdom()){
			sender.sendMessage(Messages.getMessage("notInAnyKingdom", null));
			return;
		}
		
		//Makes sure the player is the owner of their kingdom
		if(!(v.getKingdom().getOwner().equals(v.getPlayer().getName()))){
			sender.sendMessage(Messages.getMessage("mustBeKOwner", null));
			return;
		}
		
		//Deletes the kingdom
		v.getKingdom().delete();
		//v.setKingdom(null);
		v.setLeader(false);
		
		sender.sendMessage(Messages.getMessage("kingdomDisbanded", null));
	}
}
