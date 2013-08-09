package org.theglicks.bukkit.fuedalism.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.theglicks.bukkit.fuedalism.Vassal;
import org.theglicks.bukkit.fuedalism.landManagement.Fief;

public class FiefAbandon {
	public static void execute(CommandSender sender, String[] args){
		//Make sure command is being executed by a player
		if (!(sender instanceof Player)) return;
		
		Vassal v = new Vassal(sender.getName());
		Player p = (Player) sender;
		Fief f = new Fief(p.getLocation());
		
		if(f.exists()){
			if(f.getOwner() == v){
				f.delete();
				p.sendMessage("Fief abandoned!");
			} else {
				p.sendMessage("You do not own this fief!");
			}
		} else {
			p.sendMessage("Fief does not exist at your location!");
		}
	}
}
