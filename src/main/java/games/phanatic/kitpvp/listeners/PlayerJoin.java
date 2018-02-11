package games.phanatic.kitpvp.listeners;

import games.phanatic.kitpvp.PKPvP;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {

    private PKPvP pvp;

    public PlayerJoin(PKPvP pvp) {
        this.pvp = pvp;
    }

    @EventHandler
    public void execute(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        p.teleport(pvp.getLocManager().getSpawn());
        pvp.getIsManager().setPlayersInv(p);
    }
}
