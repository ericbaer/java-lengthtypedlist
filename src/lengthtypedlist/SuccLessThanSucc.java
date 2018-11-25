package lengthtypedlist;

import org.eclipse.jdt.annotation.NonNull;

/**
 * An inductive proof that one number is less than another. Assuming you don't
 * cheat using reflection or by passing nulls around, the only way you can
 * construct this is by providing proof that M-1 is less than N-1, eventually
 * leading all the way down to a proof that 0 is less than (N-M).
 * @author ericbaer
 *
 * @param <M> the predecessor of the smaller number
 * @param <N> the predecessor of the larger number
 */
public final class SuccLessThanSucc<M extends Peano<?>, N extends Peano<?>>
extends LessThan<S<M>, S<N>> {

    private final LessThan<M, N> _predecessor;
    
    /**
     * Construct a proof that S<M> is less than S<N>
     * @param predecessor a proof that M is less than N
     */
    public SuccLessThanSucc(@NonNull LessThan<M, N> predecessor) {
        super();
        _predecessor = predecessor;
    }

    @Override
    public boolean isMEqualToZero() {
        return false;
    }
    
    /**
     * Given that this object is proof that S<M> is less than S<N>, get a
     * proof that M is less than N.
     * @return
     */
    @NonNull
    public LessThan<M, N> getPredecessor() {
        return _predecessor;
    }
}
