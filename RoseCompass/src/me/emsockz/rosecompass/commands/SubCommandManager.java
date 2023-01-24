package me.emsockz.rosecompass.commands;

import java.util.HashMap;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.emsockz.rosecompass.Main;
import me.emsockz.rosecompass.commands.sub.HelpCMD;
import me.emsockz.rosecompass.commands.sub.ReloadCMD;
import me.emsockz.rosecompass.commands.sub.ToggleCMD;
import me.emsockz.rosecompass.file.config.MessagesCFG;

public class SubCommandManager implements CommandExecutor {

	public static HashMap<String, SubCommandModel> subcommands = new HashMap<>();
	
    public SubCommandManager() {
    	subcommands.put("reload", new ReloadCMD());
    	subcommands.put("help",   new HelpCMD());
    	subcommands.put("toggle",   new ToggleCMD());
    }
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            subcommands.get("help").onExecute(sender, command, label, args);
            return true;
        }
        String subcommand = args[0].toLowerCase();
        if (subcommands.get(subcommand) == null) {
        	((sender instanceof Player) ? Main.getAdventure().player((Player)sender) : Main.getAdventure().console())
            .sendMessage(MessagesCFG.COMMAND_DOES_NOT_EXIST.getString());
            return true;
        }

        subcommands.get(subcommand).onExecute(sender, command, label, args);
        return true;
	}
}
