package org.theglicks.bukkit.fuedalism;

import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.theglicks.bukkit.fuedalism.kingdoms.Kingdom;

public class Vassal{
	private String name;
	private DataStore vData = new DataStore();
	
	public Vassal(String vassalName){
		try {
			name = vassalName;
			vData.rs = vData.st.executeQuery("SELECT * FROM `vassals` WHERE `name` = '" + name + "';");
			vData.rs.first();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Kingdom getKingdom(){
		try {
			DataStore ds = new DataStore();
			ds.rs = ds.st.executeQuery("SELECT `name` FROM `kingdoms` WHERE `id` = " + vData.rs.getInt("kingdom") + ";");
			ds.rs.first();
			
			return new Kingdom(ds.rs.getString("name"));
		} catch (SQLException e) {
			e.printStackTrace();
		} return null;
	}
	
	public Player getPlayer(){
		return Bukkit.getPlayer(name);
	}
	
	public String getName(){
		return name;
	}
	
	public boolean isAllied(Vassal v){
		return getKingdom().isAllied(v.getKingdom());
	}
}