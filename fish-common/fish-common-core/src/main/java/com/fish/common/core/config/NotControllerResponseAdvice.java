package com.fish.common.core.config;

import java.lang.annotation.*;

/**
 * 不需要相应包装类的就加这个注解
 *
 * @author dayang
 */
@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NotControllerResponseAdvice {

}
