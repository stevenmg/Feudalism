package org.theglicks.bukkit.fuedalism;

import net.milkbowl.vault.economy.Economy;

import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.theglicks.bukkit.fuedalism.commandListeners.FiefCmd;
import org.theglicks.bukkit.fuedalism.commandListeners.KingdomCmd;
import org.theglicks.bukkit.fuedalism.eventListeners.BlockBreak;
import org.theglicks.bukkit.fuedalism.eventListeners.BlockPlace;
import org.theglicks.bukkit.fuedalism.eventListeners.CommandPreprocess;
import org.theglicks.bukkit.fuedalism.eventListeners.EntityDamageByEntity;
import org.theglicks.bukkit.fuedalism.eventListeners.PlayerInteract;
import org.theglicks.bukkit.fuedalism.eventListeners.PlayerJoin;
import org.theglicks.bukkit.fuedalism.eventListeners.PlayerMove;

public class Fuedalism extends JavaPlugin{
	
	public JavaPlugin fuedalismPlugin;
	public static ConfigAccessor mainConfig;
	public static Economy econ = null;
	
	@Override
	public void onEnable(){
		fuedalismPlugin = (JavaPlugin) Bukkit.getPluginManager().getPlugin("Fuedalism");
		mainConfig = new ConfigAccessor(fuedalismPlugin, "mainConfig.yml");
		
		mainConfig.saveDefaultConfig();
		mainConfig.getConfig().options().copyDefaults(true);
		
		DataStore.verifyDB();
		
		getServer().getPluginManager().registerEvents(new PlayerInteract(), this);
		getServer().getPluginManager().registerEvents(new PlayerMove(), this);
		getServer().getPluginManager().registerEvents(new BlockBreak(), this);
		getServer().getPluginManager().registerEvents(new BlockPlace(), this);
		getServer().getPluginManager().registerEvents(new EntityDamageByEntity(), this);
		getServer().getPluginManager().registerEvents(new PlayerJoin(), this);
		getServer().getPluginManager().registerEvents(new CommandPreprocess(), this);
		
		RegisteredServiceProvider<Economy> econProvider = getServer().getServicesManager().getRegistration(Economy.class);
		if(econProvider != null)
			econ = econProvider.getProvider();
		
		getCommand("kingdom").setExecutor(new KingdomCmd(this));
		getCommand("fief").setExecutor(new FiefCmd(this));
	}
}