package pers.sunrise.util.function;

import pers.sunrise.util.function.ex.BaseEx;

import java.util.Objects;
import java.util.function.LongConsumer;

@FunctionalInterface
public interface LongConsumerEx extends BaseEx<Void> {

    void accept(long l) throws Exception;

    default LongConsumerEx andThen(LongConsumerEx after) {
        Objects.requireNonNull(after);
        return l -> { accept(l); after.accept(l); };
    }

    static LongConsumer without(LongConsumerEx ex) {
        return without(ex, null);
    }

    static LongConsumer without(LongConsumerEx ex, Class<?> annotatedClass) {
        Objects.requireNonNull(ex);
        return l -> {
            try {
                ex.accept(l);
            } catch (Exception e) {
                ex.catches(e, annotatedClass);
            }
        };
    }
}
