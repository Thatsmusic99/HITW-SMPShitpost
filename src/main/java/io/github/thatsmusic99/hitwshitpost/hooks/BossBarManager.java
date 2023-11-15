package io.github.thatsmusic99.hitwshitpost.hooks;

import io.github.thatsmusic99.hitwshitpost.HITWShitpost;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class BossBarManager {
    /*
        This BossBar will mainly be used to display how long until the next trap gets activated.
        That way players know when they have to expect the next trap to prepare for it.

        - As soon as player joins the boss bar shall show up for them
        - Bossbar has to show the actual time left until next trap

        TODO Make the bossbar display the countdown
     */
    private static final int TOTAL_SECONDS_COUNTDOWN = 300;
    private static BossBarManager instance;
    private final @NotNull BossBar timerDisplay;
    private int secondsLeft;

    public BossBarManager() {
        instance = this;

        timerDisplay = Bukkit.createBossBar(ChatColor.GOLD + "Time until next trap", BarColor.GREEN, BarStyle.SOLID);
        secondsLeft = 300;
    }

    public static BossBarManager get() {
        return instance;
    }

    public void addPlayer(final @NotNull Player player) {
        timerDisplay.addPlayer(player);
    }

    public void tick() {
        secondsLeft--;

        // If the countdown has finished, then pick a trap
        if (secondsLeft == 0) {
            HITWShitpost.PickTrap();
            secondsLeft = TOTAL_SECONDS_COUNTDOWN;
        }

        timerDisplay.setProgress(secondsLeft / (double) TOTAL_SECONDS_COUNTDOWN);
    }


    public static void createTimeDisplay(int time) {
        String message = "Time until the next trap";
        int timeLeft = 1;
        BossBar timeDisplay = Bukkit.createBossBar(ChatColor.GOLD + message, BarColor.WHITE, BarStyle.SOLID);

        for (Player p : Bukkit.getOnlinePlayers()) {
            timeDisplay.addPlayer(p);
            timeDisplay.setProgress((float) timeLeft -1);
        }
    }

}
