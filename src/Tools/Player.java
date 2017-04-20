package tools;

import math.Lagrange;

import java.math.BigInteger;
import java.util.ArrayList;

/**
 * Created by "Balry" - Michael Perez on 4/13/2017.
 */
public class Player {
    private int id, threshold;
    private BigInteger myShare, prime;
    public BigInteger secret;
    private ArrayList<Pair<Integer, BigInteger>> gatheredShares = new ArrayList<Pair<Integer, BigInteger>>();

    public Player(int i, int t, BigInteger p){
        id = i;
        threshold = t;
        prime = p;
    }

    public int getId() {
        return id;
    }

    public void setMyShare(BigInteger share) {
        myShare = share;
        gatheredShares.add(new Pair<>(id, myShare));
    }

    public void sendMyShare(Player otherPlayer){
        otherPlayer.getOtherShares(new Pair<>(id, myShare));
    }

    private void getOtherShares(Pair<Integer, BigInteger> s){
        gatheredShares.add(s);
    }

    public void recoverSecret(){
        if (gatheredShares.size()>=threshold){
            Lagrange l = new Lagrange(prime);
            secret = l.interpolate(gatheredShares);
            System.out.println("\nCalculating Lagrange Interpolation!\n");
        }
        else
            System.out.println("\nPlayer" + id + " does not have enough shares to recover the secret. It needs " +
                    (threshold - gatheredShares.size()) + " more shares to be able to recover the secret.\n");
    }
}
