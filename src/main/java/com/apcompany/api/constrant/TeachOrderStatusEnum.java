package com.apcompany.api.constrant;

public enum TeachOrderStatusEnum {
	
	RUNNING(0,"教学中"),
	FINISHED(1,"教学结束"),
	PAYED(2,"已支付"),
	MARKED(3,"已评价");
	
	private final int value;
    private final String desc;

    TeachOrderStatusEnum(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

	public int getValue() {
		return value;
	}

	public String getDesc() {
		return desc;
	}
	
    

}
