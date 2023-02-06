package pers.sunrise.util.function;

import pers.sunrise.util.function.ex.BaseEx;

import java.util.Objects;

@FunctionalInterface
public interface RunnableEx extends BaseEx<Void> {

    void run() throws Exception;

    static Runnable without(RunnableEx ex) {
        return without(ex, null);
    }

    static Runnable without(RunnableEx ex, Class<?> annotatedClass) {
        Objects.requireNonNull(ex);
        return () -> {
            try {
                ex.run();
            } catch (Exception e) {
                ex.catches(e, annotatedClass);
            }
        };
    }
}
