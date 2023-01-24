package me.emsockz.rosecompass.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.emsockz.rosecompass.compass.Compass;

public class PlayerJoinListener implements Listener {

	@EventHandler
	public static void onJoin(PlayerJoinEvent e) {
		Compass.add(e.getPlayer());
	}
}
