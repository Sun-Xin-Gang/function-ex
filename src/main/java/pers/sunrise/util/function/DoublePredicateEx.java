package pers.sunrise.util.function;

import pers.sunrise.util.function.ex.BaseEx;

import java.util.Objects;
import java.util.function.DoublePredicate;

@FunctionalInterface
public interface DoublePredicateEx extends BaseEx<Boolean> {

    boolean test(double d) throws Exception;

    default DoublePredicateEx and(DoublePredicateEx other) {
        Objects.requireNonNull(other);
        return d -> test(d) && other.test(d);
    }

    default DoublePredicateEx negate() {
        return d -> !test(d);
    }

    default DoublePredicateEx or(DoublePredicateEx other) {
        Objects.requireNonNull(other);
        return d -> test(d) || other.test(d);
    }

    static DoublePredicate without(DoublePredicateEx ex) {
        return without(ex, null);
    }

    static DoublePredicate without(DoublePredicateEx ex, Class<?> annotatedClass) {
        Objects.requireNonNull(ex);
        return d -> {
            try {
                return ex.test(d);
            } catch (Exception e) {
                ex.catches(e, annotatedClass);
                return ex.returnBoolean(e);
            }
        };
    }
}
