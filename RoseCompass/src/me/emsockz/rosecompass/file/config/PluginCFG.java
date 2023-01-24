package me.emsockz.rosecompass.file.config;

import org.bukkit.configuration.file.FileConfiguration;

import me.emsockz.rosecompass.Main;
import net.kyori.adventure.bossbar.BossBar;
import net.kyori.adventure.key.Key;


public class PluginCFG {

	public static Long UPDATE;
	public static String LANG;
	
	public static Key TEXT_FONT;
	public static String TEXT_COLOR;
	
	public static BossBar.Overlay BAR_OVERLAY;
	public static BossBar.Color BAR_COLOR;
	
	public static byte[] HASH;
	
	public static void reload() {
		FileConfiguration cfg = Main.getInstance().getConfig();
		
		LANG = cfg.getString("lang");
		UPDATE = cfg.getLong("update");
		
		TEXT_FONT = Key.key(cfg.getString("text.font"));
		TEXT_COLOR = cfg.getString("text.color");
		
		BAR_OVERLAY = BossBar.Overlay.valueOf(cfg.getString("bossbar.overlay"));
		BAR_COLOR = BossBar.Color.valueOf(cfg.getString("bossbar.color"));
	}
}
