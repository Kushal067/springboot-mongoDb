package com.example.SpringMongo2.model;

public class Test {
	private String check;
	private String check2;

	public String getCheck() {
		return check;
	}

	public void setCheck(String check) {
		this.check = check;
	}
	
	public String getCheck2() {
		return check2;
	}

	public void setCheck2(String check2) {
		this.check = check2;
	}

	@Override
	public String toString() {
		return "Test [check=" + check + ", check2=" + check2 + "]";
	}

	public Test(String check, String check2) {
		super();
		this.check = check;
		this.check2=check2;
	}
	
}
