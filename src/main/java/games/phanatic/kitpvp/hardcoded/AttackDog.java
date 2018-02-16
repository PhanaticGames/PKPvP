package games.phanatic.kitpvp.hardcoded;

import games.phanatic.kitpvp.api.IHardCodedAblity;
import org.bukkit.entity.Player;

public class AttackDog extends IHardCodedAblity {

    public AttackDog() {
        setName("acc_dog");
    }

    @Override
    public void execute(Player p) {
        setPlayer(p);
        getPlayer().sendMessage("STARTING ACC_DOG");
    }
}
