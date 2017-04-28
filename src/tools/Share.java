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

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Share<?, ?> share = (Share<?, ?>) o;

        if (x != null ? !x.equals(share.x) : share.x != null)
            return false;
        return y != null ? y.equals(share.y) : share.y == null;
    }

    @Override
    public int hashCode() {
        int result = x != null ? x.hashCode() : 0;
        result = 31 * result + (y != null ? y.hashCode() : 0);
        return result;
    }
}
