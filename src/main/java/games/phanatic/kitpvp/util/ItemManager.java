package games.phanatic.kitpvp.util;

import code.matthew.psc.utils.item.ItemBuilder;
import code.matthew.psc.utils.strings.ColorUtil;
import games.phanatic.kitpvp.PKPvP;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class ItemManager {

    private ItemStack kitSelector;
    private ItemStack killStreaks;

    private PKPvP pvp;

    public ItemManager(PKPvP pvp) {
        this.pvp = pvp;
        loadItems();
    }

    public void loadItems() {
        String kitMat = pvp.getFileUtil().getItems().getString("kitselector.mat");
        int kitCount = pvp.getFileUtil().getItems().getInt("kitselector.count");
        int kitData = pvp.getFileUtil().getItems().getInt("kitselector.data");
        String kitName = pvp.getFileUtil().getItems().getString("kitselector.name");
        List<String> kitLore = pvp.getFileUtil().getItems().getStringList("kitselector.lore");
        kitSelector = new ItemBuilder(Material.valueOf(kitMat), kitCount, (byte) kitData).setName(ColorUtil.colorStr(kitName)).setLore(ColorUtil.colorList(kitLore)).build();

        String ksMat = pvp.getFileUtil().getItems().getString("ks.mat");
        int ksCount = pvp.getFileUtil().getItems().getInt("ks.count");
        int ksData = pvp.getFileUtil().getItems().getInt("ks.data");
        String ksName = pvp.getFileUtil().getItems().getString("ks.name");
        List<String> ksLore = pvp.getFileUtil().getItems().getStringList("ks.lore");
        kitSelector = new ItemBuilder(Material.valueOf(ksMat), ksCount, (byte) ksData).setName(ColorUtil.colorStr(ksName)).setLore(ColorUtil.colorList(ksLore)).build();

    }

    public ItemStack getKitSelector() {
        if(kitSelector == null) {
            loadItems();
        }
        return kitSelector;
    }

    public ItemStack getKillStreaks() {
        if(killStreaks == null) {
            loadItems();
        }
        return killStreaks;
    }
}
