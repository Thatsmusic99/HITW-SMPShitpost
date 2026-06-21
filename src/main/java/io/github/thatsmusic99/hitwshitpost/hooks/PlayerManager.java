package io.github.thatsmusic99.hitwshitpost.hooks;

import io.github.thatsmusic99.hitwshitpost.commands.InstantTrap;
import org.bukkit.entity.Player;

import java.util.UUID;

public class PlayerManager {
    public static boolean playerHasBypass(Player player) {
        UUID uuid = player.getUniqueId();
        return InstantTrap.tempBypass.contains(uuid) || WorldGuardManager.playerIsInRegion(player);
    }
}
