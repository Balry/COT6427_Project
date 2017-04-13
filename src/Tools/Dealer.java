package Tools;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;

import math.Polynomial;

/**
 * Created by "Balry" - Michael Perez on 3/19/2017.
 */
public class Dealer {
    public BigInteger primeQ;
    private ArrayList<BigInteger> shares = new ArrayList<BigInteger>();
    private ArrayList<Player> players = new ArrayList<Player>();
    private Polynomial poly;
    private int numPlayers, threshold;

    /**
     * @precondition n >= t
     * @param p upper bound prime number
     * @param n number of players
     * @param t security threshold
     */
    public Dealer(int n, int t, BigInteger p, ArrayList<Player> pl){
        primeQ = p;
        numPlayers = n;
        threshold = t;
        players = pl;
        this.generatePoly();
        this.generateShares();
    }

    private void generatePoly(){
        poly = new Polynomial(threshold, primeQ);
    }

    private void generateShares(){
        for(int i = 0; i <= numPlayers; i++)
            shares.add(i, poly.evaluate(BigInteger.valueOf(i)));
    }

    /*public void distributeShares(ArrayList<Player> players){
        for (int i = 0; i < d.shares.size(); i++)
            System.out.println(d.shares.get(i) + " ");
    }*/

    public static void main(String[] args) {
        BigInteger bigIntRand = new BigInteger(128,50, new SecureRandom());
        BigInteger bigInt = new BigInteger("5");

        Dealer randD = new Dealer(10, 5, bigIntRand);
        Dealer d = new Dealer(10, 5, bigInt);

        for (int i = 0; i < d.shares.size(); i++)
            System.out.println(d.shares.get(i) + " ");

        //d.distributeShares();
    }
}