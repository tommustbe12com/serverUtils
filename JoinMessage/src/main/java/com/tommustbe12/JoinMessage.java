package com.tommustbe12;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class JoinMessage extends JavaPlugin implements Listener {

    private boolean joinMessageEnabled;
    private String customMessage;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        joinMessageEnabled = getConfig().getBoolean("join-message-enabled", false);
        customMessage = getConfig().getString("custom-message", "&aWelcome &b{player} &ato the server!");

        getLogger().info("JoinMessage plugin enabled!");
        getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        getLogger().info("JoinMessage plugin disabled!");
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (joinMessageEnabled) {
            Player player = event.getPlayer();
            String parsedMessage = customMessage.replace("{player}", player.getName());
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', parsedMessage));
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("setjoinmessage")) {
            if (args.length != 1) {
                sender.sendMessage(ChatColor.RED + "Usage: /setjoinmessage <on|off>");
                return true;
            }

            if (args[0].equalsIgnoreCase("on")) {
                joinMessageEnabled = true;
                getConfig().set("join-message-enabled", true);
                saveConfig();
                sender.sendMessage(ChatColor.GREEN + "Join message has been enabled.");
            } else if (args[0].equalsIgnoreCase("off")) {
                joinMessageEnabled = false;
                getConfig().set("join-message-enabled", false);
                saveConfig();
                sender.sendMessage(ChatColor.RED + "Join message has been disabled.");
            } else {
                sender.sendMessage(ChatColor.RED + "Invalid argument. Use /setjoinmessage <on|off>");
            }
            return true;
        }

        if (command.getName().equalsIgnoreCase("setjoinmessagecontent")) {
            if (args.length == 0) {
                sender.sendMessage(ChatColor.RED + "Usage: /setjoinmessagecontent <message>");
                return true;
            }

            StringBuilder sb = new StringBuilder();
            for (String arg : args) {
                sb.append(arg).append(" ");
            }

            customMessage = sb.toString().trim();
            getConfig().set("custom-message", customMessage);
            saveConfig();
            sender.sendMessage(ChatColor.GREEN + "Join message set to: " + ChatColor.translateAlternateColorCodes('&', customMessage));
            return true;
        }
        
        if (command.getName().equalsIgnoreCase("youtube")) {
        	if (args.length > 0) {
        		sender.sendMessage(ChatColor.RED + "This command requires NO arguments, you dummy.");
        		return true;
        	} else {
        		sender.sendMessage(ChatColor.GOLD + "TomMustBe12's " + ChatColor.RED + "YouTube: " + ChatColor.AQUA + "https://youtube.com/@TomMustBe12");
        		return true;
        	}
        }
        
        if (command.getName().equalsIgnoreCase("rysterio")) {
        	if (args.length > 0) {
        		sender.sendMessage(ChatColor.RED + "This command requires NO arguments, you dummy.");
        		return true;
        	} else {
        		sender.sendMessage(ChatColor.GREEN + "Rysterio's YouTube: " + ChatColor.AQUA + "https://youtube.com/@Rysterio");
        		return true;
        	}
        }
        
        if (command.getName().equalsIgnoreCase("tellall")) {
            if (args.length == 0) {
                sender.sendMessage(ChatColor.RED + "Usage: /tellall <message>");
                return true;
            }

            StringBuilder sb = new StringBuilder();
            for (String arg : args) {
                sb.append(arg).append(" ");
            }

            String message = ChatColor.translateAlternateColorCodes('&', sb.toString().trim());

            for (Player player : Bukkit.getOnlinePlayers()) {
                player.sendMessage(message);
            }

            sender.sendMessage(ChatColor.GREEN + "Sent message to all players: " + message);
            return true;
        }
        
        if (command.getName().equalsIgnoreCase("tw")) {
            if (!sender.isOp()) {
                sender.sendMessage(ChatColor.RED + "You don't have permission to use this command.");
                return true;
            }

            if (sender instanceof Player player) {
                World world = player.getWorld();
                world.setTime(1000); // Set time to morning
                world.setStorm(false);
                world.setThundering(false);
                sender.sendMessage(ChatColor.GREEN + "Time set to day and weather cleared in your current world.");
                return true;
            } else {
                sender.sendMessage(ChatColor.RED + "This command can only be used by a player.");
            }

            return true;
        }
        
        if (command.getName().equalsIgnoreCase("serverutils")) {
        	sender.sendMessage(ChatColor.YELLOW + "ServerUtils Plugin:");
        	sender.sendMessage(ChatColor.GREEN + "/setjoinmessage <on|off> " + ChatColor.WHITE + "This command lets you enable or disable the join message you set.");
        	sender.sendMessage(ChatColor.GREEN + "/setjoinmessagecontent <message> " + ChatColor.WHITE + "This command sets the join message with color-enabled text. To insert player's name, put in {player}. Default message is" + ChatColor.GREEN + "Welcome " + ChatColor.AQUA + "{player}" + ChatColor.GREEN + "to the server!" );
        	sender.sendMessage(ChatColor.GREEN + "/youtube " + ChatColor.WHITE + "Shows TomMustBe12's YouTube channel link.");
        	sender.sendMessage(ChatColor.GREEN + "/rysterio " + ChatColor.WHITE + "Shows Rysterio's YouTube channel link.");
        	sender.sendMessage(ChatColor.GREEN + "/tw " + ChatColor.WHITE + "Sets time to day and weather to clear in the current world.");
        	return true;
        }
        
        return false;
    }
}
