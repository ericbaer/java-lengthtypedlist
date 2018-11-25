package lengthtypedlist;

/**
 * Type-level proof that a number is less than another number. (It would be
 * nicer if we could express that in interfaces, rather than constructing one
 * object for every step of the inductive proof.)
 * @author eric
 *
 * @param <M> the smaller number
 * @param <N> the bigger number
 */
public abstract class LessThan<M extends Peano<?>, N extends Peano<?>> {

    LessThan() { }
    
    /**
     * Is the smaller number equal to zero?
     */
    public abstract boolean isMEqualToZero();
    
}
