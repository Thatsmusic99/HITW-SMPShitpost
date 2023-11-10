package io.github.thatsmusic99.hitwshitpost;

import io.github.thatsmusic99.hitwshitpost.listeners.WeatherListener;
import org.bukkit.plugin.java.JavaPlugin;

public class HITWShitpost extends JavaPlugin {

    private static HITWShitpost instance;

    @Override
    public void onEnable() {

        instance = this;

        getServer().getPluginManager().registerEvents(new WeatherListener(), this);
    }

    public static HITWShitpost get() {
        return instance;
    }
}
