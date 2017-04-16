package Tools;

import java.math.BigInteger;
import java.util.ArrayList;

import Tools.Pair;
/**
 * Created by "Balry" - Michael Perez on 4/13/2017.
 */
public class Player {
    private int id, threshold;
    private BigInteger myShare;
    private ArrayList<Pair<Integer, BigInteger>> otherShares = new ArrayList<Pair<Integer, BigInteger>>();

    public Player(int i, int t){
        id = i;
        threshold = t;
    }

    public int getId() {
        return id;
    }

    private BigInteger getMyShare() {
        return myShare;
    }

    public void sendMyShare(Player otherPlayer){
        otherPlayer.getOtherShares(new Pair<>(id, myShare));
    }

    //TODO NOT WORK BECAUSE PASS BY VALUE
    public void getOtherShares(Pair<Integer, BigInteger> s){
        otherShares.add(s);
    }

    public void setMyShare(BigInteger share) {
        this.myShare = share;
    }

    public void recoverSecret(){
        if (otherShares.size()>threshold){
            //Do Lagrange Interpolation
        }
        else
            System.out.println("Player" + id + " does not have enough shares to recover the secret. It needs " +
                    (threshold - otherShares.size()) + " more shares to be able to recover the secret.");
    }
}
