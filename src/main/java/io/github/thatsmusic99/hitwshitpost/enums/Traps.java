package io.github.thatsmusic99.hitwshitpost.enums;

import java.util.Random;

public enum Traps {
    LEG_DAY(20),
    SUPER_SPEED(20),
    SPRINGY_SHOES(20),
    LOW_GRAVITY(20),
    SOLAR_ECLIPSE(15),
    SO_LONELY(10),
    CREEPY_CRAWLIES(10),
    GUARDIANS(10),
    PILLAGERS(10),
    JACK_FROST(10),
    FEELING_HOT(10),
    REVENGE(10),
    NOT_THE_BEES(10),
    THE_SKELETON_APPEARS(3),
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
