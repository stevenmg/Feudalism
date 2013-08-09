package org.theglicks.bukkit.fuedalism.kingdoms;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.theglicks.bukkit.fuedalism.DataStore;
import org.theglicks.bukkit.fuedalism.Fuedalism;
import org.theglicks.bukkit.fuedalism.Vassal;

public class Kingdom {
	private Player owner;
	private String name;
	private DataStore kData = new DataStore();
	private int id;
	
	public Kingdom(String kingdomName){
		try {
			name = kingdomName;
			kData.rs = kData.st.executeQuery("SELECT * FROM `kingdoms` WHERE `name` = '" + name + "';");
			kData.rs.first();
			id = kData.rs.getInt("id");
			
			DataStore oName = new DataStore();
			oName.rs = oName.st.executeQuery("SELECT `name` FROM `vassals` WHERE `id` = " + kData.rs.getInt("owner") + ";");
			oName.rs.first();
			owner = Bukkit.getPlayer(oName.rs.getString("name"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Kingdom(Player kOwner){
		try {
			owner = kOwner;
			DataStore oID = new DataStore();
			oID.rs = oID.st.executeQuery("SELECT `id` FROM `vassals` WHERE `name` = '" + owner.getName() + "';");
			oID.rs.first();
			kData.rs = kData.st.executeQuery("SELECT * FROM `kingdoms` WHERE `owner` = " + oID.rs.getInt("id") + ";");
			kData.rs.first();
			id = kData.rs.getInt("id");
			name = kData.rs.getString("name");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static Kingdom createKingdom(String name, String ownerName){
		try {
			DataStore oID = new DataStore();
			oID.rs = oID.st.executeQuery("SELECT `id` FROM `vassals` WHERE `name` = '" + ownerName + "';");
			oID.rs.first();
			DataStore ds = new DataStore();
			ds.st.execute("INSERT INTO `fuedalism`.`kingdoms` (`name`, `owner`) VALUES ('" + name + "', '" + oID.rs.getInt("id") + "');");
			
			return new Kingdom(name);
		} catch (SQLException e) {
			e.printStackTrace();
		} return null;
	}
	
	public boolean exists(){
		try {
			if(kData.rs.first()){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} return false;
	}
	
	public List<String> getMembers(){
		try {
			DataStore mList = new DataStore();
			mList.rs = mList.st.executeQuery("SELECT `name` FROM `vassals` WHERE `kingdom` = " + kData.rs.getInt("id") + ";");
			
			List<String> memberList = new ArrayList<String>();
			while(mList.rs.next()){
				memberList.add(mList.rs.getString("name"));
			}
			return memberList;
		} catch (SQLException e) {
			e.printStackTrace();
		} return null;
	}
	
	public Player getOwner(){
		return owner;
	}
	
	public String getName(){
		return name;
	}
	
	public boolean isAllied(Kingdom k){
		return RelationManager.hasAlliance(this, k);
	}
	
	public int getId(){
		return id;
	}
	
	public void invite(Vassal v){
		try {
			DataStore ds = new DataStore();
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DAY_OF_MONTH, Fuedalism.mainConfig.getConfig().getInt("Invitations.daysToExpire"));
			String expiration = cal.get(Calendar.YEAR) + "-" + cal.get(Calendar.MONTH) + "-" + cal.get(Calendar.DAY_OF_MONTH);
			ds.st.execute("INSERT INTO `fuedalism`.`invitations` (`kingdom`, `vassal`, `expiration`) VALUES ('" + getId()
					+ "', '" + v.getId() + "', '" + expiration + "');");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void delete(){
		try {
			DataStore ds = new DataStore();
			ds.st.execute("DELETE FROM `fuedalism`.`kingdoms` WHERE `id`='" + getId() + "';");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
