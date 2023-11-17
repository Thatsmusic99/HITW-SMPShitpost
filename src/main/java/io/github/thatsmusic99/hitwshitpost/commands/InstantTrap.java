package io.github.thatsmusic99.hitwshitpost.commands;

import io.github.thatsmusic99.hitwshitpost.HITWShitpost;
import io.github.thatsmusic99.hitwshitpost.enums.Traps;
import io.github.thatsmusic99.hitwshitpost.listeners.MobTraps;
import io.github.thatsmusic99.hitwshitpost.listeners.PlayerListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class InstantTrap implements TabExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player player) {

            if (player.hasPermission("hitw.admin")) {
                if (args.length > 0) {
                    switch (args[0]) {
                        case "THE_SKELETON_APPEARS" -> {
                            PlayerListener.trapReveal(Traps.THE_SKELETON_APPEARS.name());
                            MobTraps.spawnSkeleton();
                        }
                        case "SUPER_SPEED" -> {
                            PlayerListener.trapReveal(Traps.SUPER_SPEED.name());
                            PlayerListener.applySpeed();
                        }
                        case "LEG_DAY" -> {
                            PlayerListener.trapReveal(Traps.LEG_DAY.name());
                            PlayerListener.applySlow();
                        }
                        case "SPRINGY_SHOES" -> {
                            PlayerListener.trapReveal(Traps.SPRINGY_SHOES.name());
                            PlayerListener.applySpringy();
                        }
                        case "LOW_GRAVITY" -> {
                            PlayerListener.trapReveal(Traps.LOW_GRAVITY.name());
                            PlayerListener.applyGravity();
                        }
                        case "SO_LONELY" -> {
                            PlayerListener.trapReveal(Traps.SO_LONELY.name());
                            MobTraps.spawnZombies();
                        }
                        case "GUARDIANS" -> {
                            PlayerListener.trapReveal(Traps.GUARDIANS.name());
                            MobTraps.spawnFish();
                        }
                        case "PILLAGERS" -> {
                            PlayerListener.trapReveal(Traps.PILLAGERS.name());
                            MobTraps.spawnPillagers();
                        }
                        case "JACK_FROST" -> {
                            PlayerListener.trapReveal(Traps.JACK_FROST.name());
                            MobTraps.spawnJackFrost();
                        }
                        case "CREEPY_CRAWLIES" -> {
                            PlayerListener.trapReveal(Traps.CREEPY_CRAWLIES.name());
                            MobTraps.spawnSpiders();
                        }
                        case "AW_MAN" -> {
                            PlayerListener.trapReveal(Traps.AW_MAN.name());
                            MobTraps.spawnCreeper();
                        }
                        case "FEELING_HOT" -> {
                            PlayerListener.trapReveal(Traps.FEELING_HOT.name());
                            MobTraps.spawnBlaze();
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
                "GUARDIANS",
                "PILLAGERS",
                "JACK_FROST",
                "THE_SKELETON_APPEARS",
                "AW_MAN",
                "FEELING_HOT"
        ));
        List<String> results = new ArrayList<>();
        if (args.length == 1) {
            StringUtil.copyPartialMatches(args[0], suggestion, results);
            return results;
        }
        return results;
    }
}
