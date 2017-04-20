package math;

import tools.Share;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;

/**
 * Created by "Balry" - Michael Perez on 4/12/2017.
 */
public class Lagrange {
    BigInteger prime;

    public Lagrange(BigInteger p){
        prime = p;
    }

    public BigInteger interpolate(ArrayList<Share<Integer, BigInteger>> shares){
        int n = shares.size();
        BigDecimal numerator, denominator;
        BigInteger secret = BigInteger.ZERO;

        for(int count = 0; count<n; count++) {
            //Initialisation of variable

            numerator = BigDecimal.ONE;//BigInteger.valueOf(shares.get(count).x);
            denominator = BigDecimal.ONE;

            for (int count2 = 0; count2 < n; count2++) {
                // /if (count2 != count) {
                if(shares.get(count2).x != shares.get(count).x){
                    numerator = numerator.multiply(new BigDecimal(BigInteger.ZERO.subtract(BigInteger.valueOf(shares.get(count2).x))));
                    denominator = denominator.multiply(new BigDecimal(BigInteger.valueOf(shares.get(count).x).subtract(BigInteger.valueOf(shares.get(count2).x))));
                }
            }
            BigDecimal rem = numerator.remainder(denominator);
            if(rem == BigDecimal.ZERO){
                secret = secret.add(numerator.toBigInteger().divide(denominator.toBigInteger()).multiply(shares.get(count).y)).mod(prime);
            }
            else {
                BigInteger inverse = denominator.toBigInteger().modInverse(prime);
                BigInteger div = numerator.toBigInteger().multiply(inverse);
                secret = secret.add(div.multiply(shares.get(count).y)).mod(prime);
                //BigDecimal div = numerator.divide(denominator, 10, RoundingMode.HALF_UP);
                //secret = secret.add(div.toBigInteger().multiply(shares.get(count).y)).mod(prime);
            }
        }
        return secret;
    }

    //Testing the Lagrange
    public static void main(String[] args)
    {
        ArrayList<Share<Integer, BigInteger>> shares = new ArrayList<Share<Integer, BigInteger>>();
        shares.add(new Share<>(2, BigInteger.valueOf(10)));
        shares.add(new Share<>(3, BigInteger.valueOf(8)));
        shares.add(new Share<>(4, BigInteger.valueOf(9)));

        Lagrange l = new Lagrange(BigInteger.TEN);
        BigInteger sec = l.interpolate(shares);

        System.out.println("Secret is: " + sec);
    }
}
