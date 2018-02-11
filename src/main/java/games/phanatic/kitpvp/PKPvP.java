package games.phanatic.kitpvp;

import code.matthew.psc.utils.core.CommandManager;
import games.phanatic.kitpvp.cmds.Setspawn;
import games.phanatic.kitpvp.listeners.EntityDeath;
import games.phanatic.kitpvp.listeners.PlayerDeath;
import games.phanatic.kitpvp.listeners.PlayerJoin;
import games.phanatic.kitpvp.listeners.PlayerLeave;
import games.phanatic.kitpvp.util.FileUtil;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class PKPvP extends JavaPlugin {

    private FileUtil fileUtil;

    @Override
    public void onEnable() {
        fileUtil = new FileUtil(this);
        regListeners();
        regCommands();
    }

    @Override
    public void onDisable() {

    }

    private void regListeners() {
        PluginManager manager = getServer().getPluginManager();
        manager.registerEvents(new EntityDeath(), this);
        manager.registerEvents(new PlayerDeath(), this);
        manager.registerEvents(new PlayerJoin(), this);
        manager.registerEvents(new PlayerLeave(), this);
    }

    private void regCommands() {
        CommandManager.regCommand(new Setspawn(this));
    }

    public FileUtil getFileUtil() {
        return fileUtil;
    }
}
