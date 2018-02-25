package com.glove.practice;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/*
 * 注解实战
 * 需求：
 * 1.有一张用户表，字段包括用户id，用户名，昵称，年龄，性别，所在城市，邮箱，手机
 * 2.方便的对每个字段或字段的组合条件进行检索，并打印出sql语句
 * 3.使用方式要足够简单，见代码示例
 *  */
public class test {
public static void main(String[] args) throws Exception {
	Filter f1=new Filter();
	f1.setId(10);//表示查询id为10的用户
	
	Filter f2=new Filter();
	f2.setUserName("高家营");//查询用户名为lucy的用户
	
	Filter f3=new Filter();
	f3.setEmail("gjyallq@qq.com,2294626664@qq.com");//查询邮箱为其中任意一个
	
	String sql1=query(f1);
	String sql2=query(f2);
	String sql3=query(f3);
	System.out.println(sql1);
	System.out.println(sql2);
	System.out.println(sql3);
}
private static String query(Object f) throws Exception{
	StringBuilder sb=new StringBuilder();
	//开始解析注解
	//1.获取到class
	Class c=f.getClass();
	//2.获取到table的名字(判断是否是一个table对象)
	boolean exists=c.isAnnotationPresent(Table.class);
	if(!exists) {
		return null;
	}
	//3.获取table注解对象
	Table t=(Table)c.getAnnotation(Table.class);
	//4.获取table注解里面的名字（user）
	String tableName=t.value();
	//5.拼接字符串
	sb.append("select*from ").append(tableName).append(" where 1=1");
	//6.遍历所有的字段
	Field[] fArray=c.getDeclaredFields();
	//遍历所有的字段
	for(Field field:fArray) {
		//吃力每个字段对应的sql
		//拿到字段名
		boolean fExists=field.isAnnotationPresent(Column.class);
		if(!fExists) {
			continue;
		}
		Column column=field.getAnnotation(Column.class);
		String columnName=column.value();
		//拿到字段的值
		String filedName=field.getName();
		System.out.println(filedName+"---------");
		//获取字段相应的get方法，以便调用来获取字段的值
		String getMethodName="get"+filedName.substring(0, 1).toUpperCase()+filedName.substring(1);
		Object fieldValue=null;
		Method getMethod=c.getMethod(getMethodName);//获取相应的字段的get方法
		//通过反射来获取值
		fieldValue=getMethod.invoke(f);
		//拼装sql
		if(fieldValue==null||(fieldValue instanceof Integer &&(Integer)fieldValue==0)) {
			continue;//如果不满足条件就不进行处理
		}
		sb.append(" and ").append(filedName);
		if(fieldValue instanceof String) {
			if(((String)fieldValue).contains(",")) {
				String[] values=((String) fieldValue).split(",");
				sb.append(" in(");
				for(String v:values) {
					sb.append("'").append(v).append("'").append(",");
				
				}
				
				sb.deleteCharAt(sb.length()-1);//删掉最后一个逗号
				sb.append(")");
			}else {
					sb.append("=").append("'").append(fieldValue).append("'");
				}

		}else if(fieldValue instanceof Integer) {
			sb.append("=").append(fieldValue);
		}
	}
	
	return sb.toString();
}
}
