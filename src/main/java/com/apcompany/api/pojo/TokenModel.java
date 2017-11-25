package com.apcompany.api.pojo;

import com.apcompany.api.constrant.UserType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TokenModel {
	private String token;
	private Integer userId;
	private UserType userType;
	
	public TokenModel(){}
	
	public TokenModel(String token,Integer userId,UserType userType){
		this.token=token;
		this.userId = userId;
		this.userType= userType;
	}
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public UserType getUserType() {
		return userType;
	}
	public void setUserType(UserType userType) {
		this.userType = userType;
	}
	
	@Override
	public String toString() {
		ObjectMapper o=new ObjectMapper();
		try {
			return o.writeValueAsString(this);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	

}
