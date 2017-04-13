package TSS;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;

import math.Polynomial;

/**
 * Created by "Balry" - Michael Perez on 3/19/2017.
 */
public class Dealer {
    public BigInteger primeQ;
    private Polynomial poly = new Polynomial();
    private int numPlayers, threshold;

    /**
     * @precondition n >= t
     * @param p upper bound prime number
     * @param n number of players
     * @param t security threshold
     */
    public Dealer(BigInteger p, int n, int t){
        primeQ = p;
        numPlayers = n;
        threshold = t;
        generatePoly();
    }

    private void generatePoly(){
        poly.generateRandomPoly(threshold, primeQ);
    }

    public ArrayList<BigInteger> generateShares(){
        ArrayList<BigInteger> shares = new ArrayList<BigInteger>();
        for(int i = 1; i <= threshold; i++) {
            shares.add(i-1, poly.evaluate(BigInteger.valueOf(i)));
        }
        return shares;
    }

    public static void main(String[] args) {

        //generateRandomPoly(3, new BigInteger(128, 10, new SecureRandom()));

        BigInteger bigInt = new BigInteger(128,50, new SecureRandom());
        //BigInteger bigInt = new BigInteger("11");

        Dealer d = new Dealer(bigInt, 10, 5);

        System.out.println("generated polynomial =  " + d.poly);

        ArrayList<BigInteger> integerArray = d.generateShares();
        for (int i = 0; i < integerArray.size(); i++)
            System.out.println(integerArray.get(i) + " ");
    }
}