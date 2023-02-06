package pers.sunrise.util.function;

import pers.sunrise.util.function.ex.BaseEx;

import java.util.Objects;
import java.util.function.ObjIntConsumer;

@FunctionalInterface
public interface ObjIntConsumerEx<T> extends BaseEx<Void> {

    void accept(T t, int i) throws Exception;

    static <T> ObjIntConsumer<T> without(ObjIntConsumerEx<T> ex) {
        return without(ex, null);
    }

    static <T> ObjIntConsumer<T> without(ObjIntConsumerEx<T> ex, Class<?> annotatedClass) {
        Objects.requireNonNull(ex);
        return (t, i) -> {
            try {
                ex.accept(t, i);
            } catch (Exception e) {
                ex.catches(e, annotatedClass);
            }
        };
    }
}
