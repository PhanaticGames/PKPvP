package games.phanatic.kitpvp.factory;

import games.phanatic.kitpvp.api.IItem;
import games.phanatic.kitpvp.api.IKit;

import java.util.List;

public class KitFactory {

    public static IKit genKit(IItem icon, String name, List<IItem> kitItems, int price, String perm, boolean isDefault) {
        return new IKit() {
            @Override
            public IItem icon() {
                return icon;
            }

            @Override
            public String name() {
                return name;
            }

            @Override
            public int price() {
                return price;
            }

            @Override
            public List<IItem> items() {
                return kitItems;
            }

            @Override
            public String permission() {
                return perm;
            }

            @Override
            public boolean isDefault() {
                return isDefault;
            }
        };
    }
}
