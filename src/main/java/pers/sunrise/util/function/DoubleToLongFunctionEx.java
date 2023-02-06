package pers.sunrise.util.function;

import pers.sunrise.util.function.ex.BaseEx;

import java.util.Objects;
import java.util.function.DoubleToLongFunction;

@FunctionalInterface
public interface DoubleToLongFunctionEx extends BaseEx<Long> {

    long applyAsLong(double d) throws Exception;

    static DoubleToLongFunction without(DoubleToLongFunctionEx ex) {
        return without(ex, null);
    }

    static DoubleToLongFunction without(DoubleToLongFunctionEx ex, Class<?> annotatedClass) {
        Objects.requireNonNull(ex);
        return d -> {
            try {
                return ex.applyAsLong(d);
            } catch (Exception e) {
                ex.catches(e, annotatedClass);
                return ex.returnLong(e);
            }
        };
    }
}
