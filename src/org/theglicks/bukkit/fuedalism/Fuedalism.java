package org.theglicks.bukkit.fuedalism;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.theglicks.bukkit.fuedalism.commands.FiefCmd;
import org.theglicks.bukkit.fuedalism.commands.KingdomCmd;
import org.theglicks.bukkit.fuedalism.events.PlayerInteract;
import org.theglicks.bukkit.fuedalism.events.PlayerMove;

public class Fuedalism extends JavaPlugin{
	
	public JavaPlugin fuedalismPlugin;
	public static ConfigAccessor mainConfig;
	
	@Override
	public void onEnable(){
		fuedalismPlugin = (JavaPlugin) Bukkit.getPluginManager().getPlugin("Fuedalism");
		mainConfig = new ConfigAccessor(fuedalismPlugin, "mainConfig.yml");
		
		mainConfig.saveDefaultConfig();
		mainConfig.getConfig().options().copyDefaults(true);
		
		getServer().getPluginManager().registerEvents(new PlayerInteract(), this);
		getServer().getPluginManager().registerEvents(new PlayerMove(), this);
		
		getCommand("kingdom").setExecutor(new KingdomCmd(this));
		getCommand("fief").setExecutor(new FiefCmd(this));
	}
}