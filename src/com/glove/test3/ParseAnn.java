package com.glove.test3;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/*
 * 解析注解
 */
public class ParseAnn {
	public static void main(String[] args) throws ClassNotFoundException {
		//1.使用类加载器加载类
		//Class c=Class.forName("com.glove.test3.test");
		Class c=Class.forName("com.glove.test3.Child");//注解的继承
		//2.找到类上面的注解
		/*
		 * isAnnotationPresent方法判断该类或者方法上是否存在Description的注解，返回boolean值
		 */
		boolean isExist=c.isAnnotationPresent(Description.class);
		if(isExist) {
			//3.拿到注解实例
			Description d=(Description) c.getAnnotation(Description.class);
			System.out.println(d.value());//就得到类注解上面的值
		}
		
		//找到方法上的注解(需要循环遍历所有的方法)
		Method[] ms=c.getMethods();
		for(Method m:ms) {
			boolean isMExist=m.isAnnotationPresent(Description.class);
			if(isMExist) {
				Description d=(Description) m.getAnnotation(Description.class);
				System.out.println(d.value());
			}
		}
		System.out.println("--------------------");
		//另一种解析方法
		for(Method m:ms) {
			Annotation[] as=m.getAnnotations();
			for(Annotation a:as) {
				if(a instanceof Description) {
					Description d=(Description)a;
					System.out.println(d.value());
				}
			}
		}
		
	}
}
