package games.phanatic.kitpvp.itemtype;

import games.phanatic.kitpvp.api.IItem;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Material;

import java.util.List;

public class IKitSelectItem extends IItem {

    @Getter
    @Setter
    private String perm;

    public IKitSelectItem(Material mat, String name, int data, List<String> lore, int slot, String perm) {
        super(name, mat, data, lore, slot);
        setPerm(perm);
    }
}
