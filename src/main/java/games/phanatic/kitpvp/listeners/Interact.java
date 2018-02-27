package games.phanatic.kitpvp.listeners;

import games.phanatic.kitpvp.PKPvP;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class Interact implements Listener {

    private PKPvP pvp;

    public Interact(PKPvP pvp) {
        this.pvp = pvp;
    }

    @EventHandler
    public void execute(PlayerInteractEvent e) {
        if (e.getItem() == null) {
            return;
        }
        if (e.getAction() == Action.LEFT_CLICK_BLOCK || e.getAction() == Action.LEFT_CLICK_AIR) {
            if (e.getPlayer().getItemInHand().getType() == Material.COOKED_BEEF) {
                e.getPlayer().setHealth(e.getPlayer().getHealth() + 3F);
                ItemStack stack = e.getItem();
                if (stack.getAmount() >= 2) {
                    ItemStack newStack = new ItemStack(Material.COOKED_BEEF, stack.getAmount() - 1);
                    e.getPlayer().setItemInHand(newStack);
                } else {
                    e.getPlayer().setItemInHand(null);
                }
                e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.EAT, 1.0F, 1.0F);
            }
        }
        if (e.getItem().getType() == pvp.getIsManager().getKitSelector().getType()) {
            pvp.getInvManager().openKitSelector(e.getPlayer());
        } else if (e.getItem().getType() == pvp.getIsManager().getKillStreaks().getType()) {
            pvp.getInvManager().openKillsStreaks(e.getPlayer());
        }
    }
}
