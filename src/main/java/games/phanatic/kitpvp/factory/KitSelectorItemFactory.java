package games.phanatic.kitpvp.factory;

import games.phanatic.kitpvp.itemtype.IKitSelectItem;
import org.bukkit.Material;

import java.util.List;

public class KitSelectorItemFactory {

    public static IKitSelectItem createKSItem(String name, Material mat, int data, List<String> lore, int slot, String perm) {
        return new IKitSelectItem(mat, name, data, lore, slot, perm);
    }
}
