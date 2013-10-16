package org.theglicks.bukkit.fuedalism.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.theglicks.bukkit.fuedalism.Messages;
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
			if(f.getOwnerName().equals(v.getName())){
				f.delete();
				p.sendMessage(Messages.getMessage("fiefAbandoned", null));
			} else {
				p.sendMessage(Messages.getMessage("notOwnerFief", null));
			}
		} else {
			p.sendMessage(Messages.getMessage("noFiefAtLocation", null));
		}
	}
}
