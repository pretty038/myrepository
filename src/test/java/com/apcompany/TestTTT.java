package com.apcompany;

public class TestTTT {

	public void prinft(long ...test){
		System.out.println(test.length);
	}
	
	public static void main(String agrs[]){
		TestTTT t=new TestTTT();
		t.prinft(12,34,4545);
		t.prinft(new long[]{12,23,34});
	}
}
