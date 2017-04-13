package TSS;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * Created by "Balry" - Michael Perez on 3/23/2017.
 */
public class TSS {
    public static void main (String[] args) {
        SecureRandom rnd = new SecureRandom();
        BigInteger bigInt = new BigInteger(128,50,rnd);
        System.out.println(bigInt);
        //Dealer dealer = new Dealer(11, 5, 3);

    }
}
