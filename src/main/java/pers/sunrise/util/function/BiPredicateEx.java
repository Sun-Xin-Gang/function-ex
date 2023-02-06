package pers.sunrise.util.function;

import pers.sunrise.util.function.ex.BaseEx;

import java.util.Objects;
import java.util.function.BiPredicate;

@FunctionalInterface
public interface BiPredicateEx<T, U> extends BaseEx<Boolean> {

    boolean test(T t, U u) throws Exception;

    default BiPredicateEx<T, U> and(BiPredicateEx<? super T, ? super U> other) {
        Objects.requireNonNull(other);
        return (t, u) -> test(t, u) && other.test(t, u);
    }

    default BiPredicateEx<T, U> negate() {
        return (t, u) -> !test(t, u);
    }

    default BiPredicateEx<T, U> or(BiPredicateEx<? super T, ? super U> other) {
        Objects.requireNonNull(other);
        return (t, u) -> test(t, u) || other.test(t, u);
    }

    static <T, U> BiPredicate<T, U> without(BiPredicateEx<? super T, ? super U> ex) {
        return without(ex, null);
    }

    static <T, U> BiPredicate<T, U> without(BiPredicateEx<? super T, ? super U> ex, Class<?> annotatedClass) {
        Objects.requireNonNull(ex);
        return (t, u) -> {
            try {
                return ex.test(t, u);
            } catch (Exception e) {
                ex.catches(e, annotatedClass);
                return ex.returnBoolean(e);
            }
        };
    }
}
