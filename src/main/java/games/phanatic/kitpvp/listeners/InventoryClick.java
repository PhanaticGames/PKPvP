package games.phanatic.kitpvp.listeners;

import code.matthew.psc.PSC;
import code.matthew.psc.utils.strings.ColorUtil;
import games.phanatic.kitpvp.PKPvP;
import games.phanatic.kitpvp.api.IKit;
import games.phanatic.kitpvp.itemtype.IKillStreakItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class InventoryClick implements Listener {

    private PKPvP pvp;

    public InventoryClick(PKPvP pvp) {
        this.pvp = pvp;
    }

    @EventHandler
    public void onInvClick(InventoryClickEvent e) {
        String invName = ChatColor.stripColor(e.getInventory().getName());
        Player p = (Player) e.getWhoClicked();

        if (e.getCurrentItem() != null && e.getCurrentItem().getType() != null) {

            Material clickedMat = e.getCurrentItem().getType();
            String clickedName = ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName());

            String kitSelector = ChatColor.stripColor(pvp.getInvManager().getKitSelector().getName());
            String killStreaks = ChatColor.stripColor(pvp.getInvManager().getKillStreaks().getName());
            String shopName = ColorUtil.colorStr(pvp.getFileUtil().getConfig().getString("shopName"));
            shopName = ChatColor.stripColor(shopName);

            if (invName.equalsIgnoreCase(kitSelector)) {
                e.setCancelled(true);
                executeKitSelector(clickedName, clickedMat, p);
            } else if (invName.equalsIgnoreCase(killStreaks)) {
                e.setCancelled(true);
                executeKillStreaks(clickedName, clickedMat, p);
                // This line is stupid ik. The issue is, config has the color value, so i have to color, then uncolor to compare correctly
            } else if (invName.equalsIgnoreCase(shopName)) {
                e.setCancelled(true);
                executeShop(clickedMat, p);
            }
        }
    }

    private void executeShop(Material clickedMat, Player p) {
        System.out.println("EXTERNAL METHOD BEING CALLED");
        for (IKit kit : pvp.getKitManager().getKits()) {
            if (clickedMat == kit.icon().toIS().getType()) {
                p.closeInventory();
                if (pvp.getTmpDatManager().canAffordCoins(p, kit.price())) {
                    pvp.getTmpDatManager().removeCoins(p, kit.price());
                    p.sendMessage(ColorUtil.colorStr(pvp.getFileUtil().getMessages().getString("kitBought")));
                    PSC.getPerms().playerAdd(null, p, kit.permission());
                } else {
                    p.sendMessage(ColorUtil.colorStr(pvp.getFileUtil().getMessages().getString("notEnouchCoins")));
                }
            }
        }
    }

    private void executeKillStreaks(String clickedName, Material clickedMat, Player p) {
        for (IKillStreakItem item : pvp.getInvManager().getKillStreakItems()) {
            Material mat = item.getType();
            String name = ChatColor.stripColor(item.getName());
            if (clickedMat == mat && clickedName.equalsIgnoreCase(name)) {
                p.closeInventory();
                if (pvp.getTmpDatManager().canAffordKS(p, item.getMinKS())) {
                    pvp.getTmpDatManager().subtractKS(p, item.getMinKS());
                    item.getAblity().execute(p);
                    p.sendMessage(ColorUtil.colorStr(pvp.getFileUtil().getMessages().getString("givenKsReward")));
                } else {
                    p.sendMessage(ColorUtil.colorStr(pvp.getFileUtil().getMessages().getString("notEnoughKS")));
                }
            }
        }
    }

    private void executeKitSelector(String clickedName, Material clickedMat, Player p) {
        for (IKit kit : pvp.getKitManager().getKits()) {
            ItemStack icon = kit.icon().toIS();
            String iconCompareName = ChatColor.stripColor(icon.getItemMeta().getDisplayName());
            if (icon.getType() == clickedMat && iconCompareName.equalsIgnoreCase(clickedName)) {
                pvp.getKitManager().giveKit(p, kit);
                p.closeInventory();
                p.sendMessage(ColorUtil.colorStr(pvp.getFileUtil().getMessages().getString("givenKit")));
            }
        }
    }
}
