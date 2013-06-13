package org.theglicks.bukkit.fuedalism.landManagement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class SelectionManager {
	
	private static HashMap<Player, List<Location>> claimPoints =
			new HashMap<Player, List<Location>>();
	
	public static void addClaimPoint(Player player, Location point){
		if(!claimPoints.containsKey(player) || claimPoints.get(player).isEmpty()){
			List<Location> tempList = new ArrayList<Location>();
			tempList.add(point);
			claimPoints.put(player, tempList);
		} else if(claimPoints.get(player).size() == 1){
			List<Location> tempList = claimPoints.get(player);
			tempList.add(point);
			claimPoints.put(player, tempList);
		} else {
			claimPoints.remove(player);
			List<Location> tempList = new ArrayList<Location>();
			tempList.add(point);
			claimPoints.put(player, tempList);
		}
		
		player.sendMessage("Corner selected: " + point.getX() + ", " + point.getZ());
	}
	
	public static boolean canCreateClaim(Player player){
		if (claimPoints.get(player).size() == 2){
			double distance = claimPoints.get(player).get(0).distance(
					claimPoints.get(player).get(1));
			if(distance >= 15){
				return true;
			}
		}
		return false;
	}
	
	public static Fief getFief(Player p){
		Fief f = new Fief(claimPoints.get(p).get(0), claimPoints.get(p).get(1), p);
		return f;
	}
}
