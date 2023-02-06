package pers.sunrise.util.function.ex;

public class CheckedRuntimeException extends RuntimeException {
    static final long serialVersionUID = 1L;

    public CheckedRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }
}
