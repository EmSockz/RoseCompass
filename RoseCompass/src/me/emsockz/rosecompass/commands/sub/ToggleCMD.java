package me.emsockz.rosecompass.commands.sub;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.emsockz.rosecompass.commands.SubCommandModel;
import me.emsockz.rosecompass.compass.Compass;
import me.emsockz.rosecompass.file.config.MessagesCFG;

public class ToggleCMD extends SubCommandModel {
	
    public ToggleCMD() {
        this.setPlayerCommand(false);
    }

	@SuppressWarnings("serial")
	@Override
	public boolean execute() {
		if (!checkPermission("rosecompass.commands.toggle", true)) return true; 
		
		//yourself
		if (args.length == 1) {
			if (player == null) {
				sendMessage(MessagesCFG.NOT_FOR_CONSOLE);
				return true;
			}
			
			//disable
			if (Compass.data.containsKey(player.getName())) {
				Compass.remove(player);
				sendMessage(MessagesCFG.COMPASS_DISABLED);
			}
			
			//enable
			else {
				Compass.add(player);
				sendMessage(MessagesCFG.COMPASS_ENABLED);
			}
			
		}
		
		//other
		else if (args.length == 2) {
			if (!checkPermission("rosecompass.commands.toggle.other", true)) return true; 
			if (Bukkit.getPlayer(args[1]) == null) {
				sendMessage(MessagesCFG.PLAYER_NOT_FOUND);
				return true;
			}
			Player p = Bukkit.getPlayer(args[1]);
			//disable
			if (Compass.data.containsKey(p.getName())) {
				Compass.remove(p);
				sendMessage(MessagesCFG.COMPASS_DISABLED_OTHER, new HashMap<String, String>() {{put("{player}", p.getName());}});
			}
			
			//enable
			else {
				Compass.add(p);
				sendMessage(MessagesCFG.COMPASS_ENABLED_OTHER, new HashMap<String, String>() {{put("{player}", p.getName());}});
			}
		}
		return true;
	}

}
