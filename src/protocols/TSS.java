package protocols;

import tools.Dealer;
import tools.Player;
import tools.Share;

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
//        BigInteger prime = new BigInteger(128,50, new SecureRandom());

        //Create list of Players and gives them increasing id numbers
        ArrayList<Player> players = new ArrayList<Player>();
        for (int i = 1; i <= nPlayers; i++)
            players.add(new Player(i,threshold, prime));

        //Create Dealer and print out each player's shares
        Dealer dealer = new Dealer(threshold, players, prime);

        //Dealer Distributes shares to other players
        for (Player i : players)
            i.setMyShare(dealer.distributeShares(i));

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

        //Send enough shares to recover secret
        for(int i = 0; i < threshold; i++){
            players.get(i).sendMyShare(players.get(2));
        }

        System.out.println("Player" + players.get(2).getId() + " has the following shares:");
        for(Share<Integer, BigInteger> i : players.get(2).gatheredShares)
            System.out.print("(" + i.x + ", " + i.y + ") ");
        System.out.println();

        //Player 2 attempts to recover the secret
        players.get(2).recoverSecret();

        System.out.println("Dealer secret: " + dealer.secret);
        System.out.println("Player secret: " + players.get(2).secret);
        System.out.println("Dealer Secret == Player Secret? -> " + dealer.secret.equals(players.get(2).secret));

        /*Timer timer = new Timer();
        timer.schedule(new PSS(players, threshold), 0, 5000);*/
    }
}
