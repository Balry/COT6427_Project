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
        BigInteger prime = new BigInteger("11");
        //BigInteger prime = new BigInteger(128,50, new SecureRandom());

        ArrayList<Player> players = new ArrayList<Player>();
        //Creates players and gives them id number
        for (int i = 1; i <= nPlayers; i++)
            players.add(new Player(i,threshold));

        Dealer d = new Dealer(threshold, players, prime);
        for (int i = 0; i < d.shares.size(); i++)
            System.out.println("P" + d.shares.get(i).x + " has share = " + d.shares.get(i).y);

        for (Player i : players)
            i.setMyShare(d.distributeShare(i));

        System.out.println("testint breakpoint");
    }
}
