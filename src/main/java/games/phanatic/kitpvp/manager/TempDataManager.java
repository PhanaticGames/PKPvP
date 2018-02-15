package games.phanatic.kitpvp.manager;

import org.bukkit.entity.Player;

import java.util.HashMap;

public class TempDataManager {

    private HashMap<Player, Integer> killStreaks;

    public TempDataManager() {
        killStreaks = new HashMap<>();
    }

    public void addKS(Player p) {
        if(killStreaks.containsKey(p)) {
            killStreaks.replace(p, killStreaks.get(p) + 1);
        } else {
            killStreaks.put(p, 1);
        }
    }

    public void removeKS(Player p) {
        if(killStreaks.containsKey(p)) {
            killStreaks.remove(p);
        }
    }

    public boolean canAfford(Player p, int price) {
        return killStreaks.get(p) >= price;
    }

    public void subtractKS(Player p, int amount) {
        if(killStreaks.containsKey(p)) {
            killStreaks.replace(p, killStreaks.get(p) - amount);
        }
    }

}
