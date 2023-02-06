package pers.sunrise.util.function;

import pers.sunrise.util.function.ex.BaseEx;

import java.util.Objects;
import java.util.function.DoubleConsumer;

@FunctionalInterface
public interface DoubleConsumerEx extends BaseEx<Void> {

    void accept(double d) throws Exception;

    default DoubleConsumerEx andThen(DoubleConsumerEx after) {
        Objects.requireNonNull(after);
        return d -> { accept(d); after.accept(d); };
    }

    static DoubleConsumer without(DoubleConsumerEx ex) {
        return without(ex, null);
    }

    static DoubleConsumer without(DoubleConsumerEx ex, Class<?> annotatedClass) {
        Objects.requireNonNull(ex);
        return d -> {
            try {
                ex.accept(d);
            } catch (Exception e) {
                ex.catches(e, annotatedClass);
            }
        };
    }
}
