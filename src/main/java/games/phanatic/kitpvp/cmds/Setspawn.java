package games.phanatic.kitpvp.cmds;

import code.matthew.psc.api.command.ICommand;
import games.phanatic.kitpvp.PKPvP;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Setspawn extends ICommand {

    private PKPvP pvp;

    public Setspawn(PKPvP pvp) {
        super("setspawn", "pkpvp.setspawn", "Set the spawn", true);
        this.pvp = pvp;
    }

    @Override
    public boolean finalExe(CommandSender sender, String[] args) {
        Player p = (Player) sender;
        pvp.getLocManager().setSpawn(p.getLocation());
        pvp.getLocManager().saveSpawn();
        p.sendMessage(ChatColor.AQUA + "Spawn was set");
        return true;
    }
}
