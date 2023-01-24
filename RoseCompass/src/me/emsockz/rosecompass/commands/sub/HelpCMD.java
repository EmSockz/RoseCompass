package me.emsockz.rosecompass.commands.sub;

import me.emsockz.rosecompass.commands.SubCommandModel;
import me.emsockz.rosecompass.file.config.MessagesCFG;

public class HelpCMD extends SubCommandModel {
	
    public HelpCMD() {
        this.setPlayerCommand(false);
    }
    
	@Override
	public boolean execute() {
		if (checkPermission("rosecompass.commands.help.admin", false))
			sendMessage(MessagesCFG.HELP_COMMAND_ADMIN);
		else if (checkPermission("rosecompass.commands.help", false)) 
			sendMessage(MessagesCFG.HELP_COMMAND);	
		else
			sendMessage(MessagesCFG.NO_PERMISSIONS);
		
		return true;
	}
}
