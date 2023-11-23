package io.github.thatsmusic99.hitwshitpost.traps;

import io.github.thatsmusic99.hitwshitpost.HITWShitpost;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class PlayerTraps {
    private static BukkitTask rainTrap = null;
    final static long length = 600L;

    public static void ArrowRain() {
        rainTrap = Bukkit.getScheduler().runTaskTimer(HITWShitpost.get(), () -> {
            for (Player player : Bukkit.getOnlinePlayers()) {
                projectileRain(player, EntityType.ARROW);
            }
        }, 20, 5);

        Bukkit.getScheduler().runTaskLater(HITWShitpost.get(), new Runnable() {
            @Override
            public void run() {
                rainTrap.cancel();
                rainTrap = null;
            }
        }, length);
    }

    public static void snowballRain() {
        rainTrap = Bukkit.getScheduler().runTaskTimer(HITWShitpost.get(), () -> {
            for (Player player : Bukkit.getOnlinePlayers()) {
                projectileRain(player, EntityType.SNOWBALL);
            }
        }, 20, 5);

        Bukkit.getScheduler().runTaskLater(HITWShitpost.get(), new Runnable() {
            @Override
            public void run() {
                rainTrap.cancel();
                rainTrap = null;
            }
        }, length);
    }

    // Rain part
    private static void projectileRain(final @NotNull Player player, EntityType projectile) {
        Random rand = new Random();

        int arrows = rand.nextInt(10) + 1;

        // Pick random coordinates
        for (int i = 0; i < arrows; i++) {

            double x = rand.nextInt(10) - 5 + player.getX();
            double y = player.getY() + 10;
            double z = rand.nextInt(10) - 5 + player.getZ();

            Entity entity = player.getWorld().spawnEntity(new Location(player.getWorld(), x, y, z), projectile);
            entity.setVelocity(new Vector(rand.nextDouble(2) - 1, -1, rand.nextDouble(2) - 1));

            if (projectile.equals(EntityType.ARROW)) {
                Bukkit.getScheduler().runTaskLater(HITWShitpost.get(), entity::remove, 60);
            }
        }
    }
}