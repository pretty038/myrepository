package com.apcompany.api.util;

public class CommonUtil {
	
	public static Integer parseToInteger(String number){
		try {
			if(number==null){
				return null;
			}
			return Integer.valueOf(number);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
