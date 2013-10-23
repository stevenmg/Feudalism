package org.theglicks.bukkit.governance.eventListeners;

import java.sql.SQLException;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.theglicks.bukkit.governance.DataStore;

public class PlayerJoin implements Listener{
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event){
		//Makes sure player is in the database and adds them if not
		try {
			DataStore ds = new DataStore();
			ds.rs = ds.st.executeQuery("SELECT * FROM `governance`.`vassals` WHERE `name` = '" + event.getPlayer().getName() + "'");
			
			if(!ds.rs.next()) {
				ds.st.execute("INSERT INTO `governance`.`vassals` (`name`) VALUES ('" + event.getPlayer().getName() + "');");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
