package io.github.thatsmusic99.hitwshitpost.lists;

import java.util.Random;

public enum Traps {
    LEG_DAY(20),
    SUPER_SPEED(20),
    SPRINGY_SHOES(20),
    LOW_GRAVITY(20),
    SOLAR_ECLIPSE(15),
    SO_LONELY(10),
    CREEPY_CRAWLIES(10),
    EVEN_CREEPIER_CRAWLIES(10),
    SWIMMY_FISH(10),
    PILLAGERS(10),
    JACK_FROST(5),
    FEELING_HOT(5),
    REVENGE(8),
    NOT_THE_BEES(5),
    THE_SKELETON_APPEARS(3),
    ARROWS(4),
    SNOWSTORM(4),
    AW_MAN(3);

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
