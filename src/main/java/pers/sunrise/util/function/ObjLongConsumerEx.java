package pers.sunrise.util.function;

import pers.sunrise.util.function.ex.BaseEx;

import java.util.Objects;
import java.util.function.ObjLongConsumer;

@FunctionalInterface
public interface ObjLongConsumerEx<T> extends BaseEx<Void> {

    void accept(T t, long l) throws Exception;

    static <T> ObjLongConsumer<T> without(ObjLongConsumerEx<T> ex) {
        return without(ex, null);
    }

    static <T> ObjLongConsumer<T> without(ObjLongConsumerEx<T> ex, Class<?> annotatedClass) {
        Objects.requireNonNull(ex);
        return (t, l) -> {
            try {
                ex.accept(t, l);
            } catch (Exception e) {
                ex.catches(e,annotatedClass);
            }
        };
    }
}
