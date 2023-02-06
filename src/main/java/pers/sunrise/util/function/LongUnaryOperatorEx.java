package pers.sunrise.util.function;

import pers.sunrise.util.function.ex.BaseEx;

import java.util.Objects;
import java.util.function.LongUnaryOperator;

@FunctionalInterface
public interface LongUnaryOperatorEx extends BaseEx<Long> {

    long applyAsLong(long operand) throws Exception;

    default LongUnaryOperatorEx compose(LongUnaryOperatorEx before) {
        Objects.requireNonNull(before);
        return l -> applyAsLong(before.applyAsLong(l));
    }

    default LongUnaryOperatorEx andThen(LongUnaryOperatorEx after) {
        Objects.requireNonNull(after);
        return l -> after.applyAsLong(applyAsLong(l));
    }

    static LongUnaryOperatorEx identity() {
        return l -> l;
    }

    static LongUnaryOperator without(LongUnaryOperatorEx ex) {
        return without(ex, null);
    }

    static LongUnaryOperator without(LongUnaryOperatorEx ex, Class<?> annotatedClass) {
        Objects.requireNonNull(ex);
        return l -> {
            try {
                return ex.applyAsLong(l);
            } catch (Exception e) {
                ex.catches(e, annotatedClass);
                return ex.returnLong(e);
            }
        };
    }
}
