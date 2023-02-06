package pers.sunrise.util.function;

import pers.sunrise.util.function.ex.BaseEx;

import java.util.Objects;
import java.util.function.ToDoubleBiFunction;

@FunctionalInterface
public interface ToDoubleBiFunctionEx<T, U> extends BaseEx<Double> {

    double applyAsDouble(T t, U u) throws Exception;

    static <T, U> ToDoubleBiFunction<T, U> without(ToDoubleBiFunctionEx<T, U> ex) {
        return without(ex, null);
    }

    static <T, U> ToDoubleBiFunction<T, U> without(ToDoubleBiFunctionEx<T, U> ex, Class<?> annotatedClass) {
        Objects.requireNonNull(ex);
        return (t, u) -> {
            try {
                return ex.applyAsDouble(t, u);
            } catch (Exception e) {
                ex.catches(e, annotatedClass);
                return ex.returnDouble(e);
            }
        };
    }
}
