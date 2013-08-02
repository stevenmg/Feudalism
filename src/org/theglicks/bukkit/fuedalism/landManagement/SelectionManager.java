package org.theglicks.bukkit.fuedalism.landManagement;

import java.util.HashMap;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class SelectionManager {

	private static HashMap<String, LandSelection> selections =
			new HashMap<String, LandSelection>();
	
	private static LandSelection getSelection(Player p){
		return selections.get(p.getName());
	}
	
	private static void saveSelection(LandSelection select){
		selections.put(select.getOwner(), select);
	}
	
	public static void addClaimPoint(Player player, Location point){
		LandSelection ls = getSelection(player);
		ls.addPoint(point);
		saveSelection(ls);
		
		player.sendMessage("Corner selected: " + point.getX() + ", " + point.getZ());
	}
	
	public static boolean canCreateClaim(Player player){
		return getSelection(player).canCreateClaim();
	}
	
	public static Fief getFief(Player p){
		return getSelection(p).getFief();
	}
	
	public static KingdomClaim getKingdomClaim(Player p){
		return getSelection(p).getKingdomClaim();
	}
}
