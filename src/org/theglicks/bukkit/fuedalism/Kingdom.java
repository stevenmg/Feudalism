package org.theglicks.bukkit.fuedalism;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Kingdom {
	private String owner;
	private String name;
	private DataStore kData = new DataStore();
	private int id;
	
	public Kingdom(String kingdomName){
		try {
			name = kingdomName;
			kData.rs = kData.st.executeQuery("SELECT * FROM `fuedalism`.`kingdoms` WHERE `name` = '" + name + "';");
			kData.rs.first();
			id = kData.rs.getInt("id");
			
			DataStore oName = new DataStore();
			oName.rs = oName.st.executeQuery("SELECT `name` FROM `fuedalism`.`vassals` WHERE `id` = " + kData.rs.getInt("owner") + ";");
			oName.rs.first();
			owner = oName.rs.getString("name");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Kingdom(Player kOwner){
		try {
			owner = kOwner.getName();
			DataStore oID = new DataStore();
			oID.rs = oID.st.executeQuery("SELECT `id` FROM `fuedalism`.`vassals` WHERE `name` = '" + owner + "';");
			oID.rs.first();
			kData.rs = kData.st.executeQuery("SELECT * FROM `fuedalism`.`kingdoms` WHERE `owner` = " + oID.rs.getInt("id") + ";");
			kData.rs.first();
			id = kData.rs.getInt("id");
			name = kData.rs.getString("name");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static Kingdom createKingdom(String name, String ownerName){
		Bukkit.getLogger().info("10");
		try {
			Bukkit.getLogger().info("11");
			DataStore oID = new DataStore();
			oID.rs = oID.st.executeQuery("SELECT `id` FROM `fuedalism`.`vassals` WHERE `name` = '" + ownerName + "';");
			oID.rs.first();
			DataStore ds = new DataStore();
			ds.st.execute("INSERT INTO `fuedalism`.`kingdoms` (`name`, `owner`) VALUES ('" + name + "', '" + oID.rs.getInt("id") + "');");
			Bukkit.getLogger().info("INSERT INTO `fuedalism`.`kingdoms` (`name`, `owner`) VALUES ('" + name + "', '" + oID.rs.getInt("id") + "');");
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
			mList.rs = mList.st.executeQuery("SELECT `name` FROM `fuedalism`.`vassals` WHERE `kingdom` = " + kData.rs.getInt("id") + ";");
			
			List<String> memberList = new ArrayList<String>();
			while(mList.rs.next()){
				memberList.add(mList.rs.getString("name"));
			}
			return memberList;
		} catch (SQLException e) {
			e.printStackTrace();
		} return null;
	}
	
	public String getOwner(){
		return owner;
	}
	
	public String getName(){
		return name;
	}
	
	public boolean isAllied(Kingdom k){
		return getRelation(k) == 1;
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
	
	public void setRelation(Kingdom k, int relation){
		int id1;
		int id2;
		if (getId() > k.getId()) {
			id1 = getId();
			id2 = k.getId();
		} else {
			id1 = k.getId();
			id2 = getId();
		}
		
		try {
			DataStore ds = new DataStore();
			ds.st.execute("INSERT INTO `fuedalism`.`relations` (`kingdom1`, `kingdom2`, `relation`) VALUES ('" 
					+ id1 + "', '" + id2 + "', '" + relation + "');");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int getRelation(Kingdom k){
		int id1;
		int id2;
		if (getId() > k.getId()) {
			id1 = getId();
			id2 = k.getId();
		} else {
			id1 = k.getId();
			id2 = getId();
		}
		
		try {
			DataStore ds = new DataStore();
			ds.rs = ds.st.executeQuery("SELECT * FROM `fuedalism`.`relations` WHERE `kingdom1` = " + id1 + " AND `kingdom2` = " + id2);
			if(ds.rs.next())
				return ds.rs.getInt("relation");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 3;
	}
	
	public boolean hasAllyRequest(Kingdom kSender){
		try {
			DataStore ds = new DataStore();
			ds.rs = ds.st.executeQuery("SELECT * FROM `fuedalism`.`alliancerequests` WHERE `kingdom_sender` = " + kSender.getId()
					+ " AND `kingdom_receiver` = " + getId());
			if(ds.rs.next())
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public void sendAllyRequest(Kingdom k){
		try {
			DataStore ds = new DataStore();
			ds.st.execute("INSERT INTO `fuedalism`.`alliancerequests` (`kingdom_sender`, `kingdom_receiver`, `expiration`) VALUES "
					+ "('" + getId() + "', '" + k.getId() + "', '3013-1-1');");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
