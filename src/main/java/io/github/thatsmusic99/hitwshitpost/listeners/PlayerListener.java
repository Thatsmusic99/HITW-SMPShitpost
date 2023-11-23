package io.github.thatsmusic99.hitwshitpost.listeners;

import io.github.thatsmusic99.hitwshitpost.HITWShitpost;
import io.github.thatsmusic99.hitwshitpost.config.Config;
import io.github.thatsmusic99.hitwshitpost.hooks.BossBarManager;
import io.github.thatsmusic99.hitwshitpost.lists.JoinQuitMessages;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.sound.Sound;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerResourcePackStatusEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Random;

public class PlayerListener implements Listener {
    static int sec = 20;

    static final PotionEffect jumpBoost = new PotionEffect(PotionEffectType.JUMP, PotionEffect.INFINITE_DURATION, 1, false, false);
    static final PotionEffect reveal = new PotionEffect(PotionEffectType.BLINDNESS, (sec), 0, false, false);

    // Potion Effects for Traps
    //static PotionEffect springy = new PotionEffect(PotionEffectType.JUMP, (sec * Config.config.getInt("traps.length.springyshoes")), 3, false, false);
    //static PotionEffect speedBoost = new PotionEffect(PotionEffectType.SPEED, (sec * Config.config.getInt("traps.length.superspeed")), 4, false, false);
    //static PotionEffect legDay = new PotionEffect(PotionEffectType.SLOW, (sec * Config.config.getInt("traps.length.legday")), 2, false, false);
    //static PotionEffect gravity = new PotionEffect(PotionEffectType.SLOW_FALLING, (sec * Config.config.getInt("traps.length.lowgravity")), 1, false, false);
    //static PotionEffect darkness = new PotionEffect(PotionEffectType.DARKNESS, (sec * Config.config.getInt("traps.length.solareclipse")), 0, false, false);

    // Speedboost Trap
    public static void applySpeed() {
        for (Player p : Bukkit.getOnlinePlayers())  {
            p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, (sec * Config.config.getInt("traps.length.superspeed")), 4, false, false));
        }
    }

    public static void applySlow() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, (sec * Config.config.getInt("traps.length.legday")), 2, false, false));
        }
    }

    public static void applyDarkness() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            p.addPotionEffect(new PotionEffect(PotionEffectType.DARKNESS, (sec * Config.config.getInt("traps.length.solareclipse")), 0, false, false));
        }
    }

    public static void applySpringy() {
        for (Player p : Bukkit.getOnlinePlayers())  {
            p.removePotionEffect(PotionEffectType.JUMP);
            p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, (sec * Config.config.getInt("traps.length.springyshoes")), 3, false, false));
        }
    }

    public static void applyGravity() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, (sec * Config.config.getInt("traps.length.lowgravity")), 1, false, false));
        }
    }

    public static void trapReveal(String trapName) {
        for (Player p: Bukkit.getOnlinePlayers()) {
            p.addPotionEffect(reveal);

            String trap = trapName.replace('_', ' ');
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&3[&eTRAP ALERT&3]&c Trap activated: " + "&6" +  trap));
            p.sendTitle("", ChatColor.GOLD + trap);

            if (p.getResourcePackStatus() == PlayerResourcePackStatusEvent.Status.SUCCESSFULLY_LOADED) {
                p.playSound(Sound.sound(Key.key("hitwsmp:sfx_trapreveal"), Sound.Source.NEUTRAL, 5.0f, 1.0f), Sound.Emitter.self());
            } else {
                p.playSound(Sound.sound(org.bukkit.Sound.BLOCK_BELL_USE, Sound.Source.NEUTRAL, 6.0F, 0.2F), Sound.Emitter.self());
                p.playSound(Sound.sound(org.bukkit.Sound.AMBIENT_CAVE, Sound.Source.NEUTRAL, 0.4F, 0.7F), Sound.Emitter.self());
            }


        }
    }

    // Join Event
    @EventHandler
    public void playerJoinEvent(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        Random rand = new Random();

        if (!player.hasPlayedBefore()) {
            String message = JoinQuitMessages.FirstTime.get(rand.nextInt(JoinQuitMessages.FirstTime.size()));
            e.setJoinMessage(ChatColor.translateAlternateColorCodes('&', message).replace("{player}", player.getName()));
            for (Player p : Bukkit.getOnlinePlayers()) {
                p.playSound(Sound.sound(Key.key("hitwsmp:sfx_welcome"), Sound.Source.NEUTRAL, 5.0f, 1.0f), Sound.Emitter.self());
            }
        } else {
            String message = JoinQuitMessages.Return.get(rand.nextInt(JoinQuitMessages.Return.size()));
            e.setJoinMessage(ChatColor.translateAlternateColorCodes('&', message).replace("{player}", player.getName()));
            for (Player p : Bukkit.getOnlinePlayers()) {
                p.playSound(Sound.sound(Key.key("hitwsmp:sfx_join"), Sound.Source.NEUTRAL, 5.0f, 1.0f), Sound.Emitter.self());
            }
        }

        jumpBoost.apply(player);
        BossBarManager.get().addPlayer(player);
    }
    // Quit Event - making sure to remove the jump boost from the player.
    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        Random rand = new Random();

        if (player.hasPotionEffect(PotionEffectType.JUMP)) {
            player.removePotionEffect(PotionEffectType.JUMP);
        }

        for (Player p : Bukkit.getOnlinePlayers()) {
            p.playSound(Sound.sound(Key.key("hitwsmp:sfx_leave"), Sound.Source.NEUTRAL, 5.0f, 1.0f), Sound.Emitter.self());
        }

        if (player.isDead()) {
            String message = JoinQuitMessages.DeathQuit.get(rand.nextInt(JoinQuitMessages.DeathQuit.size()));
            e.setQuitMessage(ChatColor.translateAlternateColorCodes('&', message).replace("{player}", player.getName()));
        } else {
            String message = JoinQuitMessages.Quit.get(rand.nextInt(JoinQuitMessages.Quit.size()));
            e.setQuitMessage(ChatColor.translateAlternateColorCodes('&', message).replace("{player}", player.getName()));
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

    @EventHandler
    public void springyRanOut(EntityPotionEffectEvent e) {
        if (e.getEntity() instanceof Player player) {
            if (e.getAction().equals(EntityPotionEffectEvent.Action.REMOVED)) {
                Bukkit.getScheduler().runTaskLater(HITWShitpost.get(), new Runnable() {
                    @Override
                    public void run() {
                        player.addPotionEffect(jumpBoost);
                    }
                }, 0L);
            }
        }
    }
}
