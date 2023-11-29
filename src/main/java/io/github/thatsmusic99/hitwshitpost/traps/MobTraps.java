package io.github.thatsmusic99.hitwshitpost.traps;

import io.github.thatsmusic99.hitwshitpost.HITWShitpost;
import io.github.thatsmusic99.hitwshitpost.config.Config;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.Banner;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;
import java.util.Random;
import java.util.function.BiConsumer;

public class MobTraps {

    public static void spawnZombies() {

        spawnMob(Config.config.getInt("traps.spawn.solonely"), Zombie.class, (player, zombie) -> {});
    }

    public static void spawnSpiders() {

        spawnMob(Config.config.getInt("traps.spawn.creepycrawlies"), Spider.class, (player, spider) -> spider.setTarget(player));
    }

    public static void spawnFish() {

        spawnMob(Config.config.getInt("traps.spawn.swimmyfish"), Guardian.class, (player, guardian) -> {});
    }

    public static void spawnOPZombie() {

        spawnMob(Config.config.getInt("traps.spawn.opzombie"), Zombie.class, (player, zombie) -> {
            zombie.getEquipment().setHelmet(new ItemStack(Material.DIAMOND_HELMET));
            zombie.getEquipment().setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE));
            zombie.getEquipment().setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS));
            zombie.getEquipment().setBoots(new ItemStack(Material.DIAMOND_BOOTS));
            Objects.requireNonNull(zombie.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE)).setBaseValue(0.5);
        });
    }

    public static void spawnPillagers() {

        spawnMob(Config.config.getInt("traps.spawn.pillagers"), Pillager.class, (player, pillager) -> {
            pillager.setPatrolLeader(false);
            if (pillager.getEquipment().getHelmet().getType().equals(Material.WHITE_BANNER)) {
                pillager.getEquipment().setHelmet(new ItemStack(Material.AIR));
            }
        });
    }

    public static void spawnJackFrost() {

        spawnMob(Config.config.getInt("traps.spawn.jackfrost"), Snowman.class, (player, snowman) -> {});
    }

    public static void spawnCreeper() {

        spawnMob(Config.config.getInt("traps.spawn.awman"), Creeper.class, (player, creeper) -> {
            creeper.setTarget(player);
            Objects.requireNonNull(creeper.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED)).setBaseValue(0.4);
            Objects.requireNonNull(creeper.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(1);
            creeper.setHealth(1);
        });
    }

    public static void spawnBlaze() {

        spawnMob(Config.config.getInt("traps.spawn.feelinghot"), Blaze.class, (player, blaze) -> {});
    }

    public static void spawnSkeleton() {

        spawnMob(Config.config.getInt("traps.spawn.theskeletonappears"), Skeleton.class, (player, skeleton) -> {
            Objects.requireNonNull(skeleton.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(10);
            Objects.requireNonNull(skeleton.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED)).setBaseValue(2);
            skeleton.setHealth(10);
            skeleton.setTarget(player);
        });
    }

    public static void spawnBees() {
        spawnMob(Config.config.getInt("traps.spawn.notthebees"), Bee.class, (player, bee) -> {});
    }

    public static void spawnSlimes() {
        spawnMob(Config.config.getInt("traps.spawn.revenge"), Slime.class, (player, slime) -> {
            slime.setSize(2);
            slime.setTarget(player);
        });
    }
    public static void spawnCaveSpiders() {
        spawnMob(Config.config.getInt("traps.spawn.evencreepiercrawlies"), CaveSpider.class, (player, caveSpider) -> caveSpider.setTarget(player));
    }

    public static void spawnPhantom() {
        spawnMob(1, Phantom.class, (player, phantom) -> phantom.setTarget(player));
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
