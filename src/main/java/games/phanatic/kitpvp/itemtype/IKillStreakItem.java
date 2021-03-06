package games.phanatic.kitpvp.itemtype;

import games.phanatic.kitpvp.api.IHardCodedAblity;
import games.phanatic.kitpvp.api.IItem;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Material;

import java.util.List;

public class IKillStreakItem extends IItem {

    @Getter
    @Setter
    private int minKS;

    @Getter
    @Setter
    private IHardCodedAblity ablity;

    public IKillStreakItem(Material mat, String name, int data, List<String> lore, int slot, int minKS, IHardCodedAblity ablity ) {
        super(name, mat, data, lore, slot);
        setMinKS(minKS);
        setAblity(ablity);
    }
}
