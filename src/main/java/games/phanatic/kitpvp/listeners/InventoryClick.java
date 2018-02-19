package games.phanatic.kitpvp.listeners;

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

        if (e.getCurrentItem() == null && e.getCurrentItem().getType() == null) {
            return;
        }

        Material clickedMat = e.getCurrentItem().getType();
        String clickedName = ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName());

        if (invName.equalsIgnoreCase(ChatColor.stripColor(pvp.getInvManager().getKitSelector().getName()))) {
            e.setCancelled(true);
            for (IKit kit : pvp.getKitManager().getKits()) {
                ItemStack icon = kit.icon().toIS();
                String iconCompareName = ChatColor.stripColor(icon.getItemMeta().getDisplayName());
                if (icon.getType() == clickedMat && iconCompareName.equalsIgnoreCase(clickedName)) {
                    pvp.getKitManager().giveKit(p, kit);
                    p.closeInventory();
                    p.sendMessage(ColorUtil.colorStr(pvp.getFileUtil().getMessages().getString("givenKit")));
                }
            }
        } else if (invName.equalsIgnoreCase(ChatColor.stripColor(pvp.getInvManager().getKillStreaks().getName()))) {
            e.setCancelled(true);
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
        } else if(invName.equalsIgnoreCase(ChatColor.stripColor(pvp.getFileUtil().getConfig().getString("shopName")))) {
            e.setCancelled(true);
            for(IKit kit : pvp.getKitManager().getKits()) {
                if(clickedMat == kit.icon().toIS().getType()) {
                    p.closeInventory();
                    if(pvp.getTmpDatManager().canAffordCoins(p, kit.price())) {
                        pvp.getTmpDatManager().removeCoins(p, kit.price());
                        p.sendMessage(ColorUtil.colorStr(pvp.getFileUtil().getMessages().getString("kitBought")));
                        // TODO: 2/18/2018 GIVE PERMISSION 
                    } else {
                        p.sendMessage(ColorUtil.colorStr(pvp.getFileUtil().getMessages().getString("notEnouchCoins")));
                    }
                }
            }
        }
    }
}
