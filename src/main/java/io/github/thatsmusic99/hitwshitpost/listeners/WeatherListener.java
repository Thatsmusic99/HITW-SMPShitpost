package io.github.thatsmusic99.hitwshitpost.listeners;

import io.github.thatsmusic99.hitwshitpost.HITWShitpost;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class WeatherListener implements Listener {

    private BukkitTask weatherTask = null;

    @EventHandler
    public void onWeather(WeatherChangeEvent event) {

        // If it's not storming, no
        if (!event.toWeatherState()) {
            if (weatherTask == null) return;
            weatherTask.cancel();
            return;
        }

        // Start the raining >:)
        weatherTask = Bukkit.getScheduler().runTaskTimer(HITWShitpost.get(), () -> {

            for (Player player : Bukkit.getOnlinePlayers()) {
                stormArrows(player);
            }
        }, 20, 5);
    }

    private void stormArrows(final @NotNull Player player) {

        Random rand = new Random();

        int arrows = rand.nextInt(10) + 1;

        // Pick random coordinates
        for (int i = 0; i < arrows; i++) {

            double x = rand.nextInt(10) - 5 + player.getX();
            double y = player.getY() + 10;
            double z = rand.nextInt(10) - 5 + player.getZ();

            Entity entity = player.getWorld().spawnEntity(new Location(player.getWorld(), x, y, z), EntityType.ARROW);
            entity.setVelocity(new Vector(rand.nextDouble(2) - 1, -1, rand.nextDouble(2) - 1));

            Bukkit.getScheduler().runTaskLater(HITWShitpost.get(), entity::remove, 60);
        }

    }

}
