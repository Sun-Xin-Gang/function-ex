package pers.sunrise.util.function;

import pers.sunrise.util.function.ex.BaseEx;

import java.util.Objects;
import java.util.function.LongBinaryOperator;

@FunctionalInterface
public interface LongBinaryOperatorEx extends BaseEx<Long> {

    long applyAsLong(long l, long r) throws Exception;

    static LongBinaryOperator without(LongBinaryOperatorEx ex) {
        return without(ex, null);
    }

    static LongBinaryOperator without(LongBinaryOperatorEx ex, Class<?> annotatedClass) {
        Objects.requireNonNull(ex);
        return (l, r) -> {
            try {
                return ex.applyAsLong(l, r);
            } catch (Exception e) {
                ex.catches(e, annotatedClass);
                return ex.returnLong(e);
            }
        };
    }
}
