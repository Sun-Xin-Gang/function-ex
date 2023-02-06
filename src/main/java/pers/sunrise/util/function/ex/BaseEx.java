package pers.sunrise.util.function.ex;

import pers.sunrise.util.function.ex.annotation.ExceptionCatcher;
import pers.sunrise.util.function.ex.annotation.ExceptionCatchers;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;

@ExceptionCatcher
public interface BaseEx<R> {

    @SuppressWarnings("unchecked")
    default void catches(Exception cause, Class<?> annotatedClass) throws RuntimeException {
        List<ExceptionCatcher> catchers = new ArrayList<>();
        if (annotatedClass != null) {
            if (annotatedClass.isAnnotationPresent(ExceptionCatchers.class)) {
                catchers.addAll(Arrays.asList(annotatedClass.getAnnotation(ExceptionCatchers.class).value()));
            } else if (annotatedClass.isAnnotationPresent(ExceptionCatcher.class)) {
                catchers.add(annotatedClass.getAnnotation(ExceptionCatcher.class));
            }
        }
        catchers.add(BaseEx.class.getAnnotation(ExceptionCatcher.class)); // the default last one catcher

        for (ExceptionCatcher catcher : catchers) {
            Class<? extends Exception> cls = catcher.value();
            boolean isInstance;
            if (cls == CheckedException.class) {
                   isInstance = !(cause instanceof RuntimeException);
            } else isInstance = cls.isInstance(cause);

            if (isInstance) {
                Class<? extends BiConsumer<ExceptionCatcher, ? super Exception>> consumerCls = catcher.consumer();
                BiConsumer<ExceptionCatcher, ? super Exception> consumer = null;
                try {
                    Method getInstance = consumerCls.getMethod("getInstance");
                    consumer = (BiConsumer<ExceptionCatcher, ? super Exception>) getInstance.invoke(null);
                } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException ignored) { }

                if (consumer == null) {
                    try {
                        Constructor<?> constructor = consumerCls.getConstructor();
                        consumer = (BiConsumer<ExceptionCatcher, ? super Exception>) constructor.newInstance();
                    } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException | InstantiationException ignored) { }
                }

                if (consumer == null) throw new CheckedRuntimeException("neither getInstance() nor Constructor() can be found in " + consumerCls.getName(), new NoSuchMethodException());

                consumer.accept(catcher, cause);
                return;
            }
        }
        throw (RuntimeException) cause; // unchecked RuntimeException
    }

    default boolean returnBoolean(Exception e) {
        Boolean r = (Boolean) returnObj(e);
        return r != null && r;
    }

    default int returnInt(Exception e) {
        Integer r = (Integer) returnObj(e);
        return r != null ? r : 0;
    }

    default long returnLong(Exception e) {
        Long r = (Long) returnObj(e);
        return r != null ? r : 0L;
    }

    default double returnDouble(Exception e) {
        Double r = (Double) returnObj(e);
        return r != null ? r : 0D;
    }

    default R returnObj(Exception e) {
        return null;
    }
}
