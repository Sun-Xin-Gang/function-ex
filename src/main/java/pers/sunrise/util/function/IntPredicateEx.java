package pers.sunrise.util.function;

import pers.sunrise.util.function.ex.BaseEx;

import java.util.Objects;
import java.util.function.IntPredicate;

@FunctionalInterface
public interface IntPredicateEx extends BaseEx<Boolean> {

    boolean test(int i) throws Exception;

    default IntPredicateEx and(IntPredicateEx other) {
        Objects.requireNonNull(other);
        return i -> test(i) && other.test(i);
    }

    default IntPredicateEx negate() {
        return i -> !test(i);
    }

    default IntPredicateEx or(IntPredicateEx other) {
        Objects.requireNonNull(other);
        return i -> test(i) || other.test(i);
    }

    static IntPredicate without(IntPredicateEx ex) {
        return without(ex, null);
    }

    static IntPredicate without(IntPredicateEx ex, Class<?> annotatedClass) {
        Objects.requireNonNull(ex);
        return i -> {
            try {
                return ex.test(i);
            } catch (Exception e) {
                ex.catches(e, annotatedClass);
                return ex.returnBoolean(e);
            }
        };
    }
}
