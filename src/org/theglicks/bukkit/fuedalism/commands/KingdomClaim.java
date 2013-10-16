package org.theglicks.bukkit.fuedalism.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.theglicks.bukkit.fuedalism.Fuedalism;
import org.theglicks.bukkit.fuedalism.Messages;
import org.theglicks.bukkit.fuedalism.Vassal;
import org.theglicks.bukkit.fuedalism.landManagement.SelectionManager;

public class KingdomClaim {
	public static void execute(CommandSender sender, String[] args){
		//Make sure command is being executed by a player
		if (!(sender instanceof Player)) return;
		
		Vassal v = new Vassal(sender.getName());
		Player p = (Player) sender;
		
		//Makes sure player is a leader in the kingdom
		if (!v.isLeader()) {
			p.sendMessage(Messages.getMessage("mustBeLeader", null));
			return;
		}
		
		if (SelectionManager.canCreateClaim(p)) {
			int cost = Fuedalism.mainConfig.getConfig().getInt("Economy.costPerBlockKingdom") * SelectionManager.getSelection(p).getSize();
			if(Fuedalism.econ.has(p.getName(), p.getWorld().getName(), cost)){
				Fuedalism.econ.withdrawPlayer(p.getName(), p.getWorld().getName(), cost);
				SelectionManager.getKingdomClaim(p);
				p.sendMessage(Messages.getMessage("kClaimCreated", null));
			} else {
				p.sendMessage(Messages.getMessage("notEnoughMoney", ((Integer)cost).toString()));
			}
		} else {
			p.sendMessage(Messages.getMessage("selectLand", null));
		}
	}
}
