package org.theglicks.bukkit.fuedalism.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.theglicks.bukkit.fuedalism.Vassal;
import org.theglicks.bukkit.fuedalism.kingdoms.Kingdom;
import org.theglicks.bukkit.fuedalism.kingdoms.RelationManager;

public class KingdomEnemy {
	public static void execute(CommandSender sender, String[] args){
		//Make sure command is being executed by a player
		if (!(sender instanceof Player)) return;
		
		Vassal v = new Vassal(sender.getName());
		
		if(args.length == 2){
			Kingdom k1 = v.getKingdom();
			Kingdom k2 = new Kingdom(args[1]);
			RelationManager.addEnemy(k1, k2);
		}
	}
}
