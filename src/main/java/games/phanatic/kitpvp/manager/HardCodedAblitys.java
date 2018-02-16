package games.phanatic.kitpvp.manager;

import games.phanatic.kitpvp.PKPvP;
import games.phanatic.kitpvp.api.IHardCodedAblity;

import java.util.ArrayList;
import java.util.List;

public class HardCodedAblitys {

    private PKPvP pvp;

    private List<IHardCodedAblity> ablities;

    public HardCodedAblitys(PKPvP pvp) {
        this.pvp = pvp;
    }

    public void registerHardCodedAbility(IHardCodedAblity ablity) {
        if(ablities == null) {
            ablities = new ArrayList<>();
        }
        ablities.add(ablity);
    }

    public List<IHardCodedAblity> getAblities() {
        return ablities;
    }

    public IHardCodedAblity getAbility(String internalID) {
        for(IHardCodedAblity ablity : ablities) {
            if(ablity.getName().equalsIgnoreCase(internalID)) {
                return ablity;
            }
        }
        return null;
    }
}
