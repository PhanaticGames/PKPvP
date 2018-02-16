package games.phanatic.kitpvp.factory;

import games.phanatic.kitpvp.api.IHardCodedAblity;
import games.phanatic.kitpvp.itemtype.IKillStreakItem;
import org.bukkit.Material;

import java.util.List;

public class KillStreakItemFactory {

    public static IKillStreakItem createKSItem(String name, Material mat, int data, List<String> lore, int slot, int minKS, IHardCodedAblity ablity) {
        return new IKillStreakItem(mat, name, data, lore, slot, minKS, ablity);
    }
}
