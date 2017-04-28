package math;

import tools.CryptoSecureRandom;

import java.math.BigInteger;
import java.util.ArrayList;

/**
 * Created by "Balry" - Michael Perez on 4/9/2017.
 */
public class Polynomial {
    private ArrayList<BigInteger> coef = new ArrayList<BigInteger>();    //coefficients
    private int deg;            // degree of polynomial (0 for the zero polynomial)
    private BigInteger qPrime;  // coefficient upper bound prime number

    // Polynomial Constructor with random secret
    public Polynomial(int t, BigInteger prime){
        deg = t-1;
        qPrime = prime;
        this.generateRandomPoly();
    }

    // Polynomial Constructor with specified secret
    public Polynomial(int t, BigInteger prime, BigInteger secret){
        deg = t-1;
        qPrime = prime;
        this.generateRandomPoly(secret);
    }

    // Generates random coefficients of the polynomial
    private void generateRandomPoly(){
        CryptoSecureRandom randomEngine = new CryptoSecureRandom();
        for(int i=0; i<=deg; i++) //create random coefficients such that 0<= coef < prime
            coef.add(randomEngine.nextBigInteger(BigInteger.ZERO, qPrime));
    }

    // Generates random coefficients of the polynomial, but allows the secret to be defined (used for PSS where g(0)=0)
    private void generateRandomPoly(BigInteger secret){
        coef.add(secret);
        CryptoSecureRandom randomEngine = new CryptoSecureRandom();
        for(int i=1; i<=deg; i++) //create random coefficients such that 0<= coef < prime
            coef.add(randomEngine.nextBigInteger(BigInteger.ZERO, qPrime));
    }

    //TODO Display for testing/demo purposes
    public void showPoly(){
        if (this.coef.size()>0) {
            System.out.print("Polynomial created: " + this.coef.get(0));
            for (int i = 1; i < this.coef.size(); i++) {
                System.out.print(" + " + this.coef.get(i) + "x^" + i);
            }
            System.out.println();
        }
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
        /*//Polynomial polyTest = new Polynomial(3, new BigInteger("13"));
        Polynomial polyTest = new Polynomial(3, new BigInteger(128, 10, new SecureRandom()));
        System.out.println(polyTest.evaluate(new BigInteger("0")));

        Polynomial polyTestOtherConstructor = new Polynomial(3, new BigInteger(128, 10, new SecureRandom()), BigInteger.ZERO);
        System.out.println(polyTestOtherConstructor.evaluate(new BigInteger("0")));*/
    }
}