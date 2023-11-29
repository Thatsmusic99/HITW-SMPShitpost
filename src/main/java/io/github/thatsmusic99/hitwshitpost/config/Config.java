package io.github.thatsmusic99.hitwshitpost.config;

import io.github.thatsmusic99.hitwshitpost.HITWShitpost;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class Config {
    // Create config file
    public static File configFile = new File(HITWShitpost.get().getDataFolder(), "config.yml");
    public static FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);

    // Default config settings
    public static void setDefaults() throws IOException {
        // Timer
        config.addDefault("timer.interval", 300);

        // Trap Chances
        config.addDefault("traps.chances.legday", 20);
        config.addDefault("traps.chances.superspeed", 20);
        config.addDefault("traps.chances.springyshoes", 20);
        config.addDefault("traps.chances.lowgravity", 20);
        config.addDefault("traps.chances.solareclipse", 15);

        config.addDefault("traps.chances.creepycrawlies", 10);
        config.addDefault("traps.chances.evencreepiercrawlies", 10);
        config.addDefault("traps.chances.swimmyfish", 10);
        config.addDefault("traps.chances.pillagers", 10);
        config.addDefault("traps.chances.jackfrost", 5);
        config.addDefault("traps.chances.feelinghot", 5);
        config.addDefault("traps.chances.revenge", 8);
        config.addDefault("traps.chances.notthebees", 5);
        config.addDefault("traps.chances.theskeletonappears", 3);
        config.addDefault("traps.chances.awman", 3);
        config.addDefault("traps.chances.exhaustedarms", 0);
        config.addDefault("traps.chances.excavator", 3);

        config.addDefault("traps.chances.arrows", 4);
        config.addDefault("traps.chances.snowstorm", 4);

        // Spawn Amount
        config.addDefault("traps.spawn.creepycrawlies", 2);
        config.addDefault("traps.spawn.evencreepiercrawlies", 3);
        config.addDefault("traps.spawn.swimmyfish", 3);
        config.addDefault("traps.spawn.pillagers", 4);
        config.addDefault("traps.spawn.jackfrost", 4);
        config.addDefault("traps.spawn.feelinghot", 2);
        config.addDefault("traps.spawn.revenge", 4);
        config.addDefault("traps.spawn.notthebees", 5);
        config.addDefault("traps.spawn.theskeletonappears", 1);
        config.addDefault("traps.spawn.awman", 1);

        // Effect Length
        config.addDefault("traps.length.legday", 40);
        config.addDefault("traps.length.superspeed", 30);
        config.addDefault("traps.length.springyshoes", 30);
        config.addDefault("traps.length.lowgravity", 40);
        config.addDefault("traps.length.solareclipse", 30);
        config.addDefault("traps.length.exhaustedarms", 20);
        config.addDefault("traps.length.excavator", 20);

        // Rain Length
        config.addDefault("traps.rainlength.arrows", 600);
        config.addDefault("traps.rainlength.snowstorm", 600);

        //Fun Extras
        config.addDefault("mobs.parrotseatcookies", true);

        save();
    }

    // Functions
    public static void save() throws IOException {
        config.options().copyDefaults(true);
        config.save(configFile);
    }

    public static void reload() throws IOException {
        if (configFile == null) {
            configFile = new File(HITWShitpost.get().getDataFolder(), "config.yml");
        }
        config = YamlConfiguration.loadConfiguration(configFile);
        setDefaults();
        save();
    }

}
