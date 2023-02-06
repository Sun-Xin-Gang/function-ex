package pers.sunrise.util.function;

import pers.sunrise.util.function.ex.BaseEx;

import java.util.Objects;
import java.util.function.IntUnaryOperator;

@FunctionalInterface
public interface IntUnaryOperatorEx extends BaseEx<Integer> {

    int applyAsInt(int operand) throws Exception;

    default IntUnaryOperatorEx compose(IntUnaryOperatorEx before) {
        Objects.requireNonNull(before);
        return i -> applyAsInt(before.applyAsInt(i));
    }

    default IntUnaryOperatorEx andThen(IntUnaryOperatorEx after) {
        Objects.requireNonNull(after);
        return i -> after.applyAsInt(applyAsInt(i));
    }

    static IntUnaryOperatorEx identity() {
        return i -> i;
    }

    static IntUnaryOperator without(IntUnaryOperatorEx ex) {
        return without(ex, null);
    }

    static IntUnaryOperator without(IntUnaryOperatorEx ex, Class<?> annotatedClass) {
        Objects.requireNonNull(ex);
        return i -> {
            try {
                return ex.applyAsInt(i);
            } catch (Exception e) {
                ex.catches(e, annotatedClass);
                return ex.returnInt(e);
            }
        };
    }
}
