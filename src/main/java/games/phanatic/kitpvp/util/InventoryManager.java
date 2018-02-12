package games.phanatic.kitpvp.util;

import code.matthew.psc.utils.strings.ColorUtil;
import games.phanatic.kitpvp.PKPvP;
import games.phanatic.kitpvp.factory.KitSelectorItemFactory;
import games.phanatic.kitpvp.itemtype.IKillStreakItem;
import games.phanatic.kitpvp.itemtype.IKitSelectItem;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class InventoryManager {

    private Inventory kitSelector;
    private Inventory killStreaks;

    private List<IKillStreakItem> ksItems;
    private List<IKitSelectItem> kitItems;

    private PKPvP pvp;

    public InventoryManager(PKPvP pvp) {
        this.pvp = pvp;
        ksItems = new ArrayList<>();
        kitItems = new ArrayList<>();
        loadInvs();
    }

    public void loadInvs() {
        ConfigurationSection kitSection = pvp.getFileUtil().getKitSelector().getConfiguration().getConfigurationSection("inv.slots");
        kitSelector = Bukkit.createInventory(null,
                pvp.getFileUtil().getKitSelector().getInt("inv.size"),
                ColorUtil.colorStr(pvp.getFileUtil().getKitSelector().getString("inv.name")));
        // TODO: 2/12/2018 FIX THIS NASTY CODE 
        Set<String> kitKeys = kitSection.getKeys(false);
        Iterator<String> kitIt = kitKeys.iterator();

        while (kitIt.hasNext()) {
            String key = kitIt.next();
            String name = ColorUtil.colorStr(pvp.getFileUtil().getKitSelector().getString("inv.slots." + key + ".name"));
            Material mat = Material.valueOf(pvp.getFileUtil().getKitSelector().getString("inv.slots." + key + ".mat"));
            List<String> lore = pvp.getFileUtil().getKitSelector().getStringList("inv.slots." + key + ".lore");
            int data = pvp.getFileUtil().getKitSelector().getInt("inv.slots." + key + ".data");
            IKitSelectItem item = KitSelectorItemFactory.createKSItem(name, mat, data, lore, Integer.valueOf(key), "0");
            kitSelector.setItem(item.getSlot(), item.toIS());
        }
    }

    public void openKitSelector(Player p) {
        p.openInventory(kitSelector);
    }

    public void openKillsStreaks(Player p) {
        p.openInventory(killStreaks);
    }
}
