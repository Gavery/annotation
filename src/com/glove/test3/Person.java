package com.glove.test3;
@Description("Inherited interface")
public class Person {
	@Description("Inherited method")
	public String name() {
		return null;
	}

	public int age() {
		return 0;
	}

	@Deprecated
	public void sing() {}
}
