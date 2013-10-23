package org.theglicks.bukkit.governance.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.theglicks.bukkit.governance.Governance;
import org.theglicks.bukkit.governance.Messages;
import org.theglicks.bukkit.governance.Vassal;
import org.theglicks.bukkit.governance.landManagement.SelectionManager;

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
			int cost = Governance.mainConfig.getConfig().getInt("Economy.costPerBlockKingdom") * SelectionManager.getSelection(p).getSize();
			if(Governance.econ.has(p.getName(), p.getWorld().getName(), cost)){
				Governance.econ.withdrawPlayer(p.getName(), p.getWorld().getName(), cost);
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
