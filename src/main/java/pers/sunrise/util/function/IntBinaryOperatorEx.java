package pers.sunrise.util.function;

import pers.sunrise.util.function.ex.BaseEx;

import java.util.Objects;
import java.util.function.IntBinaryOperator;

@FunctionalInterface
public interface IntBinaryOperatorEx extends BaseEx<Integer> {

    int applyAsInt(int l, int r) throws Exception;

    static IntBinaryOperator without(IntBinaryOperatorEx ex) {
        return without(ex, null);
    }

    static IntBinaryOperator without(IntBinaryOperatorEx ex, Class<?> annotatedClass) {
        Objects.requireNonNull(ex);
        return (l, r) -> {
            try {
                return ex.applyAsInt(l, r);
            } catch (Exception e) {
                ex.catches(e, annotatedClass);
                return ex.returnInt(e);
            }
        };
    }
}
