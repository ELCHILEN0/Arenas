package com.TeamNovus.Arenas.Commands;

import java.util.ArrayList;
import java.util.LinkedList;

public class CommandManager {
	private static LinkedList<BaseCommand> commands = new LinkedList<BaseCommand>();
	
	public static void register(BaseCommand command) {
		commands.add(command);
	}
	
	public static void unregister(BaseCommand command) {
		commands.remove(command);
	}
		
	public static void unregisterAll() {
		commands.clear();
	}

	public static LinkedList<BaseCommand> getCommands() {
		return commands;
	}
	
	public static BaseCommand getCommand(String label) {
		for (BaseCommand command : commands) {
			for (String alias : command.getAliases()) {
				if(label.equalsIgnoreCase(alias)) {
					return command;
				}
			}
		}
		
		return null;
	}
	
	public static ArrayList<BaseCommand> getBestCommands(String label) {
		ArrayList<BaseCommand> list = new ArrayList<BaseCommand>();
		
		for (BaseCommand command : commands) {
			for (String alias : command.getAliases()) {
				if(alias.toLowerCase().startsWith(label.toLowerCase())) {
					list.add(command);
				}
			}
		}
		
		return list;
	}
}
