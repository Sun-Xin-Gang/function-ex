package pers.sunrise.util.function;

import pers.sunrise.util.function.ex.BaseEx;

import java.util.Objects;
import java.util.function.DoubleFunction;

@FunctionalInterface
public interface DoubleFunctionEx<R> extends BaseEx<R> {

    R apply(double d) throws Exception;

    static <R> DoubleFunction<R> without(DoubleFunctionEx<? extends R> ex) {
        return without(ex, null);
    }

    static <R> DoubleFunction<R> without(DoubleFunctionEx<? extends R> ex, Class<?> annotatedClass) {
        Objects.requireNonNull(ex);
        return d -> {
            try {
                return ex.apply(d);
            } catch (Exception e) {
                ex.catches(e, annotatedClass);
                return ex.returnObj(e);
            }
        };
    }
}
