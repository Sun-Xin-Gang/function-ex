package pers.sunrise.util.function;

import pers.sunrise.util.function.ex.BaseEx;

import java.util.Objects;
import java.util.function.IntFunction;

@FunctionalInterface
public interface IntFunctionEx<R> extends BaseEx<R> {

    R apply(int i) throws Exception;

    static <R> IntFunction<R> without(IntFunctionEx<R> ex) {
        return without(ex, null);
    }

    static <R> IntFunction<R> without(IntFunctionEx<R> ex, Class<?> annotatedClass) {
        Objects.requireNonNull(ex);
        return i -> {
            try {
                return ex.apply(i);
            } catch (Exception e) {
                ex.catches(e, annotatedClass);
                return ex.returnObj(e);
            }
        };
    }
}
