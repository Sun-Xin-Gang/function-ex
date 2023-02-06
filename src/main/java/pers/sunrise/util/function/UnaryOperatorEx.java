package pers.sunrise.util.function;

import java.util.Objects;
import java.util.function.UnaryOperator;

@FunctionalInterface
public interface UnaryOperatorEx<T> extends FunctionEx<T, T> {

    static <T> UnaryOperatorEx<T> identity() {
        return t -> t;
    }

    static <T> UnaryOperator<T> without(UnaryOperatorEx<T> ex) {
        return without(ex, null);
    }

    static <T> UnaryOperator<T> without(UnaryOperatorEx<T> ex, Class<?> annotatedClass) {
        Objects.requireNonNull(ex);
        return t -> {
            try {
                return ex.apply(t);
            } catch (Exception e) {
                ex.catches(e, annotatedClass);
                return ex.returnObj(e);
            }
        };
    }
}
