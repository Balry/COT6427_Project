package math;

import Tools.Pair;

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

    public BigInteger interpolate(ArrayList<Pair<Integer, BigInteger>> shares){
        int n = shares.size();
        BigInteger numerator, denominator;
        BigInteger secret = BigInteger.ZERO;

        for(int count = 0; count<n; count++) {
            //Initialisation of variable

            numerator = BigInteger.ONE;//BigInteger.valueOf(shares.get(count).x);
            denominator = BigInteger.ONE;

            for (int count2 = 0; count2 < n; count2++) {
                // /if (count2 != count) {
                if(shares.get(count2).x != shares.get(count).x){
                    numerator = numerator.multiply(BigInteger.ZERO.subtract(BigInteger.valueOf(shares.get(count2).x)));
                    denominator = denominator.multiply(BigInteger.valueOf(shares.get(count).x).subtract(BigInteger.valueOf(shares.get(count2).x)));
                }
            }
            secret = secret.add(numerator.divide(denominator).multiply(shares.get(count).y)).mod(prime);
        }
        return secret;
    }

    //Testing the Lagrange
    public static void main(String[] args)
    {
        ArrayList<Pair<Integer, BigInteger>> shares = new ArrayList<Pair<Integer, BigInteger>>();
        shares.add(new Pair<>(2, BigInteger.valueOf(10)));
        shares.add(new Pair<>(3, BigInteger.valueOf(8)));
        shares.add(new Pair<>(4, BigInteger.valueOf(9)));

        Lagrange l = new Lagrange(BigInteger.TEN);
        BigInteger sec = l.interpolate(shares);

        System.out.println("Secret is: " + sec);
    }
}
