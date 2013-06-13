package org.theglicks.bukkit.fuedalism.landManagement;

import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.theglicks.bukkit.fuedalism.DataStore;

public class Fief {
	Location corner1;
	Location corner2;
	Player owner;
	DataStore fiefData;
	DataStore fiefOwner;
	
	public Fief(Location loc){
		try {
			fiefData = new DataStore();
			fiefData.rs = fiefData.st.executeQuery("SELECT * FROM `fiefs` WHERE MBRCONTAINS("
					+ "`region`, POINT(" + loc.getX() + "," + loc.getZ() + "))"
					+ " AND `world` = '" + loc.getWorld().getName() + "';");
			fiefData.rs.first();
			
			if (exists()) {
				int ownId = fiefData.rs.getInt("vassal");
				fiefOwner = new DataStore();
				fiefOwner.rs = fiefOwner.st.executeQuery("SELECT `name` FROM `vassals` "
								+ "WHERE `id` = " + ownId + ";");
				fiefOwner.rs.first();
				owner = Bukkit.getPlayer(fiefOwner.rs.getString("name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Fief(Location corner, Location corner0, Player own){
		fiefData = new DataStore();
		fiefOwner = new DataStore();
		corner1 = corner;
		corner2 = corner0;
		owner = own;
	}
	
	public void load(){
		
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
	
	public void save(){
		try {
			fiefOwner.rs = fiefOwner.st.executeQuery("SELECT `id` FROM `vassals` " +
					"WHERE `name` = '" + owner.getName() + "';");
			fiefOwner.rs.first();
			String ownId = fiefOwner.rs.getString("id");
			
			fiefData.st.execute("INSERT INTO `fuedalism`.`fiefs` (" +
					"`region`, `vassal`, `world`) VALUES (PolygonFromText('POLYGON((" + 
					corner1.getX() + " " + corner1.getZ() + ", " +
					corner2.getX() + " " + corner2.getZ() + ", " +
					corner1.getX() + " " + corner2.getZ() + ", " +
					corner2.getX() + " " + corner1.getZ() + ", " +
					corner1.getX() + " " + corner1.getZ() + "))'), '" +
					ownId + "', '" + corner1.getWorld().getName() + "');");
			
			fiefData.rs = fiefData.st.executeQuery("SELECT * FROM `fiefs` WHERE MBRCONTAINS("
					+ "`region`, POINT(" + corner1.getX() + "," + corner1.getZ() + "))"
					+ " AND `world` = '" + corner1.getWorld().getName() + "';");
			fiefData.rs.first();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void delete(){
		try {
			int id = fiefData.rs.getInt("id");
			fiefData.st.execute("DELETE FROM `fuedalism`.`fiefs` WHERE `id`='" + id + "';");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
