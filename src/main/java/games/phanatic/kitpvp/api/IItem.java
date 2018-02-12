package games.phanatic.kitpvp.api;

import code.matthew.psc.utils.item.ItemBuilder;
import code.matthew.psc.utils.strings.ColorUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.List;

@AllArgsConstructor
public class IItem {

    @Getter
    @Setter
    String name;

    @Getter
    @Setter
    Material type;

    @Getter
    @Setter
    int data;

    @Getter
    @Setter
    List<String> lore;

    @Getter
    @Setter
    private int slot;

    public ItemStack toIS() {
        return new ItemBuilder(type, 1, (byte) data).setName(ColorUtil.colorStr(name)).setLore(ColorUtil.colorList(lore)).build();
    }

}
