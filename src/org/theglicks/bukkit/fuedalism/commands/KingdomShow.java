package org.theglicks.bukkit.fuedalism.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.theglicks.bukkit.fuedalism.Kingdom;
import org.theglicks.bukkit.fuedalism.Vassal;

public class KingdomShow {
	public static void execute(CommandSender sender, String[] args){
		//Make sure command is being executed by a player
		if (!(sender instanceof Player)) return;
		
		Vassal v = new Vassal(sender.getName());
		Player p = (Player) sender;
		Kingdom k;
		if(args.length == 2)
			k = new Kingdom(args[1]);	
		else
			k = v.getKingdom();
		
		StringBuilder members = new StringBuilder();
		for(String name: k.getMembers()){
			if(!name.equals(k.getOwner().getName()))
				members.append(name);
		}
		p.sendMessage("Name: " + k.getName());
		p.sendMessage("Owner: " + k.getOwner().getName());
		p.sendMessage("Members: " + members.toString());
	}
}
