package io.github.thatsmusic99.hitwshitpost.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class HealthAndHunger implements CommandExecutor {


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player player) {
            if (player.hasPermission("hitw.admin")) {
                if (args.length > 0) {
                    if (Bukkit.getPlayerExact(args[0]) != null && args.length > 1) {
                        if (args[1].matches("[^/d]")) {
                            Objects.requireNonNull(Bukkit.getPlayerExact(args[0])).setHealth(Double.parseDouble(args[1]));
                        }
                    }

                } else {
                    if (args[0].matches("[^/d]")) {
                        player.setHealth(Double.parseDouble(args[0]));
                    }
                }
            } else {
                sender.sendMessage(ChatColor.RED + "You do not have permission to use this command!");
                return false;
            }
        }
        return false;
    }
}
