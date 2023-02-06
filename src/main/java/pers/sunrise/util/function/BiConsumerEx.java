package pers.sunrise.util.function;

import pers.sunrise.util.function.ex.BaseEx;

import java.util.Objects;
import java.util.function.BiConsumer;

/**
 * Functional interface BiConsumer with throws Exception.
 * @author 孙欣刚（ xingang.sun@hotmail.com ）
 * @param <T> the type of the first argument to the operation
 * @param <U> the type of the second argument to the operation
 * @see java.util.function.BiConsumer
 */
@FunctionalInterface
public interface BiConsumerEx<T, U> extends BaseEx<Void> {

    void accept(T t, U u) throws Exception;

    default BiConsumerEx<T, U> andThen(BiConsumerEx<? super T, ? super U> after) {
        Objects.requireNonNull(after);
        return (l, r) -> { accept(l, r); after.accept(l, r); };
    }

    static <T, U> BiConsumer<T, U> without(BiConsumerEx<? super T, ? super U> ex) {
        return without(ex, null);
    }

    static <T, U> BiConsumer<T, U> without(BiConsumerEx<? super T, ? super U> ex, Class<?> annotatedClass) {
        Objects.requireNonNull(ex);
        return (t, u) -> {
            try {
                ex.accept(t, u);
            } catch (Exception e) {
                ex.catches(e, annotatedClass);
            }
        };
    }
}
