package io.github.thatsmusic99.hitwshitpost.hooks;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;

public class BossBarManager {
    /*
        This BossBar will mainly be used to display how long until the next trap gets activated.
        That way players know when they have to expect the next trap to prepare for it.

        - As soon as player joins the boss bar shall show up for them
        - Bossbar has to show the actual time left until next trap

        TODO Make the bossbar display the countdown
     */

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
