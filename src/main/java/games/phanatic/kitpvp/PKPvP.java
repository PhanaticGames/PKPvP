package games.phanatic.kitpvp;

import code.matthew.psc.utils.core.CommandManager;
import games.phanatic.kitpvp.cmds.Setspawn;
import games.phanatic.kitpvp.listeners.EntityDeath;
import games.phanatic.kitpvp.listeners.PlayerDeath;
import games.phanatic.kitpvp.listeners.PlayerJoin;
import games.phanatic.kitpvp.listeners.PlayerLeave;
import games.phanatic.kitpvp.util.FileUtil;
import games.phanatic.kitpvp.util.ItemManager;
import games.phanatic.kitpvp.util.LocationManager;
import lombok.Getter;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class PKPvP extends JavaPlugin {

    @Getter
    private FileUtil fileUtil;

    @Getter
    private ItemManager isManager;

    @Getter
    private LocationManager locManager;

    @Override
    public void onEnable() {
        fileUtil = new FileUtil(this);
        isManager = new ItemManager(this);
        locManager = new LocationManager(this);
        regListeners();
        regCommands();
    }

    @Override
    public void onDisable() {

    }

    private void regListeners() {
        PluginManager manager = getServer().getPluginManager();
        manager.registerEvents(new EntityDeath(), this);
        manager.registerEvents(new PlayerDeath(this), this);
        manager.registerEvents(new PlayerJoin(this), this);
        manager.registerEvents(new PlayerLeave(), this);
    }

    private void regCommands() {
        CommandManager.regCommand(new Setspawn(this));
    }
}
