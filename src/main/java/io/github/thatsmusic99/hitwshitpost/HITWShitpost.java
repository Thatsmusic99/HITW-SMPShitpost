package io.github.thatsmusic99.hitwshitpost;

import io.github.thatsmusic99.hitwshitpost.commands.InstantTrap;
import io.github.thatsmusic99.hitwshitpost.commands.KeepBag;
import io.github.thatsmusic99.hitwshitpost.config.Config;
import io.github.thatsmusic99.hitwshitpost.items.HotPotato;
import io.github.thatsmusic99.hitwshitpost.lists.Traps;
import io.github.thatsmusic99.hitwshitpost.hooks.BossBarManager;
import io.github.thatsmusic99.hitwshitpost.listeners.*;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.io.ObjectStreamException;
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
        getServer().getPluginManager().registerEvents(new CustomItemEvents(), this);

        // Custom Objects
        new HotPotato();

        // Commands
        Objects.requireNonNull(getCommand("trap")).setExecutor(new InstantTrap());
        Objects.requireNonNull(getCommand("trap")).setTabCompleter(new InstantTrap());

        Objects.requireNonNull(getCommand("keepbag")).setExecutor(new KeepBag());
        Objects.requireNonNull(getCommand("keepbag")).setTabCompleter(new KeepBag());

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

        trap.getTrap().run();
    }

    public static HITWShitpost get() {
        return instance;
    }
}
