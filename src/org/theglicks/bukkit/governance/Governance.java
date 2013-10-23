package org.theglicks.bukkit.governance;

import net.milkbowl.vault.economy.Economy;

import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.theglicks.bukkit.governance.commandListeners.FiefCmd;
import org.theglicks.bukkit.governance.commandListeners.KingdomCmd;
import org.theglicks.bukkit.governance.eventListeners.BlockBreak;
import org.theglicks.bukkit.governance.eventListeners.BlockPlace;
import org.theglicks.bukkit.governance.eventListeners.CommandPreprocess;
import org.theglicks.bukkit.governance.eventListeners.EntityDamageByEntity;
import org.theglicks.bukkit.governance.eventListeners.PlayerInteract;
import org.theglicks.bukkit.governance.eventListeners.PlayerJoin;
import org.theglicks.bukkit.governance.eventListeners.PlayerMove;

public class Governance extends JavaPlugin{
	
	public JavaPlugin governancePlugin;
	public static ConfigAccessor mainConfig;
	public static Economy econ = null;
	
	@Override
	public void onEnable(){
		governancePlugin = (JavaPlugin) Bukkit.getPluginManager().getPlugin("Governance");
		mainConfig = new ConfigAccessor(governancePlugin, "mainConfig.yml");
		
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
		
		Messages.loadMessages(this);
		
		RegisteredServiceProvider<Economy> econProvider = getServer().getServicesManager().getRegistration(Economy.class);
		if(econProvider != null)
			econ = econProvider.getProvider();
		
		getCommand("kingdom").setExecutor(new KingdomCmd(this));
		getCommand("fief").setExecutor(new FiefCmd(this));
	}
}