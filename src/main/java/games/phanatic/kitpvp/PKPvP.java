package games.phanatic.kitpvp;

import code.matthew.psc.utils.core.CommandManager;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import games.phanatic.kitpvp.cmds.Dev;
import games.phanatic.kitpvp.cmds.Setspawn;
import games.phanatic.kitpvp.cmds.Shop;
import games.phanatic.kitpvp.hardcoded.AttackDog;
import games.phanatic.kitpvp.hardcoded.DoppelGanger;
import games.phanatic.kitpvp.hardcoded.SupplyDrop;
import games.phanatic.kitpvp.hardcoded.TNT;
import games.phanatic.kitpvp.listeners.*;
import games.phanatic.kitpvp.manager.*;
import games.phanatic.kitpvp.run.CoinSave;
import games.phanatic.kitpvp.util.FileUtil;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

public class PKPvP extends JavaPlugin {

    @Getter
    private FileUtil fileUtil;

    @Getter
    private ItemManager isManager;

    @Getter
    private LocationManager locManager;

    @Getter
    private InventoryManager invManager;

    @Getter
    private KitManager kitManager;

    @Getter
    private TempDataManager tmpDatManager;

    @Getter
    private HardCodedAblitys ablitys;

    @Getter
    private EntityTracker entityTracker;

    @Override
    public void onEnable() {
        fileUtil = new FileUtil(this);
        ablitys = new HardCodedAblitys(this);
        ablitys.registerHardCodedAbility(new TNT());
        ablitys.registerHardCodedAbility(new DoppelGanger());
        ablitys.registerHardCodedAbility(new SupplyDrop());
        ablitys.registerHardCodedAbility(new AttackDog(this));
        isManager = new ItemManager(this);
        locManager = new LocationManager(this);
        invManager = new InventoryManager(this);
        kitManager = new KitManager(this);
        tmpDatManager = new TempDataManager(this);
        entityTracker = new EntityTracker();
        regListeners();
        regCommands();
        loadRunnables();
    }

    @Override
    public void onDisable() {
        tmpDatManager.saveCoins();
    }

    private void regListeners() {
        PluginManager manager = getServer().getPluginManager();
        manager.registerEvents(new EntityDeath(this), this);
        manager.registerEvents(new PlayerDeath(this), this);
        manager.registerEvents(new PlayerJoin(this), this);
        manager.registerEvents(new PlayerLeave(), this);
        manager.registerEvents(new Interact(this), this);
        manager.registerEvents(new PlayerHunger(), this);
        manager.registerEvents(new InventoryClick(this), this);
        manager.registerEvents(new Drop(this), this);
        manager.registerEvents(new BlockPlace(this), this);
        manager.registerEvents(new BlockBreak(), this);
    }

    private void regCommands() {
        CommandManager.regCommand(new Setspawn(this));
        CommandManager.regCommand(new Dev(this));
        CommandManager.regCommand(new Shop(this));
    }

    private void loadRunnables() {
        BukkitScheduler scheduler = getServer().getScheduler();
        long coinSave = getFileUtil().getConfig().getInt("datSync");
        coinSave = coinSave * 20;
        scheduler.scheduleAsyncRepeatingTask(this, new CoinSave(this), coinSave, coinSave);
    }

    public WorldGuardPlugin getWorldGuard() {
        Plugin plugin = getServer().getPluginManager().getPlugin("WorldGuard");

        if (plugin == null || !(plugin instanceof WorldGuardPlugin)) {
            System.out.println("ERROR, WORLDGUARD WAS NOT FOUND. WE NEED THIS TO OPERATE!!!");
            System.out.println("PkPvP is now disableing....");
            Bukkit.getPluginManager().disablePlugin(this);
            return null;
        }
        return (WorldGuardPlugin) plugin;
    }
}
