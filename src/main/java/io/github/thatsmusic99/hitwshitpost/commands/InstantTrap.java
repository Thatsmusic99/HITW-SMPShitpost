package io.github.thatsmusic99.hitwshitpost.commands;

import io.github.thatsmusic99.hitwshitpost.HITWShitpost;
import io.github.thatsmusic99.hitwshitpost.listeners.PlayerTraps;
import io.github.thatsmusic99.hitwshitpost.lists.Traps;
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
                        case "SWIMMY_FISH" -> {
                            PlayerListener.trapReveal(Traps.SWIMMY_FISH.name());
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
                        case "REVENGE" -> {
                            PlayerListener.trapReveal(Traps.REVENGE.name());
                            MobTraps.spawnSlimes();
                        }
                        case "NOT_THE_BEES" -> {
                            PlayerListener.trapReveal(Traps.NOT_THE_BEES.name());
                            MobTraps.spawnBees();
                        }
                        case "SOLAR_ECLIPSE" -> {
                            PlayerListener.trapReveal(Traps.SOLAR_ECLIPSE.name());
                            PlayerListener.applyDarkness();
                        }

                        case "EVEN_CREEPIER_CRAWLIES" -> {
                            PlayerListener.trapReveal(Traps.EVEN_CREEPIER_CRAWLIES.name());
                            MobTraps.spawnCaveSpiders();
                        }

                        case "ARROWS" -> {
                            PlayerListener.trapReveal(Traps.ARROWS.name());
                            PlayerTraps.ArrowRain();
                        }

                        case "SNOWSTORM" -> {
                            PlayerListener.trapReveal(Traps.SNOWSTORM.name());
                            PlayerTraps.snowballRain();
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
                "EVEN_CREEPIER_CRAWLIES",
                "ARROWS",
                "SNOWSTORM"
        ));
        List<String> results = new ArrayList<>();
        if (args.length == 1) {
            StringUtil.copyPartialMatches(args[0], suggestion, results);
            return results;
        }
        return results;
    }
}
