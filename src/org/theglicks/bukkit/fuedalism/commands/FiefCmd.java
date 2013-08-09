package org.theglicks.bukkit.fuedalism.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.theglicks.bukkit.fuedalism.Fuedalism;
import org.theglicks.bukkit.fuedalism.Vassal;
import org.theglicks.bukkit.fuedalism.landManagement.Fief;
import org.theglicks.bukkit.fuedalism.landManagement.SelectionManager;

public class FiefCmd implements CommandExecutor{
	public FiefCmd(Fuedalism fuedalism) {}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		if(sender instanceof Player){
			Player p = (Player) sender;
			Vassal v = new Vassal(p.getName());
			if(args[0].equalsIgnoreCase("create")){
				if(SelectionManager.canCreateClaim(p)){
					int cost = Fuedalism.mainConfig.getConfig().getInt("Economy.costPerBlockFief") * SelectionManager.getSelection(p).getSize();
					if(Fuedalism.econ.has(p.getName(), p.getWorld().getName(), cost)){
						Fuedalism.econ.withdrawPlayer(p.getName(), p.getWorld().getName(), cost);
						SelectionManager.getFief(p);
						p.sendMessage("Fief created Successfully!");
					}
				}
			} else if(args[0].equalsIgnoreCase("abandon")){
				Fief f = new Fief(p.getLocation());
				
				if(f.exists()){
					if(f.getOwner() == v){
						f.delete();
						p.sendMessage("Fief abandoned!");
					} else {
						p.sendMessage("You do not own this fief!");
					}
				} else { p.sendMessage("Fief does not exist at your location!"); }
			}
		}
		return true;
	}
}
