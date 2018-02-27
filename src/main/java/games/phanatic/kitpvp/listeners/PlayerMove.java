package games.phanatic.kitpvp.listeners;

import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import games.phanatic.kitpvp.PKPvP;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMove implements Listener {

    private PKPvP pvp;

    public PlayerMove(PKPvP pvp) {
        this.pvp = pvp;
    }

    @EventHandler
    public void execute(PlayerMoveEvent e) {
        ProtectedRegion rg = null;

        if (pvp.getWorldGuard().getRegionManager(e.getPlayer().getWorld()).getRegion("spawn") != null) {
            rg = pvp.getWorldGuard().getRegionManager(e.getPlayer().getWorld()).getRegion("spawn");

            // yes, this is redundent AF but, using #contains(BukkitUtil.toVector) makes build fail...
            int x = e.getPlayer().getLocation().getBlockX();
            int y = e.getPlayer().getLocation().getBlockY();
            int z = e.getPlayer().getLocation().getBlockZ();

            if (!rg.contains(x, y, z) && e.getPlayer().getInventory().getItem(0).getType() == pvp.getIsManager().getKitSelector().getType()) {
                pvp.getKitManager().giveKit(e.getPlayer(), pvp.getKitManager().getDefaultKits().get(0));
            }
        }
    }
}
