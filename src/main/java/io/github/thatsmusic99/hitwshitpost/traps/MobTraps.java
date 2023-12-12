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
import org.w3c.dom.Attr;

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

        spawnMob(Config.config.getInt("traps.spawn.fallenchampion"), Zombie.class, (player, zombie) -> {
            zombie.getEquipment().setHelmet(new ItemStack(Material.DIAMOND_HELMET));
            zombie.getEquipment().setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE));
            zombie.getEquipment().setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS));
            zombie.getEquipment().setBoots(new ItemStack(Material.DIAMOND_BOOTS));

            Objects.requireNonNull(zombie.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE)).setBaseValue(1);
            Objects.requireNonNull(zombie.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(10);
            zombie.setHealth(10);

            // Of course, we don't want anyone to have diamond armour lol
            zombie.getEquipment().setHelmetDropChance(0f);
            zombie.getEquipment().setChestplateDropChance(0f);
            zombie.getEquipment().setLeggingsDropChance(0f);
            zombie.getEquipment().setBootsDropChance(0f);

            // Just in case if the zombie spawns with something in their hand.
            if (zombie.getEquipment().getItemInMainHand().getType() != Material.AIR) {
                zombie.getEquipment().setItemInMainHand(new ItemStack(Material.AIR));
            }
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

    public static void spawnBouncySlime() {
        spawnMob(Config.config.getInt("traps.spawn.ultrabouncy"), Slime.class, (player, slime) -> {
            Objects.requireNonNull(slime.getAttribute(Attribute.GENERIC_ATTACK_KNOCKBACK)).setBaseValue(Config.config.getDouble("ultrabouncy.knockback"));
            Objects.requireNonNull(slime.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE)).setBaseValue(0.1); // Haha WEAK
            slime.setSize(2);
            slime.setTarget(player);
            HITWShitpost.get().getLogger().info(String.valueOf(Objects.requireNonNull(slime.getAttribute(Attribute.GENERIC_ATTACK_KNOCKBACK)).getBaseValue()));

        });
    }

    public static void spawnCaveSpiders() {
        spawnMob(Config.config.getInt("traps.spawn.evencreepiercrawlies"), CaveSpider.class, (player, caveSpider) -> caveSpider.setTarget(player));
    }

    public static void spawnPhantom() {
        spawnMob(1, Phantom.class, (player, phantom) -> {
            phantom.setTarget(player);
            Objects.requireNonNull(phantom.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(5);
            Objects.requireNonNull(phantom.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE)).setBaseValue(1);
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
