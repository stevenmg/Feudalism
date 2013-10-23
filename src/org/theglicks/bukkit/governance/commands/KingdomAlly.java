package org.theglicks.bukkit.governance.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.theglicks.bukkit.governance.Kingdom;
import org.theglicks.bukkit.governance.Messages;
import org.theglicks.bukkit.governance.Vassal;

public class KingdomAlly {
	public static void execute(CommandSender sender, String[] args){
		//Make sure command is being executed by a player
		if (!(sender instanceof Player)) return;
		
		Vassal v = new Vassal(sender.getName());
		
		//Makes sure player has specified a kingdom
		if(!(args.length == 2)){
			sender.sendMessage(Messages.getMessage("incorrectCmdUsage", null));
			return;
		}
		
		//Makes sure player is a kingdom leader
		if(!v.isLeader()){
			sender.sendMessage(Messages.getMessage("mustBeLeader", null));
			return;
		}
		
		Kingdom kSender = v.getKingdom();
		Kingdom kReceiver = new Kingdom(args[1]);
		
		//Makes sure the kingdoms aren't already allied
		if(kSender.getRelation(kReceiver) == 1){
			sender.sendMessage(Messages.getMessage("alreadyAllied", kReceiver.getName()));
			return;
		}
		
		if(kSender.hasAllyRequest(kReceiver))
			kSender.setRelation(kReceiver, 1);
		else if(!kReceiver.hasAllyRequest(kSender))
			kSender.sendAllyRequest(kReceiver);
		
		sender.sendMessage(Messages.getMessage("allyRequestSent", kReceiver.getName()));
	}
}
