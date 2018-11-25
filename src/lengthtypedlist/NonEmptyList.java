package lengthtypedlist;

import org.eclipse.jdt.annotation.NonNull;

/**
 * A length-typed list which contains at least one element.
 * @author ericbaer
 *
 * @param <N> the predecessor of the length of the list
 * @param <A>
 */
public final class NonEmptyList<N extends Peano<?>, A>
extends LengthTypedList<S<N>, A> {

    private final A _head;
    private final LengthTypedList<N, A> _tail;
    private final int _length;
    
    public NonEmptyList(@NonNull A head,
            @NonNull LengthTypedList<N, A> tail) {
        _head = head;
        _tail = tail;
        _length = tail.length() + 1;
    }

    /**
     * @return the head of the list
     */
    public A getHead() {
        return _head;
    }
    
    /**
     * 
     * @return the tail of the list
     */
    public LengthTypedList<N, A> getTail() {
        return _tail;
    }
    
    protected StringBuilder addToStringBuilder(StringBuilder left) {
        StringBuilder withHead = left.append(_head);
        if (1 == _length) {
            return withHead;
        } else {
            return _tail.addToStringBuilder(withHead.append(", "));
        }
    }
    
    @Override
    public int length() {
        return _length;
    }
    
    public <M extends Peano<?>> A get(LessThan<M, S<N>> index) {
        if (index instanceof ZeroLessThanN) {
            return _head;
        } else {
            // We know that there are only two subclasses of Peano, so if index
            // isn't one of them, then it's the other
            @SuppressWarnings("unchecked")
            SuccLessThanSucc<?, N> sindex = (SuccLessThanSucc<?, N>)index;
            return _tail.get(sindex.getPredecessor());
        }
    }
    
    public static void main(String[] args) {
        LengthTypedList<Zero, String> empty = new EmptyList<String>();
        
        // Note how lists of different lengths have different types
        LengthTypedList<S<Zero>, String> one =
                new NonEmptyList<Zero, String>(
                        "last element",
                        empty
                        );
        LengthTypedList<S<S<Zero>>, String> two =
                new NonEmptyList<S<Zero>, String>(
                        "second-to-last element",
                        one);

        LengthTypedList<S<S<S<Zero>>>, String> three =
                new NonEmptyList<S<S<Zero>>, String>(
                        "third-to-last element",
                        two
                        );

        System.out.println(three.toString());

        // Get the zeroth element
        LessThan<Zero, S<S<S<Zero>>>> zeroLTthree =
                new ZeroLessThanN<S<S<Zero>>>();
        System.out.println(three.get(zeroLTthree));

        // Get the first element
        LessThan<S<Zero>, S<S<S<Zero>>>> oneLTthree =
                new SuccLessThanSucc<Zero, S<S<Zero>>>(
                        new ZeroLessThanN<S<Zero>>());
        System.out.println(three.get(oneLTthree));
        
        // Get the second element
        LessThan<S<S<Zero>>, S<S<S<Zero>>>> twoLTthree = 
                new SuccLessThanSucc<S<Zero>, S<S<Zero>>>(
                        new SuccLessThanSucc<Zero, S<Zero>>(
                                new ZeroLessThanN<Zero>()));
        System.out.println(three.get(twoLTthree));
    }
}
