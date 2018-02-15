package games.phanatic.kitpvp.api;

import java.util.List;

public interface IKit {

    IItem icon();

    String name();

    int price();

    List<IItem> items();

    String permission();

    boolean isDefault();
}
