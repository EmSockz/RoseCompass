package me.emsockz.rosecompass.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import me.emsockz.rosecompass.compass.Compass;

public class PlayerQuitListener implements Listener {
	
	@EventHandler
	public static void onJoin(PlayerQuitEvent e) {
		Compass.remove(e.getPlayer());
	}
}
