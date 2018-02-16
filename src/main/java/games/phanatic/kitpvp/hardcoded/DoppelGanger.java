package games.phanatic.kitpvp.hardcoded;

import games.phanatic.kitpvp.api.IHardCodedAblity;
import org.bukkit.entity.Player;

public class DoppelGanger extends IHardCodedAblity {

    public DoppelGanger() {
        setName("dop_gan");
    }

    @Override
    public void execute(Player p) {
        setPlayer(p);
        getPlayer().sendMessage("STARTING DROP_GAN");
    }
}
