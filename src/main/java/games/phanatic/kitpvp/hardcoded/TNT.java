package games.phanatic.kitpvp.hardcoded;

import games.phanatic.kitpvp.api.IHardCodedAblity;
import org.bukkit.entity.Player;

public class TNT extends IHardCodedAblity {

    public TNT() {
        setName("tnt_drp");
    }

    @Override
    public void execute(Player p) {
        setPlayer(p);
        getPlayer().sendMessage("This is currently under development");
    }

}
