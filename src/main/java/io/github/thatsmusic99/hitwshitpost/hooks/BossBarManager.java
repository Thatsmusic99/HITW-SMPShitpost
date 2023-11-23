package io.github.thatsmusic99.hitwshitpost.hooks;

import io.github.thatsmusic99.hitwshitpost.HITWShitpost;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.sound.Sound;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerResourcePackStatusEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class BossBarManager {
    /*
        This BossBar will mainly be used to display how long until the next trap gets activated.
        That way players know when they have to expect the next trap to prepare for it.

        - As soon as player joins the boss bar shall show up for them
        - Bossbar has to show the actual time left until next trap
     */
    private static final int TOTAL_SECONDS_COUNTDOWN = 180; // 180 (3 minutes) - before: 300 (5 minutes)
    private static BossBarManager instance;
    private final @NotNull BossBar timerDisplay;
    private int secondsLeft;

    public BossBarManager() {
        instance = this;

        timerDisplay = Bukkit.createBossBar(ChatColor.GOLD + "Next trap in: ", BarColor.GREEN, BarStyle.SOLID);
        secondsLeft = TOTAL_SECONDS_COUNTDOWN;
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
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (seconds == 4 && player.getResourcePackStatus() == PlayerResourcePackStatusEvent.Status.SUCCESSFULLY_LOADED) {
                    player.playSound(Sound.sound(Key.key("hitwsmp:sfx_countdown"), Sound.Source.NEUTRAL, 9.0f, 1.0f), Sound.Emitter.self());

                } else if (seconds >= 1 && seconds <=3 && player.getResourcePackStatus() != PlayerResourcePackStatusEvent.Status.SUCCESSFULLY_LOADED) {
                    player.playSound(Sound.sound(org.bukkit.Sound.UI_BUTTON_CLICK, Sound.Source.NEUTRAL, 2.0f, 2.0f), Sound.Emitter.self());
                }
            }

            timerDisplay.setTitle(String.format(ChatColor.GOLD + "Next trap in " + ChatColor.RED + "%02d:%02d", minutes, seconds));
            return;
        }
        timerDisplay.setTitle(String.format(ChatColor.GOLD + "Next trap in %02d:%02d", minutes, seconds));
    }
}
