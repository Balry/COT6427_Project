package Tools;

import java.math.BigInteger;
import java.util.ArrayList;

/**
 * Created by "Balry" - Michael Perez on 4/13/2017.
 */
public class Player {
    private int id;
    private BigInteger myShare;
    private ArrayList<BigInteger> otherShares = new ArrayList<BigInteger>();

    public int getId() {
        return id;
    }

    public BigInteger getMyShare() {
        return myShare;
    }

    public void setMyShare(BigInteger share) {
        this.myShare = share;
    }

    public Player(int i){
        id = i;
    }

}
