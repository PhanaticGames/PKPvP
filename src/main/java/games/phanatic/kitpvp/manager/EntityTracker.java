package games.phanatic.kitpvp.manager;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EntityTracker {

    private HashMap<Player, List<Entity>> playerOwnedEntities;

    public EntityTracker() {
        playerOwnedEntities = new HashMap<>();
    }

    public HashMap<Player, List<Entity>> getPlayerOwnedEntities() {
        return playerOwnedEntities;
    }

    public void addEntityToPlayer(Player p, Entity entity) {
        if (playerOwnedEntities.containsKey(p)) {
            playerOwnedEntities.get(p).add(entity);
        } else {
            List<Entity> entitys = new ArrayList<>();
            entitys.add(entity);
            playerOwnedEntities.put(p, entitys);
        }
    }

    public List<Entity> getPlayerOwnedEntities(Player p) {
        return playerOwnedEntities.get(p);
    }

    public void removeEntityFromPlayer(Player p, Entity e) {
        if(playerOwnedEntities.containsKey(p)) {
            playerOwnedEntities.get(p).remove(e);
        }
    }
}
