package games.phanatic.kitpvp.manager;

import code.matthew.psc.utils.strings.ColorUtil;
import games.phanatic.kitpvp.PKPvP;
import games.phanatic.kitpvp.api.IHardCodedAblity;
import games.phanatic.kitpvp.api.IKit;
import games.phanatic.kitpvp.factory.KillStreakItemFactory;
import games.phanatic.kitpvp.factory.KitSelectorItemFactory;
import games.phanatic.kitpvp.itemtype.IKillStreakItem;
import games.phanatic.kitpvp.itemtype.IKitSelectItem;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class InventoryManager {

    private Inventory kitSelector;
    private Inventory killStreaks;
    private List<IKillStreakItem> ksItems;
    private List<IKitSelectItem> kitItems;

    private HashMap<String, Inventory> playerInv;
    private PKPvP pvp;

    public InventoryManager(PKPvP pvp) {
        this.pvp = pvp;
        ksItems = new ArrayList<>();
        kitItems = new ArrayList<>();
        playerInv = new HashMap<>();
        loadInvs();
    }

    public void loadInvs() {
        ConfigurationSection kitSection = pvp.getFileUtil().getKitSelector().getConfiguration().getConfigurationSection("inv.slots");
        kitSelector = Bukkit.createInventory(null,
                pvp.getFileUtil().getKitSelector().getInt("inv.size"),
                ColorUtil.colorStr(pvp.getFileUtil().getKitSelector().getString("inv.name")));
        // TODO: 2/12/2018 FIX THIS NASTY CODE
        Set<String> kitKeys = kitSection.getKeys(false);

        for (String s : kitKeys) {
            String name = ColorUtil.colorStr(pvp.getFileUtil().getKitSelector().getString("inv.slots." + s + ".name"));
            Material mat = Material.valueOf(pvp.getFileUtil().getKitSelector().getString("inv.slots." + s + ".mat"));
            List<String> lore = pvp.getFileUtil().getKitSelector().getStringList("inv.slots." + s + ".lore");
            int data = pvp.getFileUtil().getKitSelector().getInt("inv.slots." + s + ".data");
            IKitSelectItem item = KitSelectorItemFactory.createKSItem(name, mat, data, lore, Integer.valueOf(s), "0");
            kitSelector.setItem(item.getSlot(), item.toIS());
            kitItems.add(item);
        }

        ConfigurationSection ksSection = pvp.getFileUtil().getKillStreaks().getConfiguration().getConfigurationSection("inv.slots");
        killStreaks = Bukkit.createInventory(null,
                pvp.getFileUtil().getKillStreaks().getInt("inv.size"),
                ColorUtil.colorStr(pvp.getFileUtil().getKillStreaks().getString("inv.name")));
        // TODO: 2/12/2018 FIX THIS NASTY CODE
        Set<String> ksKeys = ksSection.getKeys(false);

        for (String s : ksKeys) {
            String name = ColorUtil.colorStr(pvp.getFileUtil().getKillStreaks().getString("inv.slots." + s + ".name"));
            Material mat = Material.valueOf(pvp.getFileUtil().getKillStreaks().getString("inv.slots." + s + ".mat"));
            List<String> lore = pvp.getFileUtil().getKillStreaks().getStringList("inv.slots." + s + ".lore");
            int data = pvp.getFileUtil().getKillStreaks().getInt("inv.slots." + s + ".data");
            int minKs = pvp.getFileUtil().getKillStreaks().getInt("inv.slots." + s + ".requriedStreak");
            IHardCodedAblity ability = pvp.getAblitys().getAbility(pvp.getFileUtil().getKillStreaks().getString("inv.slots." + s + ".interalID"));
            IKillStreakItem item = KillStreakItemFactory.createKSItem(name, mat, data, lore, Integer.valueOf(s), minKs, ability);
            killStreaks.setItem(item.getSlot(), item.toIS());
            ksItems.add(item);
        }
    }

    public void openShop(Player p) {
        List<IKit> dontGot = new ArrayList<>();
        for (IKit kit : pvp.getKitManager().getKits()) {
            if (!p.hasPermission(kit.permission())) {
                dontGot.add(kit);
            }
        }

        Inventory shop = Bukkit.createInventory(null, determineShopSize(dontGot.size()), ColorUtil.colorStr(pvp.getFileUtil().getConfig().getString("shopName")));
        for (IKit kit : dontGot) {
            shop.addItem(kit.icon().toIS());
        }
        p.openInventory(shop);
    }

    private int determineShopSize(int number) {
        if (number <= 9) {
            return 9;
        } else if (number <= 18) {
            return 18;
        } else if (number <= 27) {
            return 27;
        } else if (number <= 36) {
            return 36;
        } else if (number <= 45) {
            return 45;
        } else if (number <= 54) {
            return 54;
        } else {
            return 9;
        }
    }

    public void openKitSelector(Player p) {
        if (kitSelector == null) {
            loadInvs();
        }
        if (playerInv.containsKey(p.getDisplayName())) {
            p.openInventory(playerInv.get(p.getDisplayName()));
        } else {
            Inventory inv = Bukkit.createInventory(null, kitSelector.getSize(), kitSelector.getName());

            for (int i = 0; i < kitSelector.getContents().length; i++) {
                inv.setItem(i, kitSelector.getItem(i));
            }
            for (IKit kit : pvp.getKitManager().getPlayerKits(p)) {
                inv.addItem(kit.icon().toIS());
            }
            p.openInventory(inv);
            playerInv.put(p.getName(), inv);
        }
    }

    public void reloadPlayerKits(Player p) {
        if (playerInv.containsKey(p.getDisplayName())) {
            //   Inventory inv = kitSelector;
            //   for (IKit kit : pvp.getKitManager().getPlayerKits(p)) {
            //      inv.addItem(kit.icon().toIS());
            //  }
            playerInv.remove(p.getName());
            //  playerInv.put(p.getName(), inv);
        }
    }

    public void openKillsStreaks(Player p) {
        p.openInventory(killStreaks);
    }

    public Inventory getKillStreaks() {
        return killStreaks;
    }

    public List<IKillStreakItem> getKillStreakItems() {
        return ksItems;
    }

    public Inventory getKitSelector() {
        return kitSelector;
    }
}
