package pers.sunrise.util.function;

import pers.sunrise.util.function.ex.BaseEx;

import java.util.Objects;
import java.util.function.ToIntBiFunction;

@FunctionalInterface
public interface ToIntBiFunctionEx<T, U> extends BaseEx<Integer> {

    int applyAsInt(T t, U u) throws Exception;

    static <T, U> ToIntBiFunction<T, U> without(ToIntBiFunctionEx<T, U> ex) {
        return without(ex, null);
    }

    static <T, U> ToIntBiFunction<T, U> without(ToIntBiFunctionEx<T, U> ex, Class<?> annotatedClass) {
        Objects.requireNonNull(ex);
        return (t, u) -> {
            try {
                return ex.applyAsInt(t, u);
            } catch (Exception e) {
                ex.catches(e, annotatedClass);
                return ex.returnInt(e);
            }
        };
    }
}
