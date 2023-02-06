package pers.sunrise.util.function;

import pers.sunrise.util.function.ex.BaseEx;

import java.util.Objects;
import java.util.function.LongToIntFunction;

@FunctionalInterface
public interface LongToIntFunctionEx extends BaseEx<Integer> {

    int applyAsInt(long l) throws Exception;

    static LongToIntFunction without(LongToIntFunctionEx ex) {
        return without(ex, null);
    }

    static LongToIntFunction without(LongToIntFunctionEx ex, Class<?> annotatedClass) {
        Objects.requireNonNull(ex);
        return l -> {
            try {
                return ex.applyAsInt(l);
            } catch (Exception e) {
                ex.catches(e, annotatedClass);
                return ex.returnInt(e);
            }
        };
    }
}
