package lengthtypedlist;

/**
 * The Peano successor function, in the Java type system. One-letter class
 * names are a horrible idea, but this gets nested a lot. This never actually
 * gets instantiated, it's just used as a type parameter to other classes.
 * @author eric
 *
 * @param <N> the number of which this is a successor 
 */
public final class S<N> extends Peano<S<N>> {

    /**
     * You should never need to construct this class
     */
    private S() { }
}
