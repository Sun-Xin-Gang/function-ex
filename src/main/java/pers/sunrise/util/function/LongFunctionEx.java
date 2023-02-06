package pers.sunrise.util.function;

import pers.sunrise.util.function.ex.BaseEx;

import java.util.Objects;
import java.util.function.LongFunction;

@FunctionalInterface
public interface LongFunctionEx<R> extends BaseEx<R> {

    R apply(long l) throws Exception;

    static <R> LongFunction<R> without(LongFunctionEx<R> ex) {
        return without(ex, null);
    }

    static <R> LongFunction<R> without(LongFunctionEx<R> ex, Class<?> annotatedClass) {
        Objects.requireNonNull(ex);
        return l -> {
            try {
                return ex.apply(l);
            } catch (Exception e) {
                ex.catches(e, annotatedClass);
                return ex.returnObj(e);
            }
        };
    }
}
