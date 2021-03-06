package com.gildorymrp.gildorymclasses;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetLevelCommand implements CommandExecutor {
	private GildorymClasses plugin;

	public SetLevelCommand(GildorymClasses plugin) {
		this.plugin = plugin;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (sender.hasPermission("basicchar.command.setlevel")) {
			if (args.length >= 2)
				try {
					if (Bukkit.getServer().getPlayer(args[0]) != null) {
						this.plugin.levels
								.put(Bukkit.getServer().getPlayer(args[0])
										.getName(), Integer.valueOf(Integer
										.parseInt(args[1])));
						sender.sendMessage(ChatColor.GREEN
								+ Bukkit.getServer().getPlayer(args[0])
										.getName() + "'s level set to "
								+ args[1]);
						Bukkit.getServer().getPlayer(args[0])
								.setLevel(Integer.parseInt(args[1]));
					} else {
						sender.sendMessage(ChatColor.RED
								+ "That player is not online!");
					}
				} catch (NumberFormatException exception) {
					sender.sendMessage(ChatColor.RED
							+ "Level must be a number!");
				}
			else if (args.length >= 1)
				try {
					this.plugin.levels.put(sender.getName(),
							Integer.valueOf(Integer.parseInt(args[0])));
					sender.sendMessage(ChatColor.GREEN + "Level set to "
							+ args[0]);
					((Player) sender).setLevel(Integer.parseInt(args[0]));
				} catch (NumberFormatException exception) {
					sender.sendMessage(ChatColor.RED
							+ "Level must be a number!");
				}
			else
				sender.sendMessage(ChatColor.RED
						+ "You need to specify a level!");
		} else {
			sender.sendMessage(ChatColor.RED + "You do not have permission!");
		}
		return true;
	}
}