package com.TeamNovus.Arenas;

import org.bukkit.command.CommandSender;

public enum Permission {
	COMMAND_TUTORIAL("command.tutorial"),
	COMMAND_ONLINE("command.online"),
	COMMAND_KD("command.kd"),
	COMMAND_TOP("command.top"),
	COMMAND_INFO("command.info"),
	COMMAND_INFO_OTHERS("command.info.others"), 
	COMMAND_SPEED("command.speed"),
	COMMAND_HEALTH("command.health"),
	COMMAND_MANA("command.mana"),
	COMMAND_HUNGER("command.hunger"),
	COMMAND_LEVEL("command.level"),
	COMMAND_POWERS("command.powers"),
	COMMAND_ABILITIES("command.abilities"),
	COMMAND_SPECS("command.specs"),
	COMMAND_EVOLVE("command.evolve"),
	COMMAND_DEVOLVE("command.devolve"),
	COMMAND_CLASSES("command.classes"),
	COMMAND_VERBOSE("command.verbose"),
	COMMAND_GUI("command.gui"),
	COMMAND_RESET("command.reset"),
	COMMAND_MODMANA("command.modmana"),
	COMMAND_MODEXP("command.modexp"),
	COMMAND_MODCLASS("command.modclass"),
	COMMAND_MODRESET("command.modreset"),
	NONE("");
	
	private String node;
	
	private Permission(String node) {
		this.node = node;
	}
	
	public String getNode() {
		return node;
	}
	
	private static String getPermission(Permission permission) {
		return "arenas." + permission.getNode();
	}
	
	public static Boolean has(Permission permission, CommandSender target) {
		return target.hasPermission(getPermission(permission));
	}
}
