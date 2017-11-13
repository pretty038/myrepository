package com.apcompany.api.constrant;

import java.util.Map;

import com.google.common.collect.Maps;

public enum OnlineTeachCourseOrderByEnum {
	
	MONEY(1,"t_c.money_per_minute"),SCORE(2,"t_c.score");
	
	public final int key;
	public final String value;
	
	private static Map<Integer, OnlineTeachCourseOrderByEnum> allUserTypes = Maps.newHashMap();
	
	static{
		allUserTypes.put(1, MONEY);
		allUserTypes.put(2, SCORE);
	}
	
	OnlineTeachCourseOrderByEnum(int key,String value){
		this.key=key;
		this.value=value;
	}

	public int getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}
	
	public static OnlineTeachCourseOrderByEnum valueOf(Integer key) {
		if(key==null){
			return null;
		}
		return allUserTypes.get(key);
	}


}
