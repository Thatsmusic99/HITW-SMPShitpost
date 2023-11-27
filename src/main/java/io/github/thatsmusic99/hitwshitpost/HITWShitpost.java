package io.github.thatsmusic99.hitwshitpost;

import io.github.thatsmusic99.hitwshitpost.commands.InstantTrap;
import io.github.thatsmusic99.hitwshitpost.config.Config;
import io.github.thatsmusic99.hitwshitpost.lists.Traps;
import io.github.thatsmusic99.hitwshitpost.hooks.BossBarManager;
import io.github.thatsmusic99.hitwshitpost.listeners.*;
import io.github.thatsmusic99.hitwshitpost.traps.MobTraps;
import io.github.thatsmusic99.hitwshitpost.traps.PlayerTraps;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.util.Objects;

public class HITWShitpost extends JavaPlugin {

    private static HITWShitpost instance;

    @Override
    public void onEnable() {

        instance = this;

        // Events
        //getServer().getPluginManager().registerEvents(new WeatherListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerListener(), this);
        getServer().getPluginManager().registerEvents(new EntityListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerDeathListener(), this);

        // Commands
        Objects.requireNonNull(getCommand("trap")).setExecutor(new InstantTrap());
        Objects.requireNonNull(getCommand("trap")).setTabCompleter(new InstantTrap());

        // BossBar
        final BossBarManager bossBarManager = new BossBarManager();
        Bukkit.getScheduler().runTaskTimer(this, bossBarManager::tick, 20, 20);

        // Config
        try {
            Config.setDefaults();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void PickTrap() {
        Traps trap = Traps.pickTrap();
        PlayerListener.trapReveal(trap.name());

        switch (trap) {
            case LEG_DAY -> PlayerListener.applySlow();
            case SPRINGY_SHOES -> PlayerListener.applySpringy();
            case LOW_GRAVITY -> PlayerListener.applyGravity();
            case SUPER_SPEED -> PlayerListener.applySpeed();
            case SO_LONELY -> MobTraps.spawnZombies();
            case CREEPY_CRAWLIES -> MobTraps.spawnSpiders();
            case SWIMMY_FISH -> MobTraps.spawnFish();
            case PILLAGERS -> MobTraps.spawnPillagers();
            case JACK_FROST -> MobTraps.spawnJackFrost();
            case THE_SKELETON_APPEARS -> MobTraps.spawnSkeleton();
            case AW_MAN -> MobTraps.spawnCreeper();
            case FEELING_HOT -> MobTraps.spawnBlaze();
            case REVENGE -> MobTraps.spawnSlimes();
            case NOT_THE_BEES -> MobTraps.spawnBees();
            case SOLAR_ECLIPSE -> PlayerListener.applyDarkness();
            case EVEN_CREEPIER_CRAWLIES -> MobTraps.spawnCaveSpiders();
            case ARROWS -> PlayerTraps.ArrowRain();
            case SNOWSTORM -> PlayerTraps.snowballRain();
            case EXHAUSTED_ARMS -> PlayerListener.applyMineFatigue();
        }
    }

    public static HITWShitpost get() {
        return instance;
    }
}
