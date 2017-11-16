package com.apcompany.api.model.pojo;

import com.apcompany.api.constrant.UserType;

public class TokenModel {
	
	private String token;
	
	private int userId;
	
	private int userType;
	
	public TokenModel(){
		
	}
	
    public TokenModel(String token,int userId,UserType type){
		this.token=token;
		this.userId=userId;
		this.userType=type.getKey();
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}
	
	

}
