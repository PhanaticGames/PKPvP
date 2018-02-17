package games.phanatic.kitpvp.run;

import games.phanatic.kitpvp.PKPvP;

public class CoinSave implements Runnable {

    private PKPvP pvp;

    public CoinSave(PKPvP pvp) {
        this.pvp = pvp;
    }

    @Override
    public void run() {
        pvp.getTmpDatManager().saveCoins();
    }
}
