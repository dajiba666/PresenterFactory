package myapp.apt_lib.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by yang2 on 2017/7/4.
 */
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.METHOD)
public @interface MemoryCache {
}
