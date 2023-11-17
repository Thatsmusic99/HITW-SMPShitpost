package io.github.thatsmusic99.hitwshitpost.hooks;

import io.github.thatsmusic99.hitwshitpost.HITWShitpost;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
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
     */
    private static final int TOTAL_SECONDS_COUNTDOWN = 300;
    private static BossBarManager instance;
    private final @NotNull BossBar timerDisplay;
    private int secondsLeft;

    public BossBarManager() {
        instance = this;



        timerDisplay = Bukkit.createBossBar(ChatColor.GOLD + "Next trap in: ", BarColor.GREEN, BarStyle.SOLID);
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

        int seconds = (secondsLeft % 60);
        int minutes = (secondsLeft % (60 * 60) / 60);
        timerDisplay.setProgress(secondsLeft / (double) TOTAL_SECONDS_COUNTDOWN);
        if (minutes < 1) {
            if (seconds > 0 && seconds <= 3) {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    p.playSound(p, Sound.UI_BUTTON_CLICK, 3.0F, 1.7F);
                }
            }
            timerDisplay.setTitle(String.format(ChatColor.GOLD + "Next trap in " + ChatColor.RED + "%02d:%02d", minutes, seconds));
            return;
        }
        timerDisplay.setTitle(String.format(ChatColor.GOLD + "Next trap in %02d:%02d", minutes, seconds));
    }


    public static void createTimeDisplay(int time) {
        String message = "Next trap in: ";
        double timeLeft = 1;
        double seconds = Math.floor((timeLeft % 60));
        double minutes = Math.floor(timeLeft % (60 * 60) / 60);

        BossBar timeDisplay = Bukkit.createBossBar(ChatColor.GOLD + message + minutes + ":" + seconds, BarColor.WHITE, BarStyle.SOLID);

        for (Player p : Bukkit.getOnlinePlayers()) {
            timeDisplay.addPlayer(p);
            timeDisplay.setProgress((float) timeLeft -1);
        }
    }

}
