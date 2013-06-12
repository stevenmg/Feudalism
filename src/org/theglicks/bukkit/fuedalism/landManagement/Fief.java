package org.theglicks.bukkit.fuedalism.landManagement;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.theglicks.bukkit.fuedalism.DataStore;

public class Fief {
	Location corner1;
	Location corner2;
	Player owner;
	ResultSet rs;
	
	public Fief(Location loc){
		try {
			rs = DataStore.st.executeQuery("SELECT * FROM `fiefs` WHERE MBRCONTAINS(" +
					"`region`, POINT(" + loc.getX() + "," + loc.getZ() + "))" +
					" AND `world` = '" + loc.getWorld().getName() + "';");
			rs.first();
			
			if (exists()) {
				int ownId = rs.getInt("vassal");
				ResultSet ownName = DataStore.st.executeQuery("SELECT `name` FROM `vassals` "
								+ "WHERE `id` = " + ownId + ";");
				owner = Bukkit.getPlayer(ownName.getString("name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Fief(Location corner, Location corner0, Player own){
		corner1 = corner;
		corner2 = corner0;
		owner = own;
	}
	
	public void load(){
		
	}
	
	public boolean exists(){
		try {
			if(rs.first()){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} return false;
	}
	
	public void save(){
		try {
			ResultSet results = DataStore.st.executeQuery("SELECT `id` FROM `vassals` " +
					"WHERE `name` = '" + owner.getName() + "';");
			results.first();
			String ownId = results.getString("id");
			
			DataStore.st.execute("INSERT INTO `fuedalism`.`fiefs` (" +
					"`region`, `vassal`, `world`) VALUES (GeomFromText('POLYGON((" + 
					corner1.getX() + " " + corner1.getZ() + ", " +
					corner2.getX() + " " + corner2.getZ() + ", " +
					corner1.getX() + " " + corner2.getZ() + ", " +
					corner2.getX() + " " + corner1.getZ() + ", " +
					corner1.getX() + " " + corner1.getZ() + "))'), '" +
					ownId + "', '" + corner1.getWorld().getName() + "');");
			
			rs = DataStore.st.executeQuery("SELECT * FROM `fiefs` WHERE MBRCONTAINS(" +
					"`region`, POINT(" + corner1.getX() + "," + corner1.getZ() + "))" +
					" AND `world` = '" + corner1.getWorld().getName() + "';");
			rs.first();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void delete(){
		try {
			DataStore.st.execute("DELETE FROM `fuedalism`.`fiefs` WHERE `id`='" +
					rs.getInt("id") + "';");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
