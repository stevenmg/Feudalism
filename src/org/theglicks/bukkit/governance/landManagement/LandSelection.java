package org.theglicks.bukkit.governance.landManagement;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.theglicks.bukkit.governance.Vassal;

public class LandSelection {
	private Location point1;
	private Location point2;
	private String playerName;
	
	public LandSelection(String player){
		playerName = player;
	}
	
	public LandSelection(Location p1, Location p2, String player){
		point1 = p1;
		point2 = p2;
		playerName = player;
	}
	
	public boolean canCreateClaim(){
		//Checks size
		if(!(point1.distance(point2) >= 15)) return false;
				
		//Checks if it crosses another kingdom claim
		KingdomLandClaim k = new KingdomLandClaim(this);
		if(k.exists()) return false;
		
		//Checks if it crosses a fief
		Fief f = new Fief(this);
		if(f.exists()) return false;
				
		return true;
	}
	
	/*public boolean canCreateKingdomClaim(){
		//Checks size
		if(!(point1.distance(point2) >= 15)) return false;
		
		//Checks if it crosses another kingdom claim
		KingdomLandClaim k = new KingdomLandClaim(this);
		if(k.exists()) return false;
		
		//Checks if it crosses a fief
		Fief f = new Fief(this);
		if(f.exists()) return false;
		
		return true;
	}
	
	public boolean canCreateFief(){
		//Checks size
		if(!(point1.distance(point2) >= 15)) return false;
		
		//Checks if it crosses another kingdom claim
		KingdomLandClaim k = new KingdomLandClaim(this);
		if(k.exists()) return false;
				
		//Checks if it crosses a fief
		Fief f = new Fief(this);
		if(f.exists()) return false;
		
		return true;
	} */
	
	public Fief getFief(){
		return Fief.create(point1, point2, Bukkit.getPlayer(playerName));
	}
	
	public KingdomLandClaim getKingdomClaim(){
		Vassal v = new Vassal(playerName);
		
		return KingdomLandClaim.create(point1, point2, v.getKingdom());
	}
	
	public String getOwner(){
		return playerName;
	}
	
	public void addPoint(Location loc){
		if(point2 == null) {
			point2 = loc;
		} else if(point1 == null) {
			point1 = loc;
		} else {
			point2 = point1;
			point1 = loc;
		}
	}
	
	public int getSize(){
		Location loc0 = point1;
		Location loc1 = point2;
		Location loc2 = new Location(loc0.getWorld(), loc0.getX(), 0, loc1.getZ());
		loc0.setY(0);
		loc1.setY(0);
		
		return (int) (loc0.distance(loc1) * loc1.distance(loc2));
	}
	
	public String getPolygonText(){
		return "PolygonFromText('POLYGON((" + point1.getX() + " " + point1.getZ() + ", " +
				point2.getX() + " " + point2.getZ() + ", " + point1.getX() + " " + point2.getZ() + ", " +
				point2.getX() + " " + point1.getZ() + ", " + point1.getX() + " " + point1.getZ() + "))')";
	}
}
