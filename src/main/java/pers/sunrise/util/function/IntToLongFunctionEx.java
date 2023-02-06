package pers.sunrise.util.function;

import pers.sunrise.util.function.ex.BaseEx;

import java.util.Objects;
import java.util.function.IntToLongFunction;

@FunctionalInterface
public interface IntToLongFunctionEx extends BaseEx<Long> {

    long applyAsLong(int i) throws Exception;

    static IntToLongFunction without(IntToLongFunctionEx ex) {
        return without(ex, null);
    }

    static IntToLongFunction without(IntToLongFunctionEx ex, Class<?> annotatedClass) {
        Objects.requireNonNull(ex);
        return i -> {
            try {
                return ex.applyAsLong(i);
            } catch (Exception e) {
                ex.catches(e, annotatedClass);
                return ex.returnLong(e);
            }
        };
    }
}
