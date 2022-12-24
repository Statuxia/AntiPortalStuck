package me.statuxia.antiportalstuck;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

import java.util.HashMap;

public class Events implements Listener {

    @EventHandler
    public static void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        Location location = player.getLocation();
        HashMap<Integer, Location> locationsInRange = new HashMap<>();

        if (!Material.NETHER_PORTAL.equals(location.getBlock().getType())) {
            return;
        }

        for (int x = -(Config.CONFIG_BLOCK_RANGE); x <= (Config.CONFIG_BLOCK_RANGE); x++) {
            for (int z = -(Config.CONFIG_BLOCK_RANGE); z <= (Config.CONFIG_BLOCK_RANGE); z++) {
                Block block = location.clone().add(x, 0, z).getBlock();
                Block block1 = block.getLocation().clone().add(0, -1, 0).getBlock();

                if (block.isEmpty() && !block1.isEmpty()) {
                    Location blockLocation = block.getLocation().toCenterLocation();
                    blockLocation.setYaw(location.getYaw());
                    blockLocation.setPitch(location.getPitch());
                    locationsInRange.put(Math.abs(x) + Math.abs(z), blockLocation);
                }
            }
        }

        if (locationsInRange.isEmpty()) {
            if (Config.CONFIG_FORCE_TP) {
                location.setY(location.getWorld().getHighestBlockYAt(location) + 1);
                player.teleport(location, PlayerTeleportEvent.TeleportCause.PLUGIN);
            }
            return;
        }

        Location loc;
        for (int i = 0; i <= Config.CONFIG_BLOCK_RANGE * 2; i++) {
            loc = locationsInRange.get(i);
            if (loc != null) {
                player.teleport(loc, PlayerTeleportEvent.TeleportCause.PLUGIN);
                return;
            }
        }
    }
}
