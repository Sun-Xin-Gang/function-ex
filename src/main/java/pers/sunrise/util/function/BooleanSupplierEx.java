package pers.sunrise.util.function;

import pers.sunrise.util.function.ex.BaseEx;

import java.util.Objects;
import java.util.function.BooleanSupplier;

@FunctionalInterface
public interface BooleanSupplierEx extends BaseEx<Boolean> {

    boolean getAsBoolean() throws Exception;

    static BooleanSupplier without(BooleanSupplierEx ex) {
        return without(ex, null);
    }

    static BooleanSupplier without(BooleanSupplierEx ex, Class<?> annotatedClass) {
        Objects.requireNonNull(ex);
        return () -> {
            try {
                return ex.getAsBoolean();
            } catch (Exception e) {
                ex.catches(e, annotatedClass);
                return ex.returnBoolean(e);
            }
        };
    }
}
