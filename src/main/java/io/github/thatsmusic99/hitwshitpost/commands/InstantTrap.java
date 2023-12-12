package io.github.thatsmusic99.hitwshitpost.commands;

import io.github.thatsmusic99.hitwshitpost.HITWShitpost;
import io.github.thatsmusic99.hitwshitpost.config.Config;
import io.github.thatsmusic99.hitwshitpost.lists.Traps;
import io.github.thatsmusic99.hitwshitpost.listeners.PlayerListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.*;

public class InstantTrap implements TabExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player player) {

            if (player.hasPermission("hitw.admin")) {
                if (args.length > 0) {
                    try {
                        Traps trap = Traps.valueOf(args[0]);
                        PlayerListener.trapReveal(trap.name());
                        trap.getTrap().run();
                    } catch (IllegalArgumentException ex) {
                        if (args[0].equalsIgnoreCase("reload")) {
                            try {
                                Config.reload();
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                            sender.sendMessage(ChatColor.GREEN + "Config reloaded!");
                            return true;

                        } else {
                            sender.sendMessage(ChatColor.RED + "You may have typo'd the trap name. Try again!");
                            return false;
                        }
                    }
                } else {
                    HITWShitpost.PickTrap();
                }
                for (Player p: Bukkit.getOnlinePlayers()) {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&3[&eTRAP ALERT&3]&c " + sender.getName() + "&e just activated a trap!"));
                    return true;
                }
            } else {
                sender.sendMessage(ChatColor.RED + "You do not have permission to use this command!");
                return false;
            }
        }
        return false;
    }
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        List<String> suggestion = new ArrayList<>(Arrays.asList(
                "LEG_DAY",
                "SUPER_SPEED",
                "SPRINGY_SHOES",
                "LOW_GRAVITY",
                "SO_LONELY",
                "CREEPY_CRAWLIES",
                "SWIMMY_FISH",
                "PILLAGERS",
                "JACK_FROST",
                "THE_SKELETON_APPEARS",
                "AW_MAN",
                "FEELING_HOT",
                "REVENGE",
                "NOT_THE_BEES",
                "SOLAR_ECLIPSE",
                "EXHAUSTED_ARMS",
                "EVEN_CREEPIER_CRAWLIES",
                "ARROWS",
                "SNOWSTORM",
                "FALLEN_CHAMPION",
                "ULTRA_BOUNCY",
                "NO_SLEEP",
                "reload"
        ));
        List<String> results = new ArrayList<>();
        if (args.length == 1) {
            StringUtil.copyPartialMatches(args[0], suggestion, results);
            return results;
        }
        return results;
    }
}
