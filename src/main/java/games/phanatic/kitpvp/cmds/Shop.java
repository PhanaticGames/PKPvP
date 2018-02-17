package games.phanatic.kitpvp.cmds;

import code.matthew.psc.api.command.ICommand;
import games.phanatic.kitpvp.PKPvP;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Shop extends ICommand {

    private PKPvP pvp;

    public Shop(PKPvP pvp) {
        super("shop", "psc.shop", "Open the shop", true);
        this.pvp = pvp;
    }

    @Override
    public boolean finalExe(CommandSender sender, String[] args) {
        Player p = (Player) sender;
        pvp.getInvManager().openShop(p);
        return true;
    }
}
