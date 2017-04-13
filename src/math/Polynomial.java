package math;

import Tools.CryptoSecureRandom;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;

/**
 * Created by "Balry" - Michael Perez on 4/9/2017.
 */
public class Polynomial {
    private ArrayList<BigInteger> coef = new ArrayList<BigInteger>();    //coefficients
    private int deg;            // degree of polynomial (0 for the zero polynomial)
    private BigInteger qPrime;  // coefficient upper bound prime number

    public Polynomial(int t, BigInteger prime){
        deg = t-1;
        qPrime = prime;
        this.generateRandomPoly();
    }

    // Generates random coefficients of the polynomial
    private void generateRandomPoly(){
        CryptoSecureRandom randomEngine = new CryptoSecureRandom();
        for(int i=0; i<=deg; i++) //create random coefficients such that 0<= coef < prime
            coef.add(randomEngine.nextBigInteger(BigInteger.ZERO, qPrime));
    }

    // Uses Horner's method to compute and return the polynomial evaluated at x
    public BigInteger evaluate(BigInteger x){
        BigInteger result = BigInteger.ZERO;
        for (int i = deg; i >= 0; i--)
            result = coef.get(i).add(x.multiply(result)).mod(qPrime);
        return result.mod(qPrime);
    }

    // Testing Polynomial Class
    public static void main(String[] args) {
        //Polynomial polyTest = new Polynomial(3, new BigInteger("13"));
        Polynomial polyTest = new Polynomial(3, new BigInteger(128, 10, new SecureRandom()));
        System.out.println(polyTest.evaluate(new BigInteger("0")));
    }
}