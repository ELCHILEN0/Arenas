package com.TeamNovus.Arenas.Commands;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import com.TeamNovus.Arenas.Permission;

public abstract class BaseCommand {
	private String[] aliases;
	private Permission permission = Permission.NONE;
	
	private String description = "";
	private String usage = "";
	private String permissionMessage = ChatColor.RED + "You do not have permission for this command!";
	
	private boolean player = true;
	private boolean console = true;
	
	private int min = 0;
	private int max = -1;
	
	private boolean hidden = false;	
	
	public BaseCommand(String... aliases) throws InstantiationException {
		if(aliases.length == 0) {
			throw new InstantiationException("The command aliases must be > 0");
		}
		
		this.aliases = aliases;
	}
	
	protected abstract boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args);
	protected abstract ArrayList<String> onTabComplete(CommandSender sender, Command cmd, String commandLabel, String[] args);

	public String getLabel() {
		return aliases[0];
	}
	
	public String[] getAliases() {
		return aliases;
	}
	
	public Permission getPermission() {
		return permission;
	}
	
	public void setPermission(Permission permission) {
		this.permission = permission;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getUsage() {
		return usage;
	}
	
	public void setUsage(String usage) {
		this.usage = usage;
	}
	
	public String getPermissionMessage() {
		return permissionMessage;
	}
	
	public void setPermissionMessage(String permissionMessage) {
		this.permissionMessage = permissionMessage;
	}
	
	public boolean isPlayerCommand() {
		return player;
	}
	
	public void setPlayerCommand(boolean player) {
		this.player = player;
	}
	
	public boolean isConsoleCommand() {
		return console;
	}
	
	public void setConsoleCommand(boolean console) {
		this.console = console;
	}
	
	public int getMin() {
		return min;
	}
	
	public void setMin(int min) {
		this.min = min;
	}
	
	public int getMax() {
		return max;
	}
	
	public void setMax(int max) {
		this.max = max;
	}
	
	public boolean isHidden() {
		return hidden;
	}
	
	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}
	
}
