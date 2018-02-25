package com.glove.test2;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.METHOD, ElementType.TYPE }) // 注解的作用域，就是说允许此注解运用在成员上，方法上还是类上(type代表类和接口)
@Retention(RetentionPolicy.RUNTIME) // 注解的生命周期，有编译时，运行时，源代码时
@Inherited // 是否运行该注解被继承
@Documented // 生成javadoc时会生成注解（因为没有参数，所以是一个标识注解）
public @interface Description {
	/*
	 * 以下都是成员变量，并且该属性不能有参数和异常否则会报错
	 * java的成员类型是受限制的，不允许map，set等，允许java的原始数据类型，类，枚举，string,annotation
	 * 如果注解只有一个成员，则成员名必须取名为value(),在使用时候可以忽略成员名和赋值号（=）
	 * 注解可以没有成员，没有成员的注解叫 “标识注解”
	 */
	String desc();

	String author();

	int age() default 18;// 代表给这个成员赋一个默认值
	
	/*
	 * 只有一个成员时候
	 * String value();
	 */
}
