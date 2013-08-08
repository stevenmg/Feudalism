package org.theglicks.bukkit.fuedalism.kingdoms;

import java.sql.SQLException;
import java.util.Calendar;

import org.theglicks.bukkit.fuedalism.DataStore;
import org.theglicks.bukkit.fuedalism.Fuedalism;

public class RelationManager {
	public static boolean hasAlliance(Kingdom k1, Kingdom k2){	
		try {
			DataStore ds = new DataStore();
			ds.rs = ds.st.executeQuery("SELECT * FROM `alliances` WHERE (`kingdom1` = " + k1.getId() + " AND `kingdom2` = " + k2.getId() 
					+ ") OR (`kingdom1` = " + k1.getId() + " AND `kingdom2` = " + k2.getId() + ")");
			if(ds.rs.next()){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public static void addAlliance(Kingdom k1, Kingdom k2){
		try {
			DataStore ds = new DataStore();
			ds.st.execute("INSERT INTO `fuedalism`.`alliances` (`kingdom1`, `kingdom2`) VALUES ('" + k1.getId() + "', '"
					+ k2.getId() + "');");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static boolean hasRequest(Kingdom sender, Kingdom receiver){
		try {
			DataStore ds = new DataStore();
			ds.rs = ds.st.executeQuery("SELECT * FROM `alliancerequests` WHERE `kingdom_sender` = " + sender.getId() + 
					" AND `kingdom_receiver` = " + receiver.getId());
			if(ds.rs.next()){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static void addRequest(Kingdom sender, Kingdom receiver){
		try {
			DataStore ds = new DataStore();
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DAY_OF_MONTH, Fuedalism.mainConfig.getConfig().getInt("AllianceRequest.daysToExpire"));
			String expireDate = cal.get(Calendar.YEAR) + "-" + cal.get(Calendar.MONTH) + "-" + cal.get(Calendar.DAY_OF_MONTH);
			ds.st.execute("INSERT INTO `fuedalism`.`alliancerequests` (`kingdom_sender`, `kingdom_receiver`, `expiration`) VALUES "
					+ "('" + sender.getId() + "', '" + receiver.getId() + "', '" + expireDate + "');");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static boolean hasEnemy(Kingdom k1, Kingdom k2){
		try {
			DataStore ds = new DataStore();
			ds.rs = ds.st.executeQuery("SELECT * FROM `enemies` WHERE (`kingdom1` = " + k1.getId() + " AND `kingdom2` = "
					+ k2.getId() + ") OR (`kingdom2` = " + k1.getId() + " AND `kingdom1` = " + k2.getId() + ")");
			if(ds.rs.next()){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static void addEnemy(Kingdom k1, Kingdom k2){
		try {
			DataStore ds = new DataStore();
			ds.st.execute("INSERT INTO `fuedalism`.`enemies` (`kingdom1`, `kingdom2`) VALUES ('" + k1.getId() + "', '"
					+ k2.getId() + "');");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
