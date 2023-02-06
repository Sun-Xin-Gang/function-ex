package pers.sunrise.util.function.ex.annotation;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ExceptionCatchers {

    ExceptionCatcher[] value();
}
