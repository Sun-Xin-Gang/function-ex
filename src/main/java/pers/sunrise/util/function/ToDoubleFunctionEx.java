package pers.sunrise.util.function;

import pers.sunrise.util.function.ex.BaseEx;

import java.util.Objects;
import java.util.function.ToDoubleFunction;

@FunctionalInterface
public interface ToDoubleFunctionEx<T> extends BaseEx<Double> {

    double applyAsDouble(T t) throws Exception;

    static <T> ToDoubleFunction<T> without(ToDoubleFunctionEx<T> ex) {
        return without(ex, null);
    }

    static <T> ToDoubleFunction<T> without(ToDoubleFunctionEx<T> ex, Class<?> annotatedClass) {
        Objects.requireNonNull(ex);
        return t -> {
            try {
                return ex.applyAsDouble(t);
            } catch (Exception e) {
                ex.catches(e, annotatedClass);
                return ex.returnDouble(e);
            }
        };
    }
}
