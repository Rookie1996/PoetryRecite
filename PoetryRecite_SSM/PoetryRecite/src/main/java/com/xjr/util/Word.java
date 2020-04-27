package com.xjr.util;

public class Word {
	
	//出现次数
	private int value;
	//热词名称
	private String name;

	public Word() {
		
	}
	
	public Word(String name, int value) {
		this.name = name;
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Word [value=" + value + ", name=" + name + "]";
	}
	
	
}
