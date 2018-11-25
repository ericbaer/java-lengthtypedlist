package lengthtypedlist;

public final class EmptyList<A> extends LengthTypedList<Zero, A> {

    /**
     * A zero-length list doesn't contain anything, so it has no parameters.
     */
    public EmptyList() {
        super();
    }
    
    /**
     * A zero-length list doesn't contain anything, so there's nothing to
     * turn into a string.
     */
    protected StringBuilder addToStringBuilder(StringBuilder left) {
        return left;
    }
    
    public int length() {
        return 0;
    }

    @Override
    public <M extends Peano<?>> A get(LessThan<M, Zero> absurdity) {
        throw new LessThanException(absurdity);
    }
}
