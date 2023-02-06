package pers.sunrise.util.function;

import pers.sunrise.util.function.ex.BaseEx;

import java.util.Objects;
import java.util.function.IntSupplier;

@FunctionalInterface
public interface IntSupplierEx extends BaseEx<Integer> {

    int getAsInt() throws Exception;

    static IntSupplier without(IntSupplierEx ex) {
        return without(ex, null);
    }

    static IntSupplier without(IntSupplierEx ex, Class<?> annotatedClass) {
        Objects.requireNonNull(ex);
        return () -> {
            try {
                return ex.getAsInt();
            } catch (Exception e) {
                ex.catches(e, annotatedClass);
                return ex.returnInt(e);
            }
        };
    }
}
