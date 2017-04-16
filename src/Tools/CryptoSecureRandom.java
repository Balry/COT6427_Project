package Tools;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * Created by "Balry" - Michael Perez on 4/9/2017.
 */

public class CryptoSecureRandom {
    private SecureRandom randomEngine;

    public CryptoSecureRandom() {
        randomEngine = new SecureRandom();
    }

    /**
     * @return a BigInteger within the specified range (start <= randomNum < stop)
     */
    public BigInteger nextBigInteger(BigInteger start, BigInteger stop) {
        BigInteger randomNum;
        do {
            randomNum = new BigInteger(stop.bitLength(), randomEngine);
        } while (randomNum.compareTo(start) == -1 || randomNum.compareTo(stop.subtract(BigInteger.ONE)) == 1);
        return randomNum;
    }
}
