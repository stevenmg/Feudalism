package org.theglicks.bukkit.governance.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.theglicks.bukkit.governance.Governance;
import org.theglicks.bukkit.governance.Messages;
import org.theglicks.bukkit.governance.landManagement.SelectionManager;

public class FiefCreate {
	public static void execute(CommandSender sender, String[] args){
		//Make sure command is being executed by a player
		if (!(sender instanceof Player)) return;
		
		Player p = (Player) sender;
		
		if(SelectionManager.canCreateClaim(p)){
			int cost = Governance.mainConfig.getConfig().getInt("Economy.costPerBlockFief") * SelectionManager.getSelection(p).getSize();
			if(Governance.econ.has(p.getName(), p.getWorld().getName(), cost)){
				Governance.econ.withdrawPlayer(p.getName(), p.getWorld().getName(), cost);
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
