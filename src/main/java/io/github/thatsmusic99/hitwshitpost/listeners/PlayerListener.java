package io.github.thatsmusic99.hitwshitpost.listeners;

import io.github.thatsmusic99.hitwshitpost.HITWShitpost;
import io.github.thatsmusic99.hitwshitpost.hooks.BossBarManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PlayerListener implements Listener {
    static int sec = 20;
    static PotionEffect jumpBoost = new PotionEffect(PotionEffectType.JUMP, PotionEffect.INFINITE_DURATION, 1, false, false);
    static PotionEffect springy = new PotionEffect(PotionEffectType.JUMP, (sec * 8), 3, false, false);
    static PotionEffect speedBoost = new PotionEffect(PotionEffectType.SPEED, (sec * 8), 4, false, false);
    static PotionEffect legDay = new PotionEffect(PotionEffectType.SLOW, (sec * 10), 2, false, false);
    static PotionEffect gravity = new PotionEffect(PotionEffectType.SLOW_FALLING, (sec * 15), 1, false, false);
    static PotionEffect reveal = new PotionEffect(PotionEffectType.BLINDNESS, (sec), 0, false, false);

    // Speedboost Trap
    public static void applySpeed() {
        for (Player p : Bukkit.getOnlinePlayers())  {
            p.addPotionEffect(speedBoost);
        }
    }

    public static void applySlow() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            p.addPotionEffect(legDay);
        }
    }

    public static void applySpringy() {
        for (Player p : Bukkit.getOnlinePlayers())  {
            p.removePotionEffect(PotionEffectType.JUMP);
            p.addPotionEffect(springy);

            Bukkit.getScheduler().runTaskLater(HITWShitpost.get(), new Runnable() {
                @Override
                public void run() {
                    p.addPotionEffect(jumpBoost);
                }
            }, 165L);
        }
    }

    public static void applyGravity() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            p.addPotionEffect(gravity);
        }
    }

    public static void trapReveal(String trapName) {
        for (Player p: Bukkit.getOnlinePlayers()) {
            p.addPotionEffect(reveal);

            String trap = trapName.replace('_', ' ');
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&3[&eTRAP ALERT&3]&c Trap activated: " + "&6" +  trap));
            p.sendTitle("", ChatColor.GOLD + trap);
            p.playSound(p.getLocation(), Sound.AMBIENT_CAVE, 5.0F, 0.7F);
        }
    }

    // Join Event
    @EventHandler
    public void playerJoinEvent(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        jumpBoost.apply(player);

        BossBarManager.get().addPlayer(player);
    }
    // Quit Event - making sure to remove the jump boost from the player.
    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        if (player.hasPotionEffect(PotionEffectType.JUMP)) {
            player.removePotionEffect(PotionEffectType.JUMP);
        }
    }
    // Event for when the player dies or drinks milk, that the jump boost gets re-applied.
    @EventHandler
    public void playerDrinkEvent(EntityPotionEffectEvent e) {
        if (e.getEntity() instanceof Player player) {
            if (e.getCause().equals(EntityPotionEffectEvent.Cause.MILK) || e.getCause().equals(EntityPotionEffectEvent.Cause.DEATH)) {
                Bukkit.getScheduler().runTaskLater(HITWShitpost.get(), new Runnable() {
                    @Override
                    public void run() {
                        jumpBoost.apply(player);
                    }
                    }, 5L);
            }
        }
    }
}
