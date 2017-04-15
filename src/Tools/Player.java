package Tools;

import java.math.BigInteger;
import java.util.ArrayList;

import Tools.Pair;
/**
 * Created by "Balry" - Michael Perez on 4/13/2017.
 */
public class Player {
    private int id;
    private BigInteger myShare;
    private ArrayList<Pair<Integer, BigInteger>> otherShares = new ArrayList<Pair<Integer, BigInteger>>();

    public int getId() {
        return id;
    }

    private BigInteger getMyShare() {
        return myShare;
    }

    public Pair sendMyShare(Player otherPlayer){
        return new Pair(id, myShare);
    }

    public void getOtherShares(Pair<Integer, BigInteger> s){
        otherShares.add(s);
    }

    public void setMyShare(BigInteger share) {
        this.myShare = share;
    }

    public Player(int i){
        id = i;
    }

}
