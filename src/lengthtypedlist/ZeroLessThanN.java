package lengthtypedlist;

/**
 * A type-level axiom that zero is less than any non-zero number.
 * @author eric
 *
 */
public final class ZeroLessThanN<N> extends LessThan<Zero, S<N>> {

    public ZeroLessThanN() {
        super();
    }

    @Override
    public boolean isMEqualToZero() {
        return true;
    }
}
