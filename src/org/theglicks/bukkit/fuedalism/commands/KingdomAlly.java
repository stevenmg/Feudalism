package org.theglicks.bukkit.fuedalism.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.theglicks.bukkit.fuedalism.Kingdom;
import org.theglicks.bukkit.fuedalism.Vassal;

public class KingdomAlly {
	public static void execute(CommandSender sender, String[] args){
		//Make sure command is being executed by a player
		if (!(sender instanceof Player)) return;
		
		Vassal v = new Vassal(sender.getName());
		Player p = (Player) sender;
		
		if(args.length == 2){
			Kingdom kSender = v.getKingdom();
			Kingdom kReceiver = new Kingdom(args[1]);
			if(kSender.hasAllyRequest(kReceiver) && !(kSender.getRelation(kReceiver) == 1)){
				kSender.setRelation(kReceiver, 1);
			} else if(!kReceiver.hasAllyRequest(kSender)){
				kSender.sendAllyRequest(kReceiver);
			}	
		} else {
			p.sendMessage("Look up the correct command usage!");
		}
	}
}
