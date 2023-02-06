package pers.sunrise.util.function;

import pers.sunrise.util.function.ex.BaseEx;

import java.util.Objects;
import java.util.function.Consumer;

@FunctionalInterface
public interface ConsumerEx<T> extends BaseEx<Void> {

    void accept(T t) throws Exception;

    default ConsumerEx<T> andThen(ConsumerEx<? super T> after) {
        Objects.requireNonNull(after);
        return t -> { accept(t); after.accept(t); };
    }

    static <T> Consumer<T> without(ConsumerEx<? super T> ex) {
        return without(ex, null);
    }

    static <T> Consumer<T> without(ConsumerEx<? super T> ex, Class<?> annotatedClass) {
        Objects.requireNonNull(ex);
        return t -> {
            try {
                ex.accept(t);
            } catch (Exception e) {
                ex.catches(e, annotatedClass);
            }
        };
    }
}
