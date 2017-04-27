package tools;

import java.util.ArrayList;

/**
 * Created by "Balry" - Michael Perez on 4/23/2017.
 */
public class ProactiveUpdate {
    ArrayList<Player> players;


    public ProactiveUpdate(ArrayList<Player> players){
        this.players = players;
    }

    private void auxiliaryShares(){
        for (Player i : players)
            i.generateZeroConstantPolynomial();
    }
}
