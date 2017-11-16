package com.apcompany.api.service.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apcompany.api.constrant.UserStatusEnum;
import com.apcompany.api.constrant.UserType;
import com.apcompany.api.dao.UserOnlineInfoDao;
import com.apcompany.api.model.pojo.UserOnlineInfoDO;
import com.apcompany.api.service.ITeachCourseService;
import com.apcompany.api.service.IUserOnlineInfoService;

@Service
public class UserOnlineInfoServiceImp implements IUserOnlineInfoService {
	
	@Autowired private UserOnlineInfoDao onlineInfoDao;

	@Autowired private ITeachCourseService teachCourseService;

	@Override
	public boolean checkAccessStatus(int userId, UserType userType, String token) {
		UserOnlineInfoDO  userOnlineInfoDO = onlineInfoDao.checkAccessByToken(userId, userType.getKey(),token);
		if(userOnlineInfoDO==null){
			return false;
		}
		onlineInfoDao.refreshAccessTime(userOnlineInfoDO.getId());
		return true;
	}

	@Override
	public boolean checkTeacherOnlineAndNotBusy(int teachCourseId) {
		Integer id = onlineInfoDao.checkTeacherIsFree(teachCourseId);
		if(id == null){
			return false;
		}
		return true;
	}

	@Override
	public boolean quitAccess(int userId, int type) {
		onlineInfoDao.quitAccess(userId, type,UserStatusEnum.OFFLINE.getKey());
		return true;
	}

	@Override
	public String addWithLogin(int userId, int type,UserStatusEnum status,double lat, double lng) {
		try {
			String token= UUID.randomUUID().toString().replaceAll("-", "");
			UserOnlineInfoDO userOnlineInfoDO = new UserOnlineInfoDO(userId, type,status, token, lat, lng);
			onlineInfoDao.addwithLogin(userOnlineInfoDO);
			return token;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean updateTeacherStatus(int teacherId, int status) {
		onlineInfoDao.updateTeacherStatus(teacherId, status);
		return true;
	}

	@Override
	public void offlineAuto(String lastAccessTime) {
		onlineInfoDao.offlineAuto(lastAccessTime);		
	}
	
	public static void main(String[] args) {
		System.out.println(UUID.randomUUID().toString().replaceAll("-", "").length());
	}

}
