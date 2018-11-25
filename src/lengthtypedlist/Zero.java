package lengthtypedlist;

/**
 * The Peano number zero, in the Java type system. This never actually gets
 * instantiated, it's just used as a type parameter to other classes.
 * @author ericbaer
 *
 */
public final class Zero extends Peano<Zero> {

    /**
     * You should never need to construct this class
     */
    private Zero() { }
}
