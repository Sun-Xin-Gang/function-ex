package pers.sunrise.util.function;

import pers.sunrise.util.function.ex.BaseEx;

import java.util.Objects;
import java.util.function.Function;

@FunctionalInterface
public interface FunctionEx<T, R> extends BaseEx<R> {

    R apply(T t) throws Exception;

    default <V> FunctionEx<V, R> compose(FunctionEx<? super V, ? extends T> before) {
        Objects.requireNonNull(before);
        return v -> apply(before.apply(v));
    }

    default <V> FunctionEx<T, V> andThen(FunctionEx<? super R, ? extends V> after) {
        Objects.requireNonNull(after);
        return t -> after.apply(apply(t));
    }

    static <T> FunctionEx<T, T> identity() {
        return t -> t;
    }

    static <T, R> Function<T, R> without(FunctionEx<? super T, ? extends R> ex) {
        return without(ex, null);
    }

    static <T, R> Function<T, R> without(FunctionEx<? super T, ? extends R> ex, Class<?> annotatedClass) {
        Objects.requireNonNull(ex);
        return t -> {
            try {
                return ex.apply(t);
            } catch (Exception e) {
                ex.catches(e, annotatedClass);
                return ex.returnObj(e);
            }
        };
    }
}
