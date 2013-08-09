package org.theglicks.bukkit.fuedalism;

import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.theglicks.bukkit.fuedalism.landManagement.Claim;
import org.theglicks.bukkit.fuedalism.landManagement.Fief;
import org.theglicks.bukkit.fuedalism.landManagement.KingdomLandClaim;

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
	
	public boolean canBuild(Claim claim){
		if(claim instanceof Fief){
			Fief f = (Fief) claim;
			if(f.getOwner() == Bukkit.getPlayer(name)){
				return true;
			} return false;
		} else if (claim instanceof KingdomLandClaim){
			KingdomLandClaim c = (KingdomLandClaim) claim;
			if(c.getKingdom() == getKingdom()){
				return true;
			} return false;
		}
		return false;
	}
	
	public boolean canBuild(Location loc){
		KingdomLandClaim c = new KingdomLandClaim(loc);
		Fief f = new Fief(loc);
		
		if(c.exists()){
			if(!canBuild(c)) return false;
		}
		
		if(f.exists()){
			if(!canBuild(f)) return false;
		}
		return true;
	}
	
	public boolean canCreateKingdom(){
		if(getPlayer().hasPermission("Fuedalism.basic.createKingdom")){
			return true;
		} return false;
	}
	
	public boolean hasKingdom(){
		try {
			if(((Integer)vData.rs.getInt("kingdom")) == null){
				return false;
			} return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean isLeader(){
		try {
			return vData.rs.getBoolean("leader");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public int getId(){
		int id = 0;
		try {
			id = vData.rs.getInt("id");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}
	
	public boolean hasInvite(Kingdom k){
		try {
			DataStore ds = new DataStore();
			ds.rs = ds.st.executeQuery("SELECT * FROM `invitations` WHERE `kingdom` = " + k.getId() + " AND `vassal` = " + getId());
			if(ds.rs.next()) return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public void setKingdom(Kingdom k){
		try {
			DataStore ds = new DataStore();
			ds.st.execute("UPDATE `fuedalism`.`vassals` SET `kingdom`='" + k.getId() + "' WHERE `id`='" + getId() + "';");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void setLeader(boolean arg){
		try {
			DataStore ds = new DataStore();
			int bool;
			if (arg)
				bool = 1;
			else
				bool = 0;
			ds.st.execute("UPDATE `fuedalism`.`vassals` SET `leader`=" + bool + " WHERE `id`='" + getId() + "';");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}