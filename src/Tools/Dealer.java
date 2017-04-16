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
    public BigInteger primeQ;
    public int threshold;
    public BigInteger secret; //TODO remove this after testing!
    private Polynomial poly;
    public ArrayList<Pair<Integer, BigInteger>> shares = new ArrayList<Pair<Integer, BigInteger>>();//TODO CHANGE TO PRIVATE AFTER TESTING

    /**
     * @precondition    n >= t
     * @param t     security threshold
     * @param pl    List of players
     * @param p     upper bound prime number
     */
    public Dealer(int t, ArrayList<Player> pl, BigInteger p){
        primeQ = p;
        threshold = t;
        this.generatePoly();
        this.generateShares(pl);
    }

    private void generatePoly(){
        poly = new Polynomial(threshold, primeQ);
    }

    private void generateShares(ArrayList<Player> players){
        secret = poly.evaluate(BigInteger.ZERO); //TODO Saving just to be able to check later
        for(Player i : players)
            shares.add(new Pair<>(i.getId(), poly.evaluate(BigInteger.valueOf(i.getId()))));
    }

    public BigInteger distributeShare(Player player){
        BigInteger yourShare = BigInteger.ZERO;
        for (int i = 0; i < shares.get(shares.size()-1).x; i++) {
            if (shares.get(i).x == player.getId()) {
                yourShare = shares.get(i).y;
                break;
            }
        }
        return yourShare;
    }

    // Testing Dealer Class
    public static void main(String[] args) {
        int numP=10;
        int t = 5;
        ArrayList<Player> players = new ArrayList<Player>();
        for (int i = 1; i<=numP; i++)
            players.add(new Player(i,t));

        BigInteger bigInt = new BigInteger("13");
        Dealer d = new Dealer(t, players, bigInt);
        for (int i = 0; i < d.shares.size(); i++)
            System.out.println("P" + d.shares.get(i).x + " has share = " + d.shares.get(i).y);

        BigInteger bigIntRand = new BigInteger(128,50, new SecureRandom());
        Dealer randD = new Dealer(t, players, bigIntRand);
        for (int i = 0; i < randD.shares.size(); i++)
            System.out.println("P" + d.shares.get(i).x + " has share = " + d.shares.get(i).y);
    }
}