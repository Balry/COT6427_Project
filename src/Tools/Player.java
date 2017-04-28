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
    public BigInteger secret; //TODO Public for testing/demo
    public ArrayList<Share<Integer, BigInteger>> gatheredShares = new ArrayList<Share<Integer, BigInteger>>(); //TODO Public for testing/demo
    private ArrayList<Share<Integer, BigInteger>> myGenAuxShares = new ArrayList<Share<Integer, BigInteger>>();
    private ArrayList<Share<Integer, BigInteger>> pssAuxShares = new ArrayList<Share<Integer, BigInteger>>();
    public Polynomial pssPoly; //TODO Public for testing/demo

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
            System.out.println("Player" + this.id + " already has Player" + s.x + " share"); //TODO change for GUI
    }

    public void recoverSecret(){
        if(gatheredShares.size()>=threshold){
            Lagrange l = new Lagrange(prime);
            secret = l.interpolate(gatheredShares);
            System.out.println("\nCalculating Lagrange Interpolation!\n"); //TODO Display for testing/demo purposes
        }
        else
            System.out.println("\nPlayer" + id + " does not have enough shares to recover the secret. It needs " +
                    (threshold - gatheredShares.size()) + " more shares to be able to recover the secret.\n"); //TODO change for GUI
    }

    //PSS related
    public void generateZeroConstantPolynomial(){
        pssPoly = new Polynomial(threshold-1, prime, BigInteger.ZERO);
        pssPoly.showPoly();
    }

    public void generateAuxShares(ArrayList<Player> players){
        for(Player i : players)
            myGenAuxShares.add(new Share<>(i.getId(), pssPoly.evaluate(BigInteger.valueOf(i.getId()))));
    }

    public void sendMyAuxShares(ArrayList<Player> otherPlayers){
        for(Player i : otherPlayers)
            i.getOthersAuxShares(myGenAuxShares.get(i.getId()-1));
        myGenAuxShares.clear();
    }

    private void getOthersAuxShares(Share<Integer, BigInteger> s){
        pssAuxShares.add(s);
    }

    public void pssShift() {
        BigInteger result = BigInteger.ZERO;
        for(Share<Integer, BigInteger> i : pssAuxShares)
            result = result.add(i.y).mod(prime);
        result = result.multiply(BigInteger.valueOf(this.getId())).mod(prime);
        myShare = myShare.add(result).mod(prime);
        gatheredShares.clear();
        gatheredShares.add(new Share<>(id, myShare));
    }
}
