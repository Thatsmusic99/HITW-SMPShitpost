package io.github.thatsmusic99.hitwshitpost;

import io.github.thatsmusic99.hitwshitpost.commands.InstantTrap;
import io.github.thatsmusic99.hitwshitpost.enums.Traps;
import io.github.thatsmusic99.hitwshitpost.listeners.PlayerListener;
import io.github.thatsmusic99.hitwshitpost.listeners.WeatherListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.time.Instant;
import java.util.Objects;
import java.util.Random;

public class HITWShitpost extends JavaPlugin {

    private static HITWShitpost instance;

    @Override
    public void onEnable() {

        instance = this;

        getServer().getPluginManager().registerEvents(new WeatherListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerListener(), this);

        Objects.requireNonNull(getCommand("instanttrap")).setExecutor(new InstantTrap());

        Bukkit.getScheduler().runTaskTimer(this, HITWShitpost::PickTrap, (20*60), (20*60)*5);
    }

    public static void PickTrap() {
        Random random = new Random();
        int index = random.nextInt(Traps.values().length);
        Traps trap = Traps.values()[index];
        PlayerListener.trapReveal(trap.name());

        switch (trap) {
            case LEG_DAY -> PlayerListener.applySlow();
            case SPRINGY_SHOES -> PlayerListener.applySpringy();
            case LOW_GRAVITY -> PlayerListener.applyGravity();
            case SUPER_SPEED -> PlayerListener.applySpeed();
        }
    }

    public static HITWShitpost get() {
        return instance;
    }
}
