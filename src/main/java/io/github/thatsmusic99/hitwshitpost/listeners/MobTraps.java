package io.github.thatsmusic99.hitwshitpost.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Spider;

import java.util.Random;

public class MobTraps {
    static int zombieAmount = 5;
    static int spiderAmount = 3;
    static int fishAmount = 4;
    static int pillagerAmount = 4;

    public static void spawnZombies() {
        Random rand = new Random();

        for (Player p : Bukkit.getOnlinePlayers()) {
            for (int i = 0; i < zombieAmount; i++) {
                double x = rand.nextInt(10) - 5 + p.getX();
                double y = p.getY();
                double z = rand.nextInt(10) - 5 + p.getZ();

                p.getWorld().spawnEntity(new Location(p.getWorld(), x, y, z), EntityType.ZOMBIE);
            }
        }
    }

    public static void spawnSpiders() {
        Random rand = new Random();

        for (Player p : Bukkit.getOnlinePlayers()) {
            for (int i = 0; i < spiderAmount; i++) {
                double x = rand.nextInt(10) - 5 + p.getX();
                double y = p.getY();
                double z = rand.nextInt(10) - 5 + p.getZ();

                Spider entity = (Spider) p.getWorld().spawnEntity(new Location(p.getWorld(), x, y, z), EntityType.SPIDER);
                entity.setTarget(p);
            }
        }
    }

    public static void spawnFish() {
        Random rand = new Random();

        for (Player p : Bukkit.getOnlinePlayers()) {
            for (int i = 0; i < fishAmount; i++) {
                double x = rand.nextInt(10) - 5 + p.getX();
                double y = p.getY();
                double z = rand.nextInt(10) - 5 + p.getZ();

                p.getWorld().spawnEntity(new Location(p.getWorld(), x, y, z), EntityType.GUARDIAN);
            }
        }
    }

    public static void spawnPillagers() {
        Random rand = new Random();

        for (Player p : Bukkit.getOnlinePlayers()) {
            for (int i = 0; i < pillagerAmount; i++) {
                double x = rand.nextInt(10) - 5 + p.getX();
                double y = p.getY();
                double z = rand.nextInt(10) - 5 + p.getZ();

                p.getWorld().spawnEntity(new Location(p.getWorld(), x, y, z), EntityType.PILLAGER);
            }
        }
    }
}
