package io.github.thatsmusic99.hitwshitpost.config;

import io.github.thatsmusic99.hitwshitpost.HITWShitpost;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class KeepInvList {
    public static File keepInvFile = new File(HITWShitpost.get().getDataFolder(), "keepInvList.yml");
    public static FileConfiguration keepInv = YamlConfiguration.loadConfiguration(keepInvFile);

    public static void save() throws IOException {
        keepInv.set("players", keepInvPlayers);
        keepInv.save(keepInvFile);
    }

    public static List<String> keepInvPlayers = keepInv.getStringList("players");

    public static boolean getPlayer(Player target) {
        return keepInvPlayers.contains(target.getUniqueId().toString());
    }

    public static void addPlayer(Player target) throws IOException {
        if (!getPlayer(target)) {
            keepInvPlayers.add(target.getUniqueId().toString());
            save();
        }
    }

    public static void removePlayer(Player target) throws IOException {
        if (getPlayer(target)) {
            keepInvPlayers.remove(target.getUniqueId().toString());
            save();
        }
    }

}
