package pers.sunrise.util.function.ex.annotation;

import pers.sunrise.util.function.ex.CheckedException;
import pers.sunrise.util.function.ex.CheckedRuntimeException;
import pers.sunrise.util.function.ex.ToRuntimeException;

import java.lang.annotation.*;
import java.util.function.BiConsumer;

/**
 * 捕获何种异常、如何处理异常的标签式注解（可重复标注）。用于 function-ex 框架定制化处理流程。
 * @author 孙欣刚（ xingang.sun@hotmail.com ）
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Repeatable(ExceptionCatchers.class)
public @interface ExceptionCatcher {

    Class<? extends Exception> value() default CheckedException.class;

    Class<? extends BiConsumer<ExceptionCatcher, ? super Exception>> consumer() default ToRuntimeException.class;

    Class<? extends RuntimeException> to() default CheckedRuntimeException.class;

    String message() default "";
}
