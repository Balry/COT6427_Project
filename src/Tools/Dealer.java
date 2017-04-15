package Tools;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Iterator;

import math.Polynomial;

import javax.swing.text.html.HTMLDocument;

/**
 * Created by "Balry" - Michael Perez on 3/19/2017.
 */
public class Dealer {
    public BigInteger primeQ, secret;
    private ArrayList<BigInteger> shares = new ArrayList<BigInteger>();;
    private ArrayList<Player> players = new ArrayList<Player>();
    private Polynomial poly;
    private int threshold;

    /**
     * @precondition    n >= t
     * @param t     security threshold
     * @param pl    List of players
     * @param p     upper bound prime number
     */
    public Dealer(int t, ArrayList<Player> pl, BigInteger p){
        primeQ = p;
        threshold = t;
        players = pl;
        this.generatePoly();
        this.generateShares();
    }

    private void generatePoly(){
        poly = new Polynomial(threshold, primeQ);
    }

    private void generateShares(){
        secret = poly.evaluate(BigInteger.ZERO); //
        shares.add(BigInteger.ZERO); //Do not want the list of shares to have the secret
        for(Player i : players)
            shares.add(i.getId(), poly.evaluate(BigInteger.valueOf(i.getId())));
    }

    /*public void distributeShares(ArrayList<Player> players){
        for (int i = 0; i < d.shares.size(); i++)
            System.out.println(d.shares.get(i) + " ");
    }*/

    // Testing Poly
    public static void main(String[] args) {
        int numP=10;
        int t = 5;
        ArrayList<Player> players = new ArrayList<Player>();
        for (int i = 1; i<=numP; i++)
            players.add(new Player(i));

        BigInteger bigInt = new BigInteger("13");
        Dealer d = new Dealer(t, players, bigInt);
        for (int i = 0; i < d.shares.size(); i++)
            System.out.println(d.shares.get(i) + " ");

        BigInteger bigIntRand = new BigInteger(128,50, new SecureRandom());
        Dealer randD = new Dealer(t, players, bigIntRand);
        for (int i = 0; i < randD.shares.size(); i++)
            System.out.println(randD.shares.get(i) + " ");
    }
}