package games.phanatic.kitpvp.listeners;

import games.phanatic.kitpvp.PKPvP;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class Drop implements Listener {

    private PKPvP pvp;

    public Drop(PKPvP pvp) {
        this.pvp = pvp;
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent e) {
        Material dropMat = e.getItemDrop().getItemStack().getType();
        if(dropMat == pvp.getIsManager().getKillStreaks().getType() || dropMat == pvp.getIsManager().getKitSelector().getType()) {
            e.setCancelled(true);
        }
    }
}
