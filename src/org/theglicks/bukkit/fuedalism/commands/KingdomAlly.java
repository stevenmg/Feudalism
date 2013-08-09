package org.theglicks.bukkit.fuedalism.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.theglicks.bukkit.fuedalism.Vassal;
import org.theglicks.bukkit.fuedalism.kingdoms.Kingdom;
import org.theglicks.bukkit.fuedalism.kingdoms.RelationManager;

public class KingdomAlly {
	public static void execute(CommandSender sender, String[] args){
		//Make sure command is being executed by a player
		if (!(sender instanceof Player)) return;
		
		Vassal v = new Vassal(sender.getName());
		Player p = (Player) sender;
		
		if(args.length == 2){
			Kingdom kSender = v.getKingdom();
			Kingdom kReceiver = new Kingdom(args[1]);
			if(RelationManager.hasRequest(kReceiver, kSender) && !RelationManager.hasAlliance(kSender, kReceiver)){
				RelationManager.addAlliance(kSender, kReceiver);
			} else if(!RelationManager.hasRequest(kSender, kReceiver)){
				RelationManager.addRequest(kSender, kReceiver);
			}	
		} else {
			p.sendMessage("Look up the correct command usage!");
		}
	}
}
