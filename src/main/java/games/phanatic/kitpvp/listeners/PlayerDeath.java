package games.phanatic.kitpvp.listeners;

import games.phanatic.kitpvp.PKPvP;
import net.minecraft.server.v1_8_R3.PacketPlayInClientCommand;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PlayerDeath implements Listener {

    private PKPvP pvp;

    public PlayerDeath(PKPvP pvp) {
        this.pvp = pvp;
    }

    @EventHandler
    public void execute(PlayerDeathEvent e) {
        Player killed = e.getEntity();
        Player killer = e.getEntity().getKiller();
        e.getDrops().clear();
        e.setDroppedExp(0);
        Bukkit.getScheduler().scheduleSyncDelayedTask(pvp, () -> {
            ((CraftPlayer) killed).getHandle().playerConnection.a(new PacketPlayInClientCommand(PacketPlayInClientCommand.EnumClientCommand.PERFORM_RESPAWN));
            killed.teleport(pvp.getLocManager().getSpawn(), PlayerTeleportEvent.TeleportCause.PLUGIN);
            pvp.getIsManager().setPlayersInv(killed);
            killed.setFireTicks(0);
            killed.getActivePotionEffects().clear();
            pvp.getTmpDatManager().removeKS(killed);
            pvp.getTmpDatManager().addKS(killer);
            pvp.getTmpDatManager().addCoins(killer, pvp.getConfig().getInt("coinsPerKill"));
            String potEffectName = pvp.getFileUtil().getConfig().getString("killEffect");
            int timer = pvp.getFileUtil().getConfig().getInt("killEffectTime");
            int amp = pvp.getFileUtil().getConfig().getInt("killEffectAmp");
            killer.addPotionEffect(new PotionEffect(PotionEffectType.getByName(potEffectName), timer, amp));
        }, 12L);
    }
}
