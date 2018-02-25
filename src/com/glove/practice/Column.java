package com.glove.practice;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})//作用在字段上
@Retention(RetentionPolicy.RUNTIME)
public @interface Column {
	String value();
}
