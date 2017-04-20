package tools;

/**
 * Created by "Balry" - Michael Perez on 4/15/2017.
 */
public class Share<T1, T2> {
    public T1 x;
    public T2 y;

    public Share(T1 id, T2 share) {
        this.x = id;
        this.y = share;
    }
}
