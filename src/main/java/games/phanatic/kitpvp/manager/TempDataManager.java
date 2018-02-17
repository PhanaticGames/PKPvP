package games.phanatic.kitpvp.manager;

import games.phanatic.kitpvp.PKPvP;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class TempDataManager {

    private HashMap<Player, Integer> killStreaks;
    private HashMap<Player, Integer> coins;

    private PKPvP pvp;

    public TempDataManager(PKPvP pvp) {
        this.pvp = pvp;
        killStreaks = new HashMap<>();
        coins = new HashMap<>();
    }

    public void addCoins(Player p, int amount) {
        if (!coins.containsKey(p)) {
            loadPlayerCoins(p);
        }
        coins.replace(p, coins.get(p) + amount);
    }

    public void removeCoins(Player p, int amount) {
        if (!coins.containsKey(p)) {
            loadPlayerCoins(p);
        }
        coins.replace(p, coins.get(p) - amount);
    }


    public void loadPlayerCoins(Player p) {
        if (pvp.getFileUtil().getCoinConfig().contains(p.getUniqueId().toString())) {
            coins.put(p, pvp.getFileUtil().getKitConfig().getInt(p.getUniqueId().toString()));
        } else {
            coins.put(p, 0);
        }
    }

    public void saveCoins() {
        for(Player p : coins.keySet()) {
            pvp.getFileUtil().getCoinConfig().set(p.getUniqueId().toString(), coins.get(p));
        }
        pvp.getFileUtil().saveCoins();
    }

    public void setupKS(Player p) {
        killStreaks.put(p, 0);
    }

    public void addKS(Player p) {
        if (killStreaks.containsKey(p)) {
            killStreaks.replace(p, killStreaks.get(p) + 1);
        } else {
            killStreaks.put(p, 1);
        }
    }

    public void removeKS(Player p) {
        if (killStreaks.containsKey(p)) {
            killStreaks.remove(p);
        }
    }

    public boolean canAfford(Player p, int price) {
        return killStreaks.get(p) >= price;
    }

    public void subtractKS(Player p, int amount) {
        if (killStreaks.containsKey(p)) {
            killStreaks.replace(p, killStreaks.get(p) - amount);
        }
    }

}
