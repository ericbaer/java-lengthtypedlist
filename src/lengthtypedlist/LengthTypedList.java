package lengthtypedlist;

import org.eclipse.jdt.annotation.NonNull;

/**
 * A list whose type depends on its length; for example, a list of length
 * two will have a different type than a list of length three.
 * @author ericbaer
 *
 * @param <N> the length of the list
 * @param <A> the type of elements in the list
 */
public abstract class LengthTypedList<N extends Peano<?>, A> {

    LengthTypedList() { }
    
    /**
     * Appends the contents of this list to the given StringBuilder
     * @param left StringBuilder to which to append 
     * @return
     */
    @NonNull
    protected abstract StringBuilder
    addToStringBuilder(@NonNull StringBuilder left);
    
    public abstract int length();
    
    public String toString() {
        return addToStringBuilder(new StringBuilder("["))
                .append("]")
                .toString();
    }
    
    /**
     * Gets the Mth element of this list, given proof that M is less than the
     * length of the list.
     * @param index
     * @return
     */
    @NonNull
    public abstract <M extends Peano<?>> A get(@NonNull LessThan<M, N> index);
}
