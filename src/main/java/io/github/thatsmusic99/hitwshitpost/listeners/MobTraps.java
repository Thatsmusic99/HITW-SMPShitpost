package io.github.thatsmusic99.hitwshitpost.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.*;

import java.util.Objects;
import java.util.Random;

public class MobTraps {
    static int zombieAmount = 3;
    static int spiderAmount = 2;
    static int fishAmount = 3;
    static int pillagerAmount = 4;
    static int jackFrostAmount = 4;

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

                Pillager entity = (Pillager) p.getWorld().spawnEntity(new Location(p.getWorld(), x, y, z), EntityType.PILLAGER);
                entity.setPatrolLeader(false); // We don't need raids fr
            }
        }
    }

    public static void spawnJackFrost() {
        Random rand = new Random();

        for (Player p : Bukkit.getOnlinePlayers()) {
            for (int i = 0; i < jackFrostAmount; i++) {
                double x = rand.nextInt(10) - 5 + p.getX();
                double y = p.getY();
                double z = rand.nextInt(10) - 5 + p.getZ();

                Snowman entity = (Snowman) p.getWorld().spawnEntity(new Location(p.getWorld(), x, y, z), EntityType.SNOWMAN);
            }
        }
    }

    public static void spawnCreeper() {
        Random rand = new Random();

        for (Player p : Bukkit.getOnlinePlayers()) {
            for (int i = 0; i < 1; i++) {
                double x = rand.nextInt(10) - 5 + p.getX();
                double y = p.getY();
                double z = rand.nextInt(10) - 5 + p.getZ();

                Creeper entity = (Creeper) p.getWorld().spawnEntity(new Location(p.getWorld(), x, y, z), EntityType.CREEPER);
                entity.setTarget(p);
                Objects.requireNonNull(entity.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED)).setBaseValue(0.4);
                Objects.requireNonNull(entity.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(1);
                entity.setHealth(1);
            }
        }
    }

    public static void spawnSkeleton() {
        Random rand = new Random();

        for (Player p : Bukkit.getOnlinePlayers()) {
            double x = rand.nextInt(10) - 5 + p.getX();
            double y = p.getY();
            double z = rand.nextInt(10) - 5 + p.getZ();

            Skeleton entity = (Skeleton) p.getWorld().spawnEntity(new Location(p.getWorld(), x, y, z), EntityType.SKELETON);
            Objects.requireNonNull(entity.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(10);
            Objects.requireNonNull(entity.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED)).setBaseValue(2);
            entity.setHealth(10);
            entity.setTarget(p);
        }
    }
}
