package com.fruitday.boot.config.valid;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * 自定义校验
 */
@Constraint(validatedBy = {DemoValidator.class}) //验证注解实现类
@Documented
@Target({ElementType.ANNOTATION_TYPE, ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DemoValid {
    String message() default "超过最大限制，长度不能超过{max}";

    int max() default 10;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
