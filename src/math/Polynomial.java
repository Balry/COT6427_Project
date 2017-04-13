package math;

import Tools.CryptoSecureRandom;

import java.math.BigInteger;
import java.util.ArrayList;

/**
 * Created by "Balry" - Michael Perez on 4/9/2017.
 */
public class Polynomial {
    private ArrayList<BigInteger> coef = new ArrayList<BigInteger>();    //coefficients
    private int deg;            // degree of polynomial (0 for the zero polynomial)
    private BigInteger qPrime;
    private CryptoSecureRandom randomEngine;

    public ArrayList<BigInteger> generateRandomPoly(int t, BigInteger primeQ){
        deg = t-1;
        qPrime = primeQ;
        randomEngine = new CryptoSecureRandom();
        for(int i=0; i<=deg; i++){
            coef.add(randomEngine.nextBigInteger(BigInteger.ZERO, primeQ));
        }
        return coef;
    }

    // use Horner's method to compute and return the polynomial evaluated at x
    public BigInteger evaluate(BigInteger x){
        BigInteger result = BigInteger.ZERO;
        for (int i = deg; i >= 0; i--)
            result = coef.get(i).add(x.multiply(result)).mod(qPrime);
        return result.mod(qPrime);
    }

    // Testing Polynomial Class
    public static void main(String[] args) {
        Polynomial test = new Polynomial();
        //test.generateRandomPoly(3, new BigInteger(128, 10, new SecureRandom()));
        test.generateRandomPoly(3, new BigInteger("3"));
        System.out.println(test.evaluate(new BigInteger("0")));
    }
}