package games.phanatic.kitpvp.util;

import code.matthew.psc.api.file.ConfigFile;
import games.phanatic.kitpvp.PKPvP;
import lombok.Getter;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileUtil {

    private File spawnFile;

    @Getter
    private FileConfiguration spawnConfig;

    private File coinFile;

    @Getter
    private FileConfiguration coinConfig;

    @Getter
    private ConfigFile items;

    @Getter
    private ConfigFile killStreaks;

    @Getter
    private ConfigFile kitSelector;

    @Getter
    private ConfigFile kitConfig;

    @Getter
    private ConfigFile messages;

    @Getter
    private ConfigFile config;

    private PKPvP pvp;

    public FileUtil(PKPvP pvp) {
        this.pvp = pvp;

        spawnFile = new File(pvp.getDataFolder() + File.separator + "spawn.yml");
        coinFile = new File(pvp.getDataFolder() + File.separator  + "coins.dat");
        checkFileIntegrity();

        items = new ConfigFile(pvp, "items.yml");
        items.setup();

        kitSelector = new ConfigFile(pvp, "kitselector.yml");
        kitSelector.setup();

        killStreaks = new ConfigFile(pvp, "killstreaks.yml");
        killStreaks.setup();

        kitConfig = new ConfigFile(pvp, "kits.yml");
        kitConfig.setup();

        messages = new ConfigFile(pvp, "messages.yml");
        messages.setup();

        config = new ConfigFile(pvp, "config.yml");
        config.setup();

        reload();
    }

    private void checkFileIntegrity() {
        if(!pvp.getDataFolder().exists()) {
            if(pvp.getDataFolder().mkdirs()) {
                System.out.println("Created data directory...");
            } else {
                System.out.println("Data directory did not exist and failed to be created. Be prepared for errors...");
            }
        }
        if(!spawnFile.exists()) {
            // Set override to true bc if it exist and we somehow get here, there is an issue...
            pvp.saveResource("spawn.yml", true);
        }
        if(coinFile.exists()) {
            try {
                coinFile.createNewFile();
            }catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void reload() {
        spawnConfig = loadConfiguration(spawnFile);
        coinConfig = loadConfiguration(coinFile);
        items.reload();
        kitSelector.reload();
        killStreaks.reload();
        messages.reload();
        config.reload();
        kitConfig.reload();
    }

    public void saveSpawnConfig() {
        try {
            spawnConfig.save(spawnFile);
        } catch (IOException ex) {
            System.err.println("IOException caught when saving spawn file...");
            ex.printStackTrace();
        }
    }

    public void saveCoins() {
        try {
            coinConfig.save(coinFile);
        } catch (IOException ex) {
            System.err.println("IOException caught when saving coins file...");
            ex.printStackTrace();
        }
    }

    /**
     * Overriden from YamlConfiguration.loadConfiguration
     * We want to print exceptions
     * @param file The file to load
     * @return YamlConfiguration
     */
    public YamlConfiguration loadConfiguration(File file) {
        YamlConfiguration config = new YamlConfiguration();
        try {
            config.load(file);
        } catch (FileNotFoundException ex) {
            System.err.println("FileNotFoundException caught when attempting to load config...");
            ex.printStackTrace();
        } catch (IOException ex) {
            System.err.println("IOException caught when attempting to load config...");
            ex.printStackTrace();
        } catch (InvalidConfigurationException ex) {
            System.err.println("InvalidConfigurationException caught when attempting to load config...");
            ex.printStackTrace();
        }

        return config;
    }
}
