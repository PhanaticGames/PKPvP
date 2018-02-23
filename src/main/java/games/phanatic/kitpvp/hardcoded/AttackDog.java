package games.phanatic.kitpvp.hardcoded;

import games.phanatic.kitpvp.PKPvP;
import games.phanatic.kitpvp.api.IHardCodedAblity;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;
import org.bukkit.metadata.FixedMetadataValue;

public class AttackDog extends IHardCodedAblity {

    private PKPvP pvp;

    public AttackDog(PKPvP pvp) {
        setName("acc_dog");
        this.pvp = pvp;
    }

    @Override
    public void execute(Player p) {
        setPlayer(p);

        for (int i = 0; i < 5; i++) {
            Wolf wolf = getPlayer().getWorld().spawn(getPlayer().getLocation(), Wolf.class);
            wolf.setTamed(true);
            wolf.setOwner(getPlayer());
            wolf.setMetadata("owner", new FixedMetadataValue(pvp, p.getName()));
            wolf.setCustomNameVisible(true);
            wolf.setCustomName(ChatColor.RED + "Attack dog");
            wolf.setCollarColor(DyeColor.CYAN);
            pvp.getEntityTracker().addEntityToPlayer(getPlayer(), wolf);
        }

    }
}
