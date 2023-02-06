package pers.sunrise.util.function;

import pers.sunrise.util.function.ex.BaseEx;

import java.util.Objects;
import java.util.function.IntToDoubleFunction;

@FunctionalInterface
public interface IntToDoubleFunctionEx extends BaseEx<Double> {

    double applyAsDouble(int i) throws Exception;

    static IntToDoubleFunction without(IntToDoubleFunctionEx ex) {
        return without(ex, null);
    }

    static IntToDoubleFunction without(IntToDoubleFunctionEx ex, Class<?> annotatedClass) {
        Objects.requireNonNull(ex);
        return i -> {
            try {
                return ex.applyAsDouble(i);
            } catch (Exception e) {
                ex.catches(e, annotatedClass);
                return ex.returnDouble(e);
            }
        };
    }
}
