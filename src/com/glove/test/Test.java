package com.glove.test;

public class Test {
	@SuppressWarnings("deprecation") // 代表忽略过时的方法带来的警告
	public void sing() {
		Person p = new Child();
		p.sing();// 横杠代表方法过时
	}
}
