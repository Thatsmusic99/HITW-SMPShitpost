package io.github.thatsmusic99.hitwshitpost.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Random;

public class PlayerTraps {
    // Potential traps!
    public static void healPlayer() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            Random rand = new Random();
            double addHealth = rand.nextDouble(1, 2);

            if (p.getHealth() < 20) {
                double currentHealth = p.getHealth();
                p.setHealth(currentHealth + addHealth);
            }
        }
    }

}
