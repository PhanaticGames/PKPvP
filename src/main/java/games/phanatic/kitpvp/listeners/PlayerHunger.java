package games.phanatic.kitpvp.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class PlayerHunger implements Listener {

    @EventHandler
    public void execute(FoodLevelChangeEvent e) {
        e.setCancelled(true);
    }
}
