package games.phanatic.kitpvp.hardcoded;

import games.phanatic.kitpvp.api.IHardCodedAblity;
import org.bukkit.entity.Player;

public class SupplyDrop extends IHardCodedAblity {

    public SupplyDrop() {
        setName("spp_drp");
    }

    @Override
    public void execute(Player p) {
        setPlayer(p);
        getPlayer().sendMessage("This is currently in development");
    }
}
