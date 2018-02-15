package games.phanatic.kitpvp.manager;

import games.phanatic.kitpvp.PKPvP;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

public class LocationManager {

    private PKPvP pvp;
    private Location spawn;

    public LocationManager(PKPvP pvp) {
        this.pvp = pvp;
        readLocations();
    }

    public void readLocations() {
        World world = Bukkit.getWorld("world");
        String x = pvp.getFileUtil().getSpawnConfig().getString("x");
        String y = pvp.getFileUtil().getSpawnConfig().getString("y");
        String z = pvp.getFileUtil().getSpawnConfig().getString("z");
        String yaw = pvp.getFileUtil().getSpawnConfig().getString("yaw");
        String pitch = pvp.getFileUtil().getSpawnConfig().getString("p");
        spawn = new Location(world, Double.valueOf(x), Double.valueOf(y), Double.valueOf(z), Float.valueOf(yaw), Float.valueOf(pitch));
    }

    public Location getSpawn() {
        if(spawn == null) {
            readLocations();
        }
        return spawn;
    }

    public void setSpawn(Location loc) {
        this.spawn = loc;
    }

    public void saveSpawn() {
        String x = String.valueOf((spawn.getX()));
        pvp.getFileUtil().getSpawnConfig().set("z", x);
        String y = String.valueOf((spawn.getY()));
        pvp.getFileUtil().getSpawnConfig().set("y", y);
        String z = String.valueOf((spawn.getZ()));
        pvp.getFileUtil().getSpawnConfig().set("z", z);
        String yaw = String.valueOf((spawn.getYaw()));
        pvp.getFileUtil().getSpawnConfig().set("yaw", yaw);
        String pitch = String.valueOf((spawn.getPitch()));
        pvp.getFileUtil().getSpawnConfig().set("p", pitch);
        pvp.getFileUtil().saveSpawnConfig();
    }
}
