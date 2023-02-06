package pers.sunrise.util.function;

import pers.sunrise.util.function.ex.BaseEx;

import java.util.Objects;
import java.util.function.IntConsumer;

@FunctionalInterface
public interface IntConsumerEx extends BaseEx<Integer> {

    void accept(int i) throws Exception;

    default IntConsumerEx andThen(IntConsumerEx after) {
        Objects.requireNonNull(after);
        return i -> { accept(i); after.accept(i); };
    }

    static IntConsumer without(IntConsumerEx ex) {
        return without(ex, null);
    }

    static IntConsumer without(IntConsumerEx ex, Class<?> annotatedClass) {
        Objects.requireNonNull(ex);
        return i -> {
            try {
                ex.accept(i);
            } catch (Exception e) {
                ex.catches(e, annotatedClass);
            }
        };
    }
}
