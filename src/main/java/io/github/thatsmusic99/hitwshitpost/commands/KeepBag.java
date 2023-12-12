package io.github.thatsmusic99.hitwshitpost.commands;

import io.github.thatsmusic99.hitwshitpost.config.KeepInvList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class KeepBag implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player player) {
           if (player.hasPermission("hitw.admin")) {
               if (args.length > 0) {
                   switch (args[0]) {
                       case "add" -> {
                           if (args.length > 1) {
                               if (Bukkit.getPlayerExact(args[1]) != null) {
                                   Player target = Bukkit.getPlayerExact(args[1]);
                                   try {
                                       KeepInvList.addPlayer(target);
                                   } catch (IOException e) {
                                       throw new RuntimeException(e);
                                   }

                               } else {
                                   player.sendMessage(ChatColor.YELLOW + args[1] + ChatColor.RED + " not found!");
                                   return false;
                               }
                           } else {
                               player.sendMessage(ChatColor.RED + "You must include a player name!");
                               return false;
                           }
                       }
                       case "remove" -> {
                           if (args.length > 1) {
                               if (Bukkit.getPlayerExact(args[1]) != null) {
                                   Player target = Bukkit.getPlayerExact(args[1]);
                                   try {
                                       KeepInvList.removePlayer(target);
                                   } catch (IOException e) {
                                       throw new RuntimeException(e);
                                   }

                               } else {
                                   player.sendMessage(ChatColor.YELLOW + args[1] + ChatColor.RED + " not found!");
                                   return false;
                               }
                           } else {
                               player.sendMessage(ChatColor.RED + "You must include a player name!");
                               return false;
                           }
                       }
                   }
               }
           } else {
               player.sendMessage(ChatColor.RED + "You do not have permission to use this command!");
               return false;
           }
        }
        return false;
    }
}
