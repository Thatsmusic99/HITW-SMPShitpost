package io.github.thatsmusic99.hitwshitpost.hooks;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import com.sk89q.worldguard.protection.regions.RegionQuery;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class WorldGuardManager {
    public static boolean playerIsInRegion(Player player) {
        Location plrLocation = player.getLocation();
        RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
        RegionQuery query = container.createQuery();

        return query.getApplicableRegions(BukkitAdapter.adapt(plrLocation)).size() > 0;
    }
}
