package pers.sunrise.util.function;

import pers.sunrise.util.function.ex.BaseEx;

import java.util.Objects;
import java.util.function.DoubleSupplier;

@FunctionalInterface
public interface DoubleSupplierEx extends BaseEx<Double> {

    double getAsDouble() throws Exception;

    static DoubleSupplier without(DoubleSupplierEx ex) {
        return without(ex, null);
    }

    static DoubleSupplier without(DoubleSupplierEx ex, Class<?> annotatedClass) {
        Objects.requireNonNull(ex);
        return () -> {
            try {
                return ex.getAsDouble();
            } catch (Exception e) {
                ex.catches(e, annotatedClass);
                return ex.returnDouble(e);
            }
        };
    }
}
