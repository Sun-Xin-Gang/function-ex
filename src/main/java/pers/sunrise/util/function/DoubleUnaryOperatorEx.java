package pers.sunrise.util.function;

import pers.sunrise.util.function.ex.BaseEx;

import java.util.Objects;
import java.util.function.DoubleUnaryOperator;

@FunctionalInterface
public interface DoubleUnaryOperatorEx extends BaseEx<Double> {

    double applyAsDouble(double operand) throws Exception;

    default DoubleUnaryOperatorEx compose(DoubleUnaryOperatorEx before) {
        Objects.requireNonNull(before);
        return d -> applyAsDouble(before.applyAsDouble(d));
    }

    default DoubleUnaryOperatorEx andThen(DoubleUnaryOperatorEx after) {
        Objects.requireNonNull(after);
        return d -> after.applyAsDouble(applyAsDouble(d));
    }

    static DoubleUnaryOperatorEx identity() {
        return d -> d;
    }

    static DoubleUnaryOperator without(DoubleUnaryOperatorEx ex) {
        return without(ex, null);
    }

    static DoubleUnaryOperator without(DoubleUnaryOperatorEx ex, Class<?> annotatedClass) {
        Objects.requireNonNull(ex);
        return d -> {
            try {
                return ex.applyAsDouble(d);
            } catch (Exception e) {
                ex.catches(e, annotatedClass);
                return ex.returnDouble(e);
            }
        };
    }
}
