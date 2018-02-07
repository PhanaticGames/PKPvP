package games.phanatic.kitpvp;

import games.phanatic.kitpvp.listeners.EntityDeath;
import games.phanatic.kitpvp.listeners.PlayerDeath;
import games.phanatic.kitpvp.listeners.PlayerJoin;
import games.phanatic.kitpvp.listeners.PlayerLeave;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class PKPvP extends JavaPlugin {

    @Override
    public void onEnable() {

        regListeners();
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
}
