package games.phanatic.kitpvp.cmds;

import code.matthew.psc.api.command.ICommand;
import games.phanatic.kitpvp.PKPvP;
import games.phanatic.kitpvp.util.FileUtil;
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
        pvp.getFileUtil().getSpawnConfig().set("x", p.getLocation().getX());
        pvp.getFileUtil().getSpawnConfig().set("y", p.getLocation().getY());
        pvp.getFileUtil().getSpawnConfig().set("z", p.getLocation().getZ());
        pvp.getFileUtil().getSpawnConfig().set("p", p.getLocation().getPitch());
        pvp.getFileUtil().getSpawnConfig().set("y", p.getLocation().getYaw());
        pvp.getFileUtil().saveSpawnConfig();
        p.sendMessage(ChatColor.AQUA + "Spawn was set");
        return true;
    }
}
