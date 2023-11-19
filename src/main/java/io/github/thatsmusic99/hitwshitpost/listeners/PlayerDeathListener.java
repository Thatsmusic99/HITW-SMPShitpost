package io.github.thatsmusic99.hitwshitpost.listeners;


import io.github.thatsmusic99.hitwshitpost.HITWShitpost;
import io.github.thatsmusic99.hitwshitpost.lists.DeathMessages;
import org.bukkit.ChatColor;
import org.bukkit.entity.*;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.Objects;
import java.util.Random;


public class PlayerDeathListener implements Listener {

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        Player player = e.getPlayer();
        EntityDamageEvent.DamageCause cause = Objects.requireNonNull(e.getPlayer().getLastDamageCause()).getCause();
        HITWShitpost.get().getLogger().info(Objects.requireNonNull(e.getPlayer().getLastDamageCause().getCause().name()));

        Random rand = new Random();

        switch (cause) {
            case LAVA -> {
                String message = DeathMessages.LAVA.get(rand.nextInt(DeathMessages.LAVA.size()));
                e.setDeathMessage(ChatColor.translateAlternateColorCodes('&', message.replace("{player}", player.getName())));
            }
            case FALL -> {
                Event block = player.getLastDamageCause();
                String message = DeathMessages.FALL.get(rand.nextInt(DeathMessages.FALL.size()));
                e.setDeathMessage(ChatColor.translateAlternateColorCodes('&', message.replace("{player}", player.getName())));
            }
            case ENTITY_ATTACK -> {
                if (player.getLastDamageCause() instanceof EntityDamageByEntityEvent event && !(event.getDamager() instanceof Player)) {
                    String message = DeathMessages.ENTITY_ATTACK.get(rand.nextInt(DeathMessages.ENTITY_ATTACK.size()));
                    e.setDeathMessage(ChatColor.translateAlternateColorCodes('&', message.replace("{player}", player.getName()).replace("{mob}", event.getDamager().getName())));
                } else if (player.getLastDamageCause() instanceof EntityDamageByEntityEvent event && (event.getDamager() instanceof Player)) {
                    String message = DeathMessages.PLAYER.get(rand.nextInt(DeathMessages.PLAYER.size()));
                    e.setDeathMessage(ChatColor.translateAlternateColorCodes('&', message.replace("{player}", player.getName()).replace("{mob}", event.getDamager().getName())));

                }
            }
            case PROJECTILE -> {
                if (player.getLastDamageCause() instanceof EntityDamageByEntityEvent event && event.getDamager() instanceof Arrow arrow && arrow.getShooter() != null && arrow.getShooter() instanceof Entity entity) {
                    String message = DeathMessages.SHOT.get(rand.nextInt(DeathMessages.SHOT.size()));
                    e.setDeathMessage(ChatColor.translateAlternateColorCodes('&', message.replace("{player}", player.getName()).replace("{mob}", entity.getName())));
                }

                if (player.getLastDamageCause() instanceof EntityDamageByEntityEvent event && event.getDamager() instanceof LlamaSpit spit && spit.getShooter() != null && spit.getShooter() instanceof Entity entity) {
                    String message = DeathMessages.SPIT.get(rand.nextInt(DeathMessages.SPIT.size()));
                    e.setDeathMessage(ChatColor.translateAlternateColorCodes('&', message.replace("{player}", player.getName()).replace("{mob}", entity.getName())));
                }
                String message = DeathMessages.PROJECTILE.get(rand.nextInt(DeathMessages.PROJECTILE.size()));
                e.setDeathMessage(ChatColor.translateAlternateColorCodes('&', message.replace("{player}", player.getName())));
            }
            case VOID -> {
                String message = DeathMessages.VOID.get(rand.nextInt(DeathMessages.VOID.size()));
                e.setDeathMessage(ChatColor.translateAlternateColorCodes('&', message.replace("{player}", player.getName())));
            }
            case POISON -> {
                String message = DeathMessages.POISON.get(rand.nextInt(DeathMessages.POISON.size()));
                e.setDeathMessage(ChatColor.translateAlternateColorCodes('&', message.replace("{player}", player.getName())));
            }
            case FIRE -> {
                String message = DeathMessages.FIRE.get(rand.nextInt(DeathMessages.FIRE.size()));
                e.setDeathMessage(ChatColor.translateAlternateColorCodes('&', message.replace("{player}", player.getName())));
            }
            case BLOCK_EXPLOSION -> {
                String message = DeathMessages.EXPLOSION.get(rand.nextInt(DeathMessages.EXPLOSION.size()));
                e.setDeathMessage(ChatColor.translateAlternateColorCodes('&', message.replace("{player}", player.getName())));
            }

            case ENTITY_EXPLOSION -> {
                if (player.getLastDamageCause() instanceof EntityDamageByEntityEvent event) {
                    String message = DeathMessages.CREEPER.get(rand.nextInt(DeathMessages.CREEPER.size()));
                    e.setDeathMessage(ChatColor.translateAlternateColorCodes('&', message.replace("{player}", player.getName()).replace("{mob}", event.getDamager().getName())));
                }
            }

            case DROWNING -> {
                String message = DeathMessages.DROWN.get(rand.nextInt(DeathMessages.DROWN.size()));
                e.setDeathMessage(ChatColor.translateAlternateColorCodes('&', message.replace("{player}", player.getName())));

            }
        }
    }
}
