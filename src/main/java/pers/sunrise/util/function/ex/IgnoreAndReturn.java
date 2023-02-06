package pers.sunrise.util.function.ex;

import pers.sunrise.util.function.ex.annotation.ExceptionCatcher;

import java.util.function.BiConsumer;

public class IgnoreAndReturn implements BiConsumer<ExceptionCatcher, Exception> {
    private static final IgnoreAndReturn singleton = new IgnoreAndReturn();

    private IgnoreAndReturn() { }

    public static IgnoreAndReturn getInstance() { return singleton; }

    @Override
    public void accept(ExceptionCatcher catcher, Exception cause) { }
}
