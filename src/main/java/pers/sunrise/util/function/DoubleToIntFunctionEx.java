package pers.sunrise.util.function;

import pers.sunrise.util.function.ex.BaseEx;

import java.util.Objects;
import java.util.function.DoubleToIntFunction;

@FunctionalInterface
public interface DoubleToIntFunctionEx extends BaseEx<Integer> {

    int applyAsInt(double d) throws Exception;

    static DoubleToIntFunction without(DoubleToIntFunctionEx ex) {
        return without(ex, null);
    }

    static DoubleToIntFunction without(DoubleToIntFunctionEx ex, Class<?> annotatedClass) {
        Objects.requireNonNull(ex);
        return d -> {
            try {
                return ex.applyAsInt(d);
            } catch (Exception e) {
                ex.catches(e, annotatedClass);
                return ex.returnInt(e);
            }
        };
    }
}
