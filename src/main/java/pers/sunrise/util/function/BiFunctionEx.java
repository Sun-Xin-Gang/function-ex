package pers.sunrise.util.function;

import pers.sunrise.util.function.ex.BaseEx;

import java.util.Objects;
import java.util.function.BiFunction;

/**
 *
 * @author 孙欣刚（ xingang.sun@hotmail.com ）
 * @param <T> the type of the first argument to the function
 * @param <U> the type of the second argument to the function
 * @param <R> the type of the result of the function
 * @see java.util.function.BiFunction
 */
@FunctionalInterface
public interface BiFunctionEx<T, U, R> extends BaseEx<R> {

    R apply(T t, U u) throws Exception;

    default <V> BiFunctionEx<T, U, V> andThen(FunctionEx<? super R, ? extends V> after) {
        Objects.requireNonNull(after);
        return (t, u) -> after.apply(apply(t, u));
    }

    static <T, U, R> BiFunction<T, U, R> without(BiFunctionEx<? super T, ? super U, ? extends R> ex) {
        return without(ex, null);
    }

    static <T, U, R> BiFunction<T, U, R> without(BiFunctionEx<? super T, ? super U, ? extends R> ex, Class<?> annotatedClass) {
        Objects.requireNonNull(ex);
        return (t, u) -> {
            try {
                return ex.apply(t, u);
            } catch (Exception e) {
                ex.catches(e, annotatedClass);
                return ex.returnObj(e);
            }
        };
    }
}
