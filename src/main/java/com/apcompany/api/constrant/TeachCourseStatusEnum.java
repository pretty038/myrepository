package com.apcompany.api.constrant;

import java.util.Map;

import com.google.common.collect.Maps;

public enum TeachCourseStatusEnum {
	
	CLOSED(0,"关闭"),NORMAL(1,"正常"),;
	
	public final int key;
	public final String value;
	
	private static Map<Integer, TeachCourseStatusEnum> allUserStatusEnum = Maps.newHashMap();
	
	static{
		allUserStatusEnum.put(0, CLOSED);
		allUserStatusEnum.put(1, NORMAL);
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
