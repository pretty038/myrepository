package com.apcompany.api.constrant;

import java.util.Map;

import com.google.common.collect.Maps;

public enum TeachCourseStatusEnum {
	
	CLOSE(0,"关闭"),NORMAL(1,"正常"),BUSY(2,"忙碌");
	
	public final int key;
	public final String value;
	
	private static Map<Integer, TeachCourseStatusEnum> allUserStatusEnum = Maps.newHashMap();
	
	static{
		allUserStatusEnum.put(0, CLOSE);
		allUserStatusEnum.put(1, NORMAL);
		allUserStatusEnum.put(2, BUSY);
	}
	
	TeachCourseStatusEnum(int key,String value){
		this.key=key;
		this.value=value;
	}

	public int getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}

	public static TeachCourseStatusEnum valueOf(int key){
		return allUserStatusEnum.get(key);
	}

}
