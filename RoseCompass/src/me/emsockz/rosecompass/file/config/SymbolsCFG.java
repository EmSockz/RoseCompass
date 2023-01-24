package me.emsockz.rosecompass.file.config;

import java.util.HashMap;

import org.bukkit.configuration.file.FileConfiguration;

import me.emsockz.rosecompass.Main;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;

public class SymbolsCFG {

	public static HashMap<Integer, Component> data;
	
	public static void reload() {
		FileConfiguration cfg = Main.getSymbols().getConfig();
		HashMap<Integer, Component> d = new HashMap<>();
		for (int n = 0; n <= 360; n++) {
			Component s = MiniMessage.miniMessage().deserialize(PluginCFG.TEXT_COLOR+cfg.getString("symbols."+n)).font(PluginCFG.TEXT_FONT);
			d.put(n, s);
		}
		
		data = d;
	}
}
