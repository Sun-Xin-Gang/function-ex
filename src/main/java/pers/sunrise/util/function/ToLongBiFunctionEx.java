package pers.sunrise.util.function;

import pers.sunrise.util.function.ex.BaseEx;

import java.util.Objects;
import java.util.function.ToLongBiFunction;

@FunctionalInterface
public interface ToLongBiFunctionEx<T, U> extends BaseEx<Long> {

    long applyAsLong(T t, U u) throws Exception;

    static <T, U> ToLongBiFunction<T, U> without(ToLongBiFunctionEx<T, U> ex) {
        return without(ex, null);
    }

    static <T, U> ToLongBiFunction<T, U> without(ToLongBiFunctionEx<T, U> ex, Class<?> annotatedClass) {
        Objects.requireNonNull(ex);
        return (t, u) -> {
            try {
                return ex.applyAsLong(t, u);
            } catch (Exception e) {
                ex.catches(e, annotatedClass);
                return ex.returnLong(e);
            }
        };
    }
}
