package tools;

import math.Polynomial;

import java.math.BigInteger;
import java.util.ArrayList;

/**
 * Created by "Balry" - Michael Perez on 3/19/2017.
 */
public class Dealer {
    public BigInteger prime;
    public int threshold;
    public BigInteger secret; //TODO Remove this after testing/demo
    private Polynomial poly;
    public ArrayList<Share<Integer, BigInteger>> shares = new ArrayList<Share<Integer, BigInteger>>(); //TODO Public only for testing/demo

    /**
     * @precondition    n >= t
     * @param t     security threshold
     * @param pl    List of players
     * @param p     upper bound prime number
     */
    public Dealer(int t, ArrayList<Player> pl, BigInteger p){
        prime = p;
        threshold = t;
        this.generatePoly();
        this.generateShares(pl);
    }

    private void generatePoly(){
        poly = new Polynomial(threshold, prime);
        poly.showPoly(); //TODO Display for testing/demo purposes
    }

    private void generateShares(ArrayList<Player> players){
        secret = poly.evaluate(BigInteger.ZERO); //TODO Saving to be able to check later
        for(Player i : players) {
            shares.add(new Share<>(i.getId(), poly.evaluate(BigInteger.valueOf(i.getId()))));
            System.out.println("P" + shares.get(i.getId()-1).x + " will get share = " + shares.get(i.getId()-1).y); //TODO Display for testing/demo purposes
        }
        System.out.println(); //TODO Display for testing/demo purposes
    }

    public BigInteger distributeShares(Player player){
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
/*        int numP=10;
        int t = 5;
        ArrayList<Player> players = new ArrayList<Player>();
        for (int i = 1; i<=numP; i++)
            players.add(new Player(i,t, 13));

        BigInteger bigInt = new BigInteger("13");
        Dealer d = new Dealer(t, players, bigInt);
        for (int i = 0; i < d.shares.size(); i++)
            System.out.println("P" + d.shares.get(i).x + " has share = " + d.shares.get(i).y);

        BigInteger bigIntRand = new BigInteger(128,50, new SecureRandom());
        Dealer randD = new Dealer(t, players, bigIntRand);
        for (int i = 0; i < randD.shares.size(); i++)
            System.out.println("P" + d.shares.get(i).x + " has share = " + d.shares.get(i).y);*/
    }
}