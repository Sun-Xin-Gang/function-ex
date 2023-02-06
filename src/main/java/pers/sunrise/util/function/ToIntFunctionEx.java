package pers.sunrise.util.function;

import pers.sunrise.util.function.ex.BaseEx;

import java.util.Objects;
import java.util.function.ToIntFunction;

@FunctionalInterface
public interface ToIntFunctionEx<T> extends BaseEx<Integer> {

    int applyAsInt(T t) throws Exception;

    static <T> ToIntFunction<T> without(ToIntFunctionEx<T> ex) {
        return without(ex, null);
    }

    static <T> ToIntFunction<T> without(ToIntFunctionEx<T> ex, Class<?> annotatedClass) {
        Objects.requireNonNull(ex);
        return t -> {
            try {
                return ex.applyAsInt(t);
            } catch (Exception e) {
                ex.catches(e, annotatedClass);
                return ex.returnInt(e);
            }
        };
    }
}
