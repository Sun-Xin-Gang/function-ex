package pers.sunrise.util.function;

import pers.sunrise.util.function.ex.BaseEx;

import java.util.Objects;
import java.util.function.DoubleBinaryOperator;

@FunctionalInterface
public interface DoubleBinaryOperatorEx extends BaseEx<Double> {

    double applyAsDouble(double l, double r) throws Exception;

    static DoubleBinaryOperator without(DoubleBinaryOperatorEx ex) {
        return without(ex, null);
    }

    static DoubleBinaryOperator without(DoubleBinaryOperatorEx ex, Class<?> annotatedClass) {
        Objects.requireNonNull(ex);
        return (l, r) -> {
            try {
                return ex.applyAsDouble(l, r);
            } catch (Exception e) {
                ex.catches(e, annotatedClass);
                return ex.returnDouble(e);
            }
        };
    }
}
