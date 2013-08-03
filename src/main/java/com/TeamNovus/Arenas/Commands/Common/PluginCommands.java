package com.TeamNovus.Arenas.Commands.Common;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import com.TeamNovus.Arenas.Arenas;
import com.TeamNovus.Arenas.Permission;
import com.TeamNovus.Arenas.Commands.BaseCommand;
import com.TeamNovus.Arenas.Models.Arena.Arena;
import com.TeamNovus.Arenas.Models.Arena.Team;
import com.TeamNovus.Arenas.Util.StringUtil;

public class PluginCommands {
	
	@BaseCommand(aliases = { "create" }, desc = "", permission = Permission.NONE, min = 1)
	public void onCreateCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {		
		if(Arenas.i.getArenaExact(args[0]) != null) {
			sender.sendMessage(ChatColor.RED + "This arena already exists!");
			return;
		}
		
		Arena arena = new Arena(args[0]);
		Arenas.i.attach(arena);
		
		sender.sendMessage(ChatColor.GREEN + "Created arena " + ChatColor.YELLOW + arena.getName() + ChatColor.GREEN + "!");
	}
	
	@BaseCommand(aliases = { "addteam" }, desc = "", permission = Permission.NONE, min = 4)
	public void onAddTeamCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {		
		Arena arena = Arenas.i.getArenaExact(args[0]);
		
		if(arena == null) {
			sender.sendMessage(ChatColor.RED + "No arena was found by this name!");
			return;
		}
		
		Team team = new Team(args[1], ChatColor.getByChar(args[2]), StringUtil.isInteger(args[3]) ? Integer.valueOf(args[3]) : 0);
	}
	
	

}
