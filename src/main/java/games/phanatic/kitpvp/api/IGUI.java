package games.phanatic.kitpvp.api;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class IGUI {

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private int size;

    @Getter
    @Setter
    private List<IItem> items;

}
