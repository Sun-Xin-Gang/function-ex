package pers.sunrise.util.function;

import pers.sunrise.util.function.ex.BaseEx;

import java.util.Objects;
import java.util.function.Predicate;

@FunctionalInterface
public interface PredicateEx<T> extends BaseEx<Boolean> {

    boolean test(T t) throws Exception;

    default PredicateEx<T> and(PredicateEx<? super T> other) {
        Objects.requireNonNull(other);
        return t -> test(t) && other.test(t);
    }

    default PredicateEx<T> negate() {
        return t -> !test(t);
    }

    default PredicateEx<T> or(PredicateEx<? super T> other) {
        Objects.requireNonNull(other);
        return t -> test(t) || other.test(t);
    }

    static <T> PredicateEx<T> isEqual(Object targetRef) {
        return (null == targetRef)
                ? Objects::isNull
                : targetRef::equals;
    }

    static <T> Predicate<T> without(PredicateEx<T> ex) {
        return without(ex, null);
    }

    static <T> Predicate<T> without(PredicateEx<T> ex, Class<?> annotatedClass) {
        Objects.requireNonNull(ex);
        return t -> {
            try {
                return ex.test(t);
            } catch (Exception e) {
                ex.catches(e, annotatedClass);
                return ex.returnBoolean(e);
            }
        };
    }
}
