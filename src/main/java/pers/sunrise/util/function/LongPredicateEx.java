package pers.sunrise.util.function;

import pers.sunrise.util.function.ex.BaseEx;

import java.util.Objects;
import java.util.function.LongPredicate;

@FunctionalInterface
public interface LongPredicateEx extends BaseEx<Boolean> {

    boolean test(long l) throws Exception;

    default LongPredicateEx and(LongPredicateEx other) {
        Objects.requireNonNull(other);
        return l -> test(l) && other.test(l);
    }

    default LongPredicateEx negate() {
        return l -> !test(l);
    }

    default LongPredicateEx or(LongPredicateEx other) {
        Objects.requireNonNull(other);
        return l -> test(l) || other.test(l);
    }

    static LongPredicate without(LongPredicateEx ex) {
        return without(ex, null);
    }

    static LongPredicate without(LongPredicateEx ex, Class<?> annotatedClass) {
        Objects.requireNonNull(ex);
        return l -> {
            try {
                return ex.test(l);
            } catch (Exception e) {
                ex.catches(e, annotatedClass);
                return ex.returnBoolean(e);
            }
        };
    }
}
