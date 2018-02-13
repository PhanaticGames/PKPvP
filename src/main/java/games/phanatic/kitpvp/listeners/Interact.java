package games.phanatic.kitpvp.listeners;

import games.phanatic.kitpvp.PKPvP;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class Interact implements Listener {

    private PKPvP pvp;

    public Interact(PKPvP pvp) {
        this.pvp = pvp;
    }

    @EventHandler
    public void execute(PlayerInteractEvent e) {
        if (e.getItem().getType() == pvp.getIsManager().getKitSelector().getType()) {
            pvp.getInvManager().openKitSelector(e.getPlayer());
        } else if (e.getItem().getType() == pvp.getIsManager().getKillStreaks().getType()) {
            pvp.getInvManager().openKillsStreaks(e.getPlayer());
        }
    }
}
