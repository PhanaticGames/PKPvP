package games.phanatic.kitpvp.listeners;

import code.matthew.psc.api.player.PlayerUtils;
import code.matthew.psc.utils.strings.ColorUtil;
import games.phanatic.kitpvp.PKPvP;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.metadata.MetadataValue;

public class EntityDeath implements Listener {

    private PKPvP pvp;

    public EntityDeath(PKPvP pvp) {
        this.pvp = pvp;
    }

    @EventHandler
    public void execute(EntityDeathEvent e) {
        if (e.getEntityType() == EntityType.WOLF) {
            if (e.getEntity().hasMetadata("owner")) {
                MetadataValue value = e.getEntity().getMetadata("owner").get(0);
                Player owner = PlayerUtils.getOnlinePlayer(value.asString());
                owner.sendMessage(ColorUtil.colorStr(pvp.getFileUtil().getMessages().getString("dogKilled").replaceAll("%KILLER%", e.getEntity().getKiller().getName())));
            }
        }
    }
}
