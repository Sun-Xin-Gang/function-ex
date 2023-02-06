package pers.sunrise.util.function;

import pers.sunrise.util.function.ex.BaseEx;

import java.util.Objects;
import java.util.function.ObjDoubleConsumer;

@FunctionalInterface
public interface ObjDoubleConsumerEx<T> extends BaseEx<Void> {

    void accept(T t, double d) throws Exception;

    static <T> ObjDoubleConsumer<T> without(ObjDoubleConsumerEx<T> ex) {
        return without(ex, null);
    }

    static <T> ObjDoubleConsumer<T> without(ObjDoubleConsumerEx<T> ex, Class<?> annotatedClass) {
        Objects.requireNonNull(ex);
        return (t, d) -> {
            try {
                ex.accept(t, d);
            } catch (Exception e) {
                ex.catches(e, annotatedClass);
            }
        };
    }
}
