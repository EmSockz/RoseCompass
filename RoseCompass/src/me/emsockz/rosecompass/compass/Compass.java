package me.emsockz.rosecompass.compass;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.entity.Player;

import me.emsockz.rosecompass.Main;
import me.emsockz.rosecompass.file.config.PluginCFG;
import me.emsockz.rosecompass.file.config.SymbolsCFG;
import net.kyori.adventure.bossbar.BossBar;
import net.kyori.adventure.text.Component;

public class Compass {
	
	public static HashMap<String, BossBar> data = new HashMap<>();
	public static List<Player> players = new ArrayList<>();
	
	public static void add(Player p) {
		BossBar bar = BossBar.bossBar(Component.text(" "), 1, PluginCFG.BAR_COLOR, PluginCFG.BAR_OVERLAY);
		
		Main.getAdventure().player(p).showBossBar(bar);
		data.put(p.getName(), bar);
		players.add(p);
	}

	public static void remove(Player p) {
		Main.getAdventure().player(p).hideBossBar(data.get(p.getName()));
		data.remove(p.getName());
		players.remove(p);
	}
	
	public static void update() {
		players.forEach((player) -> {
			data.get(player.getName()).name(SymbolsCFG.data.get(Math.round((player.getEyeLocation().getYaw()+180F)%360)));
		});
	}
}
