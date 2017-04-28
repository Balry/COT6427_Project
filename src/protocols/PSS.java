package protocols;

import tools.Dealer;
import tools.Player;
import tools.ProactiveUpdate;
import tools.Share;

import java.math.BigInteger;
import java.util.ArrayList;

/**
 * Created by "Balry" - Michael Perez on 4/23/2017.
 */
public class PSS {
    public static void main(String[] args) {
        int nPlayers = 11;
        int threshold = 5;   // >500 takes too long for prezi
        BigInteger prime = new BigInteger("13");
//        BigInteger prime = new BigInteger(128,50, new SecureRandom());

        //Create list of Players and gives them increasing id numbers
        ArrayList<Player> players = new ArrayList<Player>();
        for (int i = 1; i <= nPlayers; i++)
            players.add(new Player(i,threshold, prime));

        Dealer dealer = new Dealer(threshold, players, prime);

        for (Player i : players)
            i.setMyShare(dealer.distributeShares(i));

        System.out.println();
        //Send enough shares to recover secret
        for(int i = 0; i < threshold; i++)
            players.get(i).sendMyShare(players.get(2));

        System.out.println("Player" + players.get(2).getId() + " has the following shares:");
        for(Share<Integer, BigInteger> i : players.get(2).gatheredShares)
            System.out.print("(" + i.x + ", " + i.y + ") ");
        System.out.println();

        //Player 2 attempts to recover the secret
        players.get(2).recoverSecret();

        System.out.println("Dealer secret: " + dealer.secret);
        System.out.println("Player secret: " + players.get(2).secret);
        System.out.println("Dealer Secret == Player Secret? -> " + dealer.secret.equals(players.get(2).secret) + " \n\n");

        //Proactive Update
        System.out.println("Proactive Update");
        ProactiveUpdate pss = new ProactiveUpdate(players);

        System.out.println();
        //Send enough shares to recover secret
        for(int i = 0; i < threshold; i++)
            players.get(i).sendMyShare(players.get(2));

        System.out.println("Player" + players.get(2).getId() + " has the following shares:");
        for(Share<Integer, BigInteger> i : players.get(2).gatheredShares)
            System.out.print("(" + i.x + ", " + i.y + ") ");
        System.out.println();

        //Player 2 attempts to recover the secret after proactive update
        players.get(2).recoverSecret();

        System.out.println("Dealer secret: " + dealer.secret);
        System.out.println("Player secret: " + players.get(2).secret);
        System.out.println("Dealer Secret == Player Secret? -> " + dealer.secret.equals(players.get(2).secret));
    }
}
