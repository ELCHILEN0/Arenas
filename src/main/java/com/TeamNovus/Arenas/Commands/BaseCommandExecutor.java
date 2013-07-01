package com.TeamNovus.Arenas.Commands;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import com.TeamNovus.Arenas.Arenas;
import com.TeamNovus.Arenas.Colors;
import com.TeamNovus.Arenas.Permission;

public class BaseCommandExecutor implements CommandExecutor, TabCompleter {
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {		
		if(args.length == 0) {			
			sender.sendMessage(Colors.EXTRA + "__________________.[ " + Colors.HIGHLIGHT + Arenas.getPlugin().getName() + Colors.EXTRA + " ].__________________");
			sender.sendMessage(Colors.TITLE + "Version: " 		+ Colors.TEXT + Arenas.getPlugin().getDescription().getVersion());
			sender.sendMessage(Colors.TITLE + "Description: " 	+ Colors.TEXT + Arenas.getPlugin().getDescription().getDescription());
			sender.sendMessage(Colors.TITLE + "Author: " 		+ Colors.TEXT + Arenas.getPlugin().getDescription().getAuthors().get(0));
			sender.sendMessage(Colors.TITLE + "Website: " 		+ Colors.TEXT + Arenas.getPlugin().getDescription().getWebsite());
			sender.sendMessage(Colors.EXTRA + "---------------------------------------------------");
			return true;
		}
		
		if(CommandManager.getCommand(args[0]) == null) {
			sender.sendMessage(Colors.ERROR + "The specified command was not found!");
			return true;
		}
		
		BaseCommand command = CommandManager.getCommand(args[0]);
		Object[] commandArgs = ArrayUtils.remove(args, 0);
		
		if(sender instanceof Player && !(command.isPlayerCommand())) {
			sender.sendMessage(Colors.ERROR + "This command cannot be ran as a player!");
			return true;
		}
		
		if(sender instanceof ConsoleCommandSender && !(command.isConsoleCommand())) {
			sender.sendMessage(Colors.ERROR + "This command cannot be ran from the console!");
			return true;
		}
		
		if(command.getPermission() != null && !(command.getPermission().equals(Permission.NONE)) && !(Permission.has(command.getPermission(), sender))) {
			sender.sendMessage(command.getPermissionMessage());
			return true;
		}
		
		if((commandArgs.length < command.getMin()) || (commandArgs.length > command.getMax() && command.getMax() != -1)) {
			sender.sendMessage(Colors.ERROR + "Usage: /" + commandLabel + " " + command.getAliases()[0] + " " + command.getUsage());
			return true;
		}
		
		return command.onCommand(sender, cmd, commandLabel, args);
	}
	
	public List<String> onTabComplete(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if(args.length == 0) {
			ArrayList<String> list = new ArrayList<String>();
			
			for(BaseCommand command : CommandManager.getCommands()) {
				for(String alias : command.getAliases()) {
					list.add(alias);
				}
			}
			
			return list;		
		}
		
		ArrayList<BaseCommand> commands = CommandManager.getBestCommands(args[args.length - 1]);
		
		switch (commands.size()) {
		case 0:
			return new ArrayList<String>();
			
		case 1:
			return commands.get(0).onTabComplete(sender, cmd, commandLabel, args);

		default:
			ArrayList<String> list = new ArrayList<String>();
			
			for(BaseCommand command : commands) {
				for(String alias : command.getAliases()) {
					list.add(alias);
				}
			}
			
			return list;			
		}
	}
}
