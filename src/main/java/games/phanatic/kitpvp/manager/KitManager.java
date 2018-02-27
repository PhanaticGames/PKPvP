package games.phanatic.kitpvp.manager;

import games.phanatic.kitpvp.PKPvP;
import games.phanatic.kitpvp.api.IItem;
import games.phanatic.kitpvp.api.IKit;
import games.phanatic.kitpvp.factory.KitFactory;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class KitManager {

    private PKPvP pvp;

    private List<IKit> kits;
    private List<IKit> defaultKits;

    public KitManager(PKPvP pvp) {
        this.pvp = pvp;
        defaultKits = new ArrayList<>();
        loadKits();
    }

    private void loadKits() {
        kits = new ArrayList<>();
        ConfigurationSection kits = pvp.getFileUtil().getKitConfig().getConfiguration().getConfigurationSection("kits");
        Set<String> kitsKeys = kits.getKeys(false);

        for (String name : kitsKeys) {
            String perm = pvp.getFileUtil().getKitConfig().getString("kits." + name + ".permission");
            int price = pvp.getFileUtil().getKitConfig().getInt("kits." + name + ".price");
            boolean isDefault = pvp.getFileUtil().getKitConfig().getBoolean("kits." + name + ".default");
            IItem icon = loadItem(pvp.getFileUtil().getKitConfig().getConfiguration(), "kits." + name + ".icon");
            List<IItem> items = new ArrayList<>();

            for (String s : pvp.getFileUtil().getKitConfig().getConfiguration().getConfigurationSection("kits." + name + ".items").getKeys(false)) {
                items.add(loadItem(pvp.getFileUtil().getKitConfig().getConfiguration(), "kits." + name + ".items." + s));
            }
            IKit kit = KitFactory.genKit(icon, name, items, price, perm, isDefault);
            this.kits.add(kit);
            if (isDefault) {
                defaultKits.add(kit);
            }
        }
    }

    public List<IKit> getPlayerKits(Player p) {
        List<IKit> pKits = new ArrayList<>();

        for (IKit kit : kits) {
            if (kit.isDefault()) {
                pKits.add(kit);
            } else if (kit.permission() != null && p.hasPermission(kit.permission())) {
                pKits.add(kit);
            }
        }

        return pKits;
    }

    public List<IKit> getKits() {
        return kits;
    }

    public void giveKit(Player p, IKit kit) {
        p.getInventory().clear();
        p.getInventory().setArmorContents(null);
        p.getActivePotionEffects().clear();
        p.setFireTicks(0);
        for (IItem item : kit.items()) {
            p.getInventory().setItem(item.getSlot(), item.toIS());
        }
        p.getInventory().setItem(8, pvp.getIsManager().getKillStreaks());
    }

    private IItem loadItem(FileConfiguration config, String path) {
        Material mat = Material.valueOf(config.getString(path + ".mat"));
        int data = config.getInt(path + ".data");
        String name = config.getString(path + ".name");
        List<String> lore = config.getStringList(path + ".lore");
        int slot = config.getInt(path + ".slot");
        return new IItem(name, mat, data, lore, slot);
    }

    public List<IKit> getDefaultKits() {
        return defaultKits;
    }

}
