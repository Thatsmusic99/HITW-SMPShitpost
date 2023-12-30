package io.github.thatsmusic99.hitwshitpost.lists;

import io.github.thatsmusic99.hitwshitpost.config.Config;
import io.github.thatsmusic99.hitwshitpost.listeners.PlayerListener;
import io.github.thatsmusic99.hitwshitpost.traps.MobTraps;
import io.github.thatsmusic99.hitwshitpost.traps.PlayerTraps;

import java.util.Random;

public enum Traps {
    LEG_DAY(Config.config.getInt("traps.chances.legday"), PlayerListener::applySlow),
    SUPER_SPEED(Config.config.getInt("traps.chances.superspeed"), PlayerListener::applySpeed),
    SPRINGY_SHOES(Config.config.getInt("traps.chances.springyshoes"), PlayerListener::applySpringy),
    LOW_GRAVITY(Config.config.getInt("traps.chances.lowgravity"), PlayerListener::applyGravity),
    SOLAR_ECLIPSE(Config.config.getInt("traps.chances.solareclipse"), PlayerListener::applyDarkness),
    EXHAUSTED_ARMS(Config.config.getInt("traps.chances.exhaustedarms"), PlayerListener::applyMineFatigue),
    EXCAVATOR(Config.config.getInt("traps.chance.excavator"), PlayerListener::applyHaste),
    SO_LONELY(Config.config.getInt("traps.chances.solonely"), MobTraps::spawnZombies),
    CREEPY_CRAWLIES(Config.config.getInt("traps.chances.creepycrawlies"), MobTraps::spawnSpiders),
    EVEN_CREEPIER_CRAWLIES(Config.config.getInt("traps.chances.evencreepiercrawlies"), MobTraps::spawnCaveSpiders),
    SWIMMY_FISH(Config.config.getInt("traps.chances.swimmyfish"), MobTraps::spawnFish),
    PILLAGERS(Config.config.getInt("traps.chances.pillagers"), MobTraps::spawnPillagers),
    JACK_FROST(Config.config.getInt("traps.chances.jackfrost"), MobTraps::spawnJackFrost),
    FEELING_HOT(Config.config.getInt("traps.chances.feelinghot"), MobTraps::spawnBlaze),
    REVENGE(Config.config.getInt("traps.chances.revenge"), MobTraps::spawnSlimes),
    NOT_THE_BEES(Config.config.getInt("traps.chances.notthebees"), MobTraps::spawnBees),
    THE_SKELETON_APPEARS(Config.config.getInt("traps.chances.theskeletonappears"), MobTraps::spawnSkeleton),
    ARROWS(Config.config.getInt("traps.chances.arrows"), PlayerTraps::ArrowRain),
    SNOWSTORM(Config.config.getInt("traps.chances.snowstorm"), PlayerTraps::snowballRain),
    ULTRA_BOUNCY(Config.config.getInt("traps.chances.ultrabouncy"), MobTraps::spawnBouncySlime),
    FALLEN_CHAMPION(Config.config.getInt("traps.chances.fallenchampion"), MobTraps::spawnOPZombie),
    NO_SLEEP(Config.config.getInt("traps.chances.nosleep"), MobTraps::spawnPhantom),
    THORS_RAGE(Config.config.getInt("traps.chances.thorsrage"), PlayerTraps::lightningStrike),
    AW_MAN(Config.config.getInt("traps.chances.awman"), MobTraps::spawnCreeper);

    private final int weight;
    private final Runnable trap;

    Traps(final int weight, final Runnable trap) {
        this.weight = weight;
        this.trap = trap;
    }

    public static int getTotalWeight() {
        int total = 0;
        for (Traps traps : values()) {
            total += traps.weight;
        }
        return total;
    }

    public Runnable getTrap() {
        return trap;
    }

    public static Traps pickTrap() {

        // Get the weight we can pick from
        final int maxWeight = getTotalWeight();

        // Pick the weight
        int weight = new Random().nextInt(maxWeight);

        // Go through each trap
        for (Traps trap : values()) {
            if (trap.weight > weight) return trap;
            weight -= trap.weight;
        }

        return AW_MAN;
    }
}
