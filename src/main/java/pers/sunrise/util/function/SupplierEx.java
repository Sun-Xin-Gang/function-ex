package pers.sunrise.util.function;

import pers.sunrise.util.function.ex.BaseEx;

import java.util.Objects;
import java.util.function.Supplier;

@FunctionalInterface
public interface SupplierEx<T> extends BaseEx<T> {

    T get() throws Exception;

    static <T> Supplier<T> without(SupplierEx<T> ex) {
        return without(ex, null);
    }

    static <T> Supplier<T> without(SupplierEx<T> ex, Class<?> annotatedClass) {
        Objects.requireNonNull(ex);
        return () -> {
            try {
                return ex.get();
            } catch (Exception e) {
                ex.catches(e, annotatedClass);
                return ex.returnObj(e);
            }
        };
    }
}
