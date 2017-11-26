package com.apcompany.api.constrant;

import java.util.Map;

import com.google.common.collect.Maps;

public enum MessagePushEnum {
    OPEN_VIDEO("open video");
	
	
	public final String value;
	
	private static Map<Integer, MessagePushEnum> allUserTypes = Maps.newHashMap();
	
	static{
		allUserTypes.put(1, OPEN_VIDEO);
		
	}
	
	MessagePushEnum(String value){
		
		this.value=value;
	}

	
	
	public String getValue() {
		return value;
	}



	public static MessagePushEnum valueOf(Integer key) {
		if(key==null){
			return OPEN_VIDEO;
		}
		MessagePushEnum result =allUserTypes.get(key);
		return result==null?OPEN_VIDEO:result;
	}

}
