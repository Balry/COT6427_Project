package protocols;

import tools.Dealer;
import tools.Player;
import tools.ProactiveUpdate;

import java.math.BigInteger;
import java.util.ArrayList;

/**
 * Created by "Balry" - Michael Perez on 4/23/2017.
 */
public class PSS {
    public static void main(String[] args) {
        TSS tss = new TSS();
        String[] empty = {""};
        tss.main(empty);

        int nPlayers = 11;
        int threshold = 5;   // >500 takes too long for prezi
        BigInteger prime = new BigInteger("13");

        //Create list of Players and gives them increasing id numbers
        ArrayList<Player> players = new ArrayList<Player>();
        for (int i = 1; i <= nPlayers; i++)
            players.add(new Player(i,threshold, prime));

        //Create Dealer and print out each player's shares
        Dealer dealer = new Dealer(threshold, players, prime);
        dealer.poly.showPoly();
        for (int i = 0; i < dealer.shares.size(); i++)
            System.out.println("P" + dealer.shares.get(i).x + " has share = " + dealer.shares.get(i).y);

        //Dealer Distributes shares to other players
        for (Player i : players)
            i.setMyShare(dealer.distributeShare(i));

        //Proactive Update
        ProactiveUpdate pss = new ProactiveUpdate(players);
    }
}
