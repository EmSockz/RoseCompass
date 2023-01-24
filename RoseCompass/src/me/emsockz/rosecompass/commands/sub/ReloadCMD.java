package me.emsockz.rosecompass.commands.sub;

import me.emsockz.rosecompass.Main;
import me.emsockz.rosecompass.commands.SubCommandModel;
import me.emsockz.rosecompass.file.config.MessagesCFG;

public class ReloadCMD extends SubCommandModel {
	
    public ReloadCMD() {
        this.setPlayerCommand(false);
    }

	@Override
	public boolean execute() {
		if (!checkPermission("rosecompass.commands.reload", true)) return true; 
		Main.getInstance().reloadPlugin();
		sendMessage(MessagesCFG.RELOAD_PLUGIN);
		return true;
	}
}
