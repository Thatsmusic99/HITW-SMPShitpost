package io.github.thatsmusic99.hitwshitpost.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.*;

import java.util.Objects;
import java.util.Random;
import java.util.function.BiConsumer;

public class MobTraps {
    static int zombieAmount = 3;
    static int spiderAmount = 2;
    static int fishAmount = 3;
    static int pillagerAmount = 4;
    static int jackFrostAmount = 4;

    public static void spawnZombies() {

        spawnMob(zombieAmount, Zombie.class, (player, zombie) -> {});
    }

    public static void spawnSpiders() {

        spawnMob(spiderAmount, Spider.class, (player, spider) -> spider.setTarget(player));
    }

    public static void spawnFish() {

        spawnMob(fishAmount, Guardian.class, (player, guardian) -> {});
    }

    public static void spawnPillagers() {

        spawnMob(pillagerAmount, Pillager.class, (player, pillager) -> pillager.setPatrolLeader(false));
    }

    public static void spawnJackFrost() {

        spawnMob(jackFrostAmount, Snowman.class, (player, snowman) -> {});
    }

    public static void spawnCreeper() {

        spawnMob(1, Creeper.class, (player, creeper) -> {
            creeper.setTarget(player);
            Objects.requireNonNull(creeper.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED)).setBaseValue(0.4);
            Objects.requireNonNull(creeper.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(1);
            creeper.setHealth(1);
        });
    }

    public static void spawnSkeleton() {

        spawnMob(1, Skeleton.class, (player, skeleton) -> {
            Objects.requireNonNull(skeleton.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(10);
            Objects.requireNonNull(skeleton.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED)).setBaseValue(2);
            skeleton.setHealth(10);
            skeleton.setTarget(player);
        });
    }

    public static <T extends Entity> void spawnMob(int amount, Class<T> entityClass, BiConsumer<Player, T> postSpawn) {
        Random rand = new Random();

        for (Player p : Bukkit.getOnlinePlayers()) {
            for (int i = 0; i < amount; i++) {
                double x = rand.nextInt(10) - 5 + p.getX();
                double y = p.getY();
                double z = rand.nextInt(10) - 5 + p.getZ();

                T entity = p.getWorld().spawn(new Location(p.getWorld(), x, y, z), entityClass);
                postSpawn.accept(p, entity);
            }
        }
    }
}
