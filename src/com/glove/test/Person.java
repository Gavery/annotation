package com.glove.test;

public interface Person {
public String name();
public int age();

/*
 * @Deprecated
 * 代表过时的方法
 */
@Deprecated
public void sing();
}
