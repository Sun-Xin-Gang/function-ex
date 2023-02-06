package pers.sunrise.util.function.ex;

import pers.sunrise.util.function.ex.annotation.ExceptionCatcher;

import java.util.function.BiConsumer;

public class ToRuntimeException implements BiConsumer<ExceptionCatcher, Exception> {
    private static final ToRuntimeException singleton = new ToRuntimeException();

    private ToRuntimeException() { }

    public static ToRuntimeException getInstance() { return singleton; }

    @Override
    public void accept(ExceptionCatcher catcher, Exception cause) throws RuntimeException {
        String message = catcher.message();
        Class<? extends RuntimeException> cls = catcher.to();
        if (cls == CheckedRuntimeException.class) throw new CheckedRuntimeException(message, cause);

        try { // throw new XxxException(String, Throwable);
            throw cls.getConstructor(String.class, Throwable.class).newInstance(message, cause);
        } catch (Exception ignore) { }

        try { // throw new XxxException(Throwable);
            throw cls.getConstructor(Throwable.class).newInstance(cause);
        } catch (Exception ignore) { }

        try { // throw new XxxException(String);
            throw cls.getConstructor(String.class).newInstance(message);
        } catch (Exception ignore) { }

        throw new CheckedRuntimeException(message, cause); // target RuntimeException illegal
    }
}
