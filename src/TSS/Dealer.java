package TSS;
import java.util.concurrent.ThreadLocalRandom;
import Tools.Polynomial;

/**
 * Created by "Balry" - Michael Perez on 3/19/2017.
 */
public class Dealer {
    private Polynomial poly = new Polynomial(0,0);
    private int prime, players, threshold=2;

    public Dealer(int q, int n, int t) {
        prime = q;
        players = n;
        threshold = t;
        generatePoly();
    }

    public Polynomial generatePoly(){
        Polynomial poly = new Polynomial(0,0);
        for(int i = 0; i < threshold; i++){
            poly = poly.plus(new Polynomial(ThreadLocalRandom.current().nextInt(1, prime + 1), i));
        }
        return poly;
    }


    public int [] generateShares(){
        int [] shares = new int[threshold];
        for(int i = 1; i <= threshold; i++) {
            shares[i-1] = poly.evaluate(i);
        }
        return shares;
    }

    public static void main(String[] args) {



        Dealer d = new Dealer(11, 5, 3);
        int [] integerArray = d.generateShares();
        for (int i = 0; i < integerArray.length; i++)
            System.out.println(integerArray[i] + " ");
    }
}