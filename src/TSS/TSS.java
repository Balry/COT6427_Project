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
        int nPlayers = 5;
        int threshold = 3;
        //BigInteger prime = new BigInteger("11");
        BigInteger prime = new BigInteger(128,50, new SecureRandom());

        //Creates players and gives them id number
        ArrayList<Player> players = new ArrayList<Player>();
        for (int i = 1; i <= nPlayers; i++)
            players.add(new Player(i,threshold, prime));

        //Create Dealer and print out each player's shares
        Dealer d = new Dealer(threshold, players, prime);
        for (int i = 0; i < d.shares.size(); i++)
            System.out.println("P" + d.shares.get(i).x + " has share = " + d.shares.get(i).y);

        //Dealer Distributes shares to other players
        for (Player i : players)
            i.setMyShare(d.distributeShare(i));

        //Players 1, 2, and 4, send shares to player 3
        players.get(0).sendMyShare(players.get(2));
        players.get(1).sendMyShare(players.get(2));
        players.get(3).sendMyShare(players.get(2));

        //Player 2 tries to recover the secret
        players.get(2).recoverSecret();


        System.out.println("Dealer secret: " + d.secret);
    }
}
