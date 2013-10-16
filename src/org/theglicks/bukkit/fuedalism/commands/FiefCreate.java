package org.theglicks.bukkit.fuedalism.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.theglicks.bukkit.fuedalism.Fuedalism;
import org.theglicks.bukkit.fuedalism.Messages;
import org.theglicks.bukkit.fuedalism.landManagement.SelectionManager;

public class FiefCreate {
	public static void execute(CommandSender sender, String[] args){
		//Make sure command is being executed by a player
		if (!(sender instanceof Player)) return;
		
		Player p = (Player) sender;
		
		if(SelectionManager.canCreateClaim(p)){
			int cost = Fuedalism.mainConfig.getConfig().getInt("Economy.costPerBlockFief") * SelectionManager.getSelection(p).getSize();
			if(Fuedalism.econ.has(p.getName(), p.getWorld().getName(), cost)){
				Fuedalism.econ.withdrawPlayer(p.getName(), p.getWorld().getName(), cost);
				SelectionManager.getFief(p);
				p.sendMessage(Messages.getMessage("fiefCreated", null));
			} else {
				p.sendMessage(Messages.getMessage("notEnoughMoney", ((Integer) cost).toString()));
			}
		} else {
			p.sendMessage(Messages.getMessage("selectLand", null));
		}
	}
}
