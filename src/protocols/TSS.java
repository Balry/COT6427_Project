package protocols;

import tools.Dealer;
import tools.Player;

import java.math.BigInteger;
import java.util.ArrayList;


/**
 * Created by "Balry" - Michael Perez on 3/23/2017.
 */
public class TSS {
    public static void main (String[] args) {
        int nPlayers = 11;
        int threshold = 5;   // >500 takes too long for prezi
        BigInteger prime = new BigInteger("13");
//        BigInteger prime = new BigInteger("31");
//        BigInteger prime = new BigInteger("229");
//        BigInteger prime = new BigInteger("349");
//        BigInteger prime = new BigInteger(128,50, new SecureRandom());

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

        // Group of Players out of order send shares to Player3
/*        players.get(0).sendMyShare(players.get(2));
        players.get(1).sendMyShare(players.get(2));
        players.get(4).sendMyShare(players.get(2));
        players.get(5).sendMyShare(players.get(2));
        players.get(2).sendMyShare(players.get(2)); //repeat
        players.get(4).sendMyShare(players.get(2)); //repeat
        players.get(10).sendMyShare(players.get(2));
        players.get(3).sendMyShare(players.get(2));
        players.get(23).sendMyShare(players.get(2));
        players.get(44).sendMyShare(players.get(2));
        players.get(15).sendMyShare(players.get(2));
        players.get(26).sendMyShare(players.get(2));
        players.get(7).sendMyShare(players.get(2));
        players.get(40).sendMyShare(players.get(2));
        players.get(34).sendMyShare(players.get(2));*/

        //This repeats Player3
        for(int i = 0; i <=threshold+1; i++){
            players.get(i).sendMyShare(players.get(2));
        }

        //Player 2 attempts to recover the secret
        players.get(2).recoverSecret();

        System.out.println("Dealer secret: " + dealer.secret);
        System.out.println("Player secret: " + players.get(2).secret);
        System.out.println("Dealer Secret == Player Secret? -> " + dealer.secret.equals(players.get(2).secret));

        /*Timer timer = new Timer();
        timer.schedule(new PSS(players, threshold), 0, 5000);*/
    }
}
