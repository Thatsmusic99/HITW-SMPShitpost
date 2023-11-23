package io.github.thatsmusic99.hitwshitpost.lists;

import io.github.thatsmusic99.hitwshitpost.config.Config;

import java.util.Random;

public enum Traps {
    LEG_DAY(Config.config.getInt("traps.chances.legday")),
    SUPER_SPEED(Config.config.getInt("traps.chances.superspeed")),
    SPRINGY_SHOES(Config.config.getInt("traps.chances.springyshoes")),
    LOW_GRAVITY(Config.config.getInt("traps.chances.lowgravity")),
    SOLAR_ECLIPSE(Config.config.getInt("traps.chances.solareclipse")),
    SO_LONELY(Config.config.getInt("traps.chances.solonely")),
    CREEPY_CRAWLIES(Config.config.getInt("traps.chances.creepycrawlies")),
    EVEN_CREEPIER_CRAWLIES(Config.config.getInt("traps.chances.evencreepiercrawlies")),
    SWIMMY_FISH(Config.config.getInt("traps.chances.swimmyfish")),
    PILLAGERS(Config.config.getInt("traps.chances.pillagers")),
    JACK_FROST(Config.config.getInt("traps.chances.jackfrost")),
    FEELING_HOT(Config.config.getInt("traps.chances.feelinghot")),
    REVENGE(Config.config.getInt("traps.chances.revenge")),
    NOT_THE_BEES(Config.config.getInt("traps.chances.notthebees")),
    THE_SKELETON_APPEARS(Config.config.getInt("traps.chances.theskeletonappears")),
    ARROWS(Config.config.getInt("traps.chances.arrows")),
    SNOWSTORM(Config.config.getInt("traps.chances.snowstorm")),
    AW_MAN(Config.config.getInt("traps.chances.awman"));

    private final int weight;

    Traps(final int weight) {
        this.weight = weight;
    }

    public static int getTotalWeight() {
        int total = 0;
        for (Traps traps : values()) {
            total += traps.weight;
        }
        return total;
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
