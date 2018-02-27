package games.phanatic.kitpvp.listeners;

import games.phanatic.kitpvp.util.ScoreboardUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerLeave implements Listener {

    @EventHandler
    public void execute(PlayerQuitEvent e) {
        Player p = e.getPlayer();

        if (ScoreboardUtil.hasScore(p)) {
            ScoreboardUtil.removeScore(p);
        }
    }
}
