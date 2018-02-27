package games.phanatic.kitpvp.manager;

import games.phanatic.kitpvp.PKPvP;
import games.phanatic.kitpvp.util.ScoreboardUtil;
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
        if (coins.containsKey(p)) {
            coins.replace(p, coins.get(p) + amount);
        } else {
            loadPlayerCoins(p);
            coins.replace(p, coins.get(p) + amount);
        }
        if (ScoreboardUtil.hasScore(p)) {
            ScoreboardUtil.removeScore(p);
        }
        pvp.setPlayersSB(p);
    }

    public void removeCoins(Player p, int amount) {
        if (!coins.containsKey(p)) {
            loadPlayerCoins(p);
        }
        coins.replace(p, coins.get(p) - amount);
        if (ScoreboardUtil.hasScore(p)) {
            ScoreboardUtil.removeScore(p);
        }
        pvp.setPlayersSB(p);
    }

    public void loadPlayerCoins(Player p) {
        if(pvp.getFileUtil().getCoinConfig().isSet(p.getUniqueId().toString())) {
            coins.put(p, pvp.getFileUtil().getCoinConfig().getInt(p.getUniqueId().toString()));
        } else {
            coins.put(p, 0);
        }
    }

    public void saveCoins() {
        for (Player p : coins.keySet()) {
            if(!(coins.get(p) == 0)) {
                pvp.getFileUtil().getCoinConfig().set(p.getUniqueId().toString(), coins.get(p));
            }
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
        if (ScoreboardUtil.hasScore(p)) {
            ScoreboardUtil.removeScore(p);
        }
        pvp.setPlayersSB(p);
    }

    public void removeKS(Player p) {
        if (killStreaks.containsKey(p)) {
            killStreaks.remove(p);
        }
        if (ScoreboardUtil.hasScore(p)) {
            ScoreboardUtil.removeScore(p);
        }
        pvp.setPlayersSB(p);
    }

    public boolean canAffordKS(Player p, int price) {
        return killStreaks.get(p) >= price;
    }

    public boolean canAffordCoins(Player p, int price) {
        return coins.get(p) >= price;
    }

    public void subtractKS(Player p, int amount) {
        if (killStreaks.containsKey(p)) {
            killStreaks.replace(p, killStreaks.get(p) - amount);
        }
        if (ScoreboardUtil.hasScore(p)) {
            ScoreboardUtil.removeScore(p);
        }
        pvp.setPlayersSB(p);
    }

    public int getPlayerCoins(Player p) {
        return coins.get(p);
    }

    public int getPlayerKS(Player p) {
        return killStreaks.get(p);
    }

}
