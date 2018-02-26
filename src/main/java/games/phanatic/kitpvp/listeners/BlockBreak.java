package games.phanatic.kitpvp.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreak implements Listener {

    @EventHandler
    public void execute(BlockBreakEvent e) {
        if(!e.getPlayer().isOp()) {
            e.setCancelled(true);
        }
    }
}
