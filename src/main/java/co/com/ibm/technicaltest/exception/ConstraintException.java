package co.com.ibm.technicaltest.exception;

public class ConstraintException extends Exception {

    public ConstraintException(String s) {
        super(s);
    }
    public ConstraintException(String s, Throwable ex) {
        super(s, ex);
    }
}
