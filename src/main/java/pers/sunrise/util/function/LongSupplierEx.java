package pers.sunrise.util.function;

import pers.sunrise.util.function.ex.BaseEx;

import java.util.Objects;
import java.util.function.LongSupplier;

@FunctionalInterface
public interface LongSupplierEx extends BaseEx<Long> {

    long getAsLong() throws Exception;

    static LongSupplier without(LongSupplierEx ex) {
        return without(ex, null);
    }

    static LongSupplier without(LongSupplierEx ex, Class<?> annotatedClass) {
        Objects.requireNonNull(ex);
        return () -> {
            try {
                return ex.getAsLong();
            } catch (Exception e) {
                ex.catches(e, annotatedClass);
                return ex.returnLong(e);
            }
        };
    }
}
