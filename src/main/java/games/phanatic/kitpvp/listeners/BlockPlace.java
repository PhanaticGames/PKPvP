package games.phanatic.kitpvp.listeners;

import games.phanatic.kitpvp.PKPvP;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockPlace implements Listener {

    private PKPvP pvp;

    public BlockPlace(PKPvP pvp) {
        this.pvp = pvp;
    }

    @EventHandler
    public void execute(BlockPlaceEvent e) {
        if(e.getItemInHand().getType() == pvp.getIsManager().getKillStreaks().getType()) {
            e.setCancelled(true);
        }
    }
}
