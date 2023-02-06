package pers.sunrise.util.function;

import pers.sunrise.util.function.ex.BaseEx;

import java.util.Objects;
import java.util.function.LongToDoubleFunction;

@FunctionalInterface
public interface LongToDoubleFunctionEx extends BaseEx<Double> {

    double applyAsDouble(long l) throws Exception;

    static LongToDoubleFunction without(LongToDoubleFunctionEx ex) {
        return without(ex, null);
    }

    static LongToDoubleFunction without(LongToDoubleFunctionEx ex, Class<?> annotatedClass) {
        Objects.requireNonNull(ex);
        return l -> {
            try {
                return ex.applyAsDouble(l);
            } catch (Exception e) {
                ex.catches(e, annotatedClass);
                return ex.returnDouble(e);
            }
        };
    }
}
