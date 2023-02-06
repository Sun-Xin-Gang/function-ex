package pers.sunrise.util.function;

import java.util.Comparator;
import java.util.Objects;
import java.util.function.BinaryOperator;

@FunctionalInterface
public interface BinaryOperatorEx<T> extends BiFunctionEx<T, T, T> {

    static <T> BinaryOperatorEx<T> minBy(Comparator<? super T> comparator) {
        Objects.requireNonNull(comparator);
        return (a, b) -> comparator.compare(a, b) <= 0 ? a : b;
    }

    static <T> BinaryOperatorEx<T> maxBy(Comparator<? super T> comparator) {
        Objects.requireNonNull(comparator);
        return (a, b) -> comparator.compare(a, b) >= 0 ? a : b;
    }

    static <T> BinaryOperator<T> without(BinaryOperatorEx<T> ex) {
        return without(ex, null);
    }

    static <T> BinaryOperator<T> without(BinaryOperatorEx<T> ex, Class<?> annotatedClass) {
        Objects.requireNonNull(ex);
        return (a, b) -> {
            try {
                return ex.apply(a, b);
            } catch (Exception e) {
                ex.catches(e, annotatedClass);
                return ex.returnObj(e);
            }
        };
    }
}
