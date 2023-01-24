package me.emsockz.rosecompass.file;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import me.emsockz.rosecompass.Main;

public class SymbolsFile {
	
	private final String path = "symbols.yml";
	private File file;
	private FileConfiguration config;

	public SymbolsFile() {
		if (!new File(Main.getInstance().getDataFolder(), path).exists()) {
			Main.getInstance().saveResource(path, false);
			
			file = new File(Main.getInstance().getDataFolder(), path);
			config = YamlConfiguration.loadConfiguration(file);
		} else {
			file = new File(Main.getInstance().getDataFolder(), path);
			config = YamlConfiguration.loadConfiguration(file);
		}
	}
	

	public void save() {
		file = new File(Main.getInstance().getDataFolder(), path);
		
		try {
			config.save(file);
		} catch (IOException e) {
			throw new RuntimeException("Failed to save file "+path, e);
		}
		
		config = YamlConfiguration.loadConfiguration(file);
	}

	public void reload() {
		file = new File(Main.getInstance().getDataFolder(), path);
		config = YamlConfiguration.loadConfiguration(file);
	}

	public FileConfiguration getConfig() {
		return config;
	}
}
