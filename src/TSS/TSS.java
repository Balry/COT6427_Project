package TSS;

import Tools.Dealer;
import Tools.Player;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;

/**
 * Created by "Balry" - Michael Perez on 3/23/2017.
 */
public class TSS {
    public static void main (String[] args) {
        int nPlayers = 10;
        int threshold = 3;

        ArrayList<Player> players = new ArrayList<Player>();
        for (int i = 1; i <= nPlayers; i++)
            players.add(new Player(i));

        Dealer d = new Dealer(threshold, players, new BigInteger(128,50, new SecureRandom()));
    }
}
