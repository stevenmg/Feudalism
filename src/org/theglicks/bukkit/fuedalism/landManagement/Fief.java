package org.theglicks.bukkit.fuedalism.landManagement;

import java.sql.SQLException;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.theglicks.bukkit.fuedalism.DataStore;
import org.theglicks.bukkit.fuedalism.Vassal;

public class Fief  extends Claim {
	String owner;
	DataStore fiefData;
	DataStore fiefOwner;
	
	public Fief(Location loc){
		try {
			fiefData = new DataStore();
			fiefData.rs = fiefData.st.executeQuery("SELECT * FROM `fuedalism`.`fiefs` WHERE MBRCONTAINS("
					+ "`region`, POINT(" + loc.getX() + "," + loc.getZ() + "))"
					+ " AND `world` = '" + loc.getWorld().getName() + "';");
			fiefData.rs.first();
			
			if (exists()) {
				int ownId = fiefData.rs.getInt("vassal");
				fiefOwner = new DataStore();
				fiefOwner.rs = fiefOwner.st.executeQuery("SELECT `name` FROM `fuedalism`.`vassals` WHERE `id` = " + ownId + ";");
				fiefOwner.rs.first();
				owner = fiefOwner.rs.getString("name");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Fief(LandSelection select){
		try {
			fiefData = new DataStore();
			fiefData.rs = fiefData.st.executeQuery("SELECT * FROM `fuedalism`.`fiefs` WHERE MBRIntersects(" + select.getPolygonText() + ", `region`)");
			fiefData.rs.first();
			
			if (exists()) {
				int ownId = fiefData.rs.getInt("vassal");
				fiefOwner = new DataStore();
				fiefOwner.rs = fiefOwner.st.executeQuery("SELECT `name` FROM `fuedalism`.`vassals` WHERE `id` = " + ownId + ";");
				fiefOwner.rs.first();
				owner = fiefOwner.rs.getString("name");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static Fief create(Location corner, Location corner0, Player own){
		try {
			DataStore fOwner = new DataStore();
			DataStore fData = new DataStore();
			fOwner.rs = fOwner.st.executeQuery("SELECT `id` FROM `fuedalism`.`vassals` WHERE `name` = '" + own.getName() + "';");
			fOwner.rs.first();
			String ownId = fOwner.rs.getString("id");
			fData.st.execute("INSERT INTO `fuedalism`.`fiefs` (`region`, `vassal`, `world`) VALUES (PolygonFromText('POLYGON((" + 
					corner.getX() + " " + corner.getZ() + ", " +
					corner0.getX() + " " + corner0.getZ() + ", " +
					corner.getX() + " " + corner0.getZ() + ", " +
					corner0.getX() + " " + corner.getZ() + ", " +
					corner.getX() + " " + corner.getZ() + "))'), '" +
					ownId + "', '" + corner.getWorld().getName() + "');");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return new Fief(corner);
	}
	
	public boolean exists(){
		try {
			if(fiefData.rs.first()){
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} return false;
	}
	
	public void delete(){
		try {
			int id = fiefData.rs.getInt("id");
			fiefData.st.execute("DELETE FROM `fuedalism`.`fiefs` WHERE `id`='" + id + "';");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Vassal getOwner(){
		return new Vassal(owner);
	}
	
	public String getOwnerName(){
		return owner;
	}
}
