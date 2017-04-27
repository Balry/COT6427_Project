package tools;

import math.Lagrange;
import math.Polynomial;

import java.math.BigInteger;
import java.util.ArrayList;

/**
 * Created by "Balry" - Michael Perez on 4/13/2017.
 */
public class Player {
    private int id, threshold;
    private BigInteger myShare, prime;
    public BigInteger secret;
    private ArrayList<Share<Integer, BigInteger>> gatheredShares = new ArrayList<Share<Integer, BigInteger>>();
    private ArrayList<Share<Integer, BigInteger>> auxiliaryShares = new ArrayList<Share<Integer, BigInteger>>();
    private Polynomial pssPoly;

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
        gatheredShares.add(new Share<>(id, myShare));
    }

    public void sendMyShare(Player otherPlayer){
        otherPlayer.getOtherShares(new Share<>(id, myShare));
    }

    private void getOtherShares(Share<Integer, BigInteger> s){
        //if(gatheredShares.indexOf(s) == -1)
        if(!gatheredShares.contains(s))
            gatheredShares.add(s);
       else
            System.out.println("Player" + this.id + " already has Player" + s.x + " share");
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

    //PSS related
    public void generateZeroConstantPolynomial(){
        pssPoly = new Polynomial(threshold, prime, BigInteger.ZERO);
    }

    public void makeAuxiliaryShares(){
        //auxiliaryShares
    }

    public void sendAuxShare(Player otherPlayer){
        otherPlayer.getOtherAuxShares(new Share<>(id, myShare));
    }

    private void getOtherAuxShares(Share<Integer, BigInteger> s){
        //if(gatheredShares.indexOf(s) == -1)
        if(!gatheredShares.contains(s))
            gatheredShares.add(s);
        else
            System.out.println("Player" + this.id + " already has Player" + s.x + " share");
    }

}
