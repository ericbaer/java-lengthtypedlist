package lengthtypedlist;

import org.eclipse.jdt.annotation.NonNull;

public class LessThanException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 4759447738845581139L;

    /**
     * It is impossible to construct LessThan<M, Zero>. Any method that gets
     * passed such a value should call this. 
     * @param absurdity
     */
    public <M extends Peano<?>> LessThanException
    (@NonNull LessThan<M, ? extends Zero> absurdity) {
        super("There is no natural number M such than M < 0");
    }

}
