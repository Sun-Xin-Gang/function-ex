package pers.sunrise.util.function;

import pers.sunrise.util.function.ex.BaseEx;

import java.util.Objects;
import java.util.function.ToLongFunction;

@FunctionalInterface
public interface ToLongFunctionEx<T> extends BaseEx<Long> {

    long applyAsLong(T t) throws Exception;

    static <T> ToLongFunction<T> without(ToLongFunctionEx<T> ex) {
        return without(ex, null);
    }

    static <T> ToLongFunction<T> without(ToLongFunctionEx<T> ex, Class<?> annotatedClass) {
        Objects.requireNonNull(ex);
        return t -> {
            try {
                return ex.applyAsLong(t);
            } catch (Exception e) {
                ex.catches(e, annotatedClass);
                return ex.returnLong(e);
            }
        };
    }
}
