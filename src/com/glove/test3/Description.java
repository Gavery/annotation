package com.glove.test3;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Inherited //只能用在extends上，不能用于接口实现上，并且只能继承类上的注解，方法上的不行
@Documented 
public @interface Description {
	 String value();

}
