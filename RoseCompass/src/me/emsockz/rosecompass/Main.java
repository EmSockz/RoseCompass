package me.emsockz.rosecompass;

import java.io.File;
import java.util.List;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.command.PluginCommand;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import me.emsockz.rosecompass.listeners.PlayerJoinListener;
import me.emsockz.rosecompass.listeners.PlayerQuitListener;
import me.emsockz.rosecompass.commands.SubCommandManager;
import me.emsockz.rosecompass.commands.TabCommandManager;
import me.emsockz.rosecompass.compass.Compass;
import me.emsockz.rosecompass.file.MessagesFile;
import me.emsockz.rosecompass.file.SymbolsFile;
import me.emsockz.rosecompass.file.config.MessagesCFG;
import me.emsockz.rosecompass.file.config.PluginCFG;
import me.emsockz.rosecompass.file.config.SymbolsCFG;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;

public class Main extends JavaPlugin {

	private static Main instance = null;
	private static MessagesFile messages = null;
	private static SymbolsFile symbols = null;
	private static BukkitAudiences adventure;
	
	private static Logger log = Logger.getLogger("Minecraft");
    
	@Override
	public void onEnable() {
		instance = this;
		adventure = BukkitAudiences.create(instance);
		loadMessagesFiles();
		
		saveDefaultConfig();
		PluginCFG.LANG = getConfig().getString("lang");
		messages = new MessagesFile();
		symbols = new SymbolsFile();
		MessagesCFG.refreshAll();
		PluginCFG.reload();
		SymbolsCFG.reload();
		
        PluginCommand pluginCommand = instance.getCommand("rosecompass");
        	pluginCommand.setExecutor(new SubCommandManager());
        	pluginCommand.setTabCompleter(new TabCommandManager());
        	
        Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(), instance);
        Bukkit.getPluginManager().registerEvents(new PlayerQuitListener(), instance);
    	 
        Bukkit.getScheduler().runTaskTimerAsynchronously(instance, () -> {
        	Compass.update();
        }, 0, PluginCFG.UPDATE);
	}
	
	
	@Override
	public void onDisable() {
		if (adventure != null) {
			adventure.close();
			adventure = null;
		}
	}
	
	public void disablePlugin() {
		instance.setEnabled(false);
	}
	
	public void loadMessagesFiles() {
		List<String> langs = List.of("en", "ru");;
		
		langs.forEach((lang) -> {
			if (!new File(Main.getInstance().getDataFolder(), "lang/messages_"+lang+".yml").exists()) 
				instance.saveResource("lang/messages_"+lang+".yml", false);
		});
	}
	
	public static void debug(String text) {
		log.severe("DEBUG: "+text);
	}
	
    public static void logInfo(String text){
    	log.info(text);
    }
    
    public static void logWarning(String text){
    	log.warning(text);
    }
    
    public static void logSevere(String text){
    	log.severe(text);
    }
    
    public void schedulerRun(Runnable task) {
        Bukkit.getScheduler().runTask(instance, task);
    }
	
    public static Main getInstance() {
    	return instance;
    }
    
    public static MessagesFile getMessages() {
    	return messages;
    }
    
    public static SymbolsFile getSymbols() {
    	return symbols;
    }
    
	public static BukkitAudiences getAdventure() {
		if (adventure == null) {
			throw new IllegalStateException("Tried to acces Adventure when the plugin was disables!");
		}
		
		return adventure;
	}
    
	public static FileConfiguration getMCFG() {
		return messages.getConfig();
	}
	
	public void reloadPlugin() {
		instance.reloadConfig();
		PluginCFG.LANG = instance.getConfig().getString("lang");
		messages.reload();
		MessagesCFG.refreshAll();
		PluginCFG.reload();
		SymbolsCFG.reload();
	}
}
