package com.apcompany.api.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.apcompany.api.model.pojo.UserOnlineInfoDO;

public interface UserOnlineInfoDao {
	
	@Insert("replace into user_online_info(user_id,type,token,status,address,access_time,created) "
			+ "values(#{userId},#{type},#{token},#{status},point(${lat},${lng}),"
			+ "now(),now()) ")
	public void addwithLogin(UserOnlineInfoDO userOnlineInfoDO);
		
	@Select("select user_online_info.id as id from user_online_info where type=1 and "
			+ " status=1 inner join teach_course on user_online_info.user_id=teach_course.teacher_id "
			+ " and teach_course.status=1 and teach_course.id=#{id}")
	public Integer checkTCNormal(@Param("id")int Id);
	
	@Update("update user_online_info set status=#{status} where id=#{id}")
	public void updateStatusByID(int id, int status);
	
	@Select("select * from user_online_info where token=#{token} and status=1")
	public UserOnlineInfoDO checkAccessByToken(@Param("token")String token);
	
	@Update("update user_online_info set access_time=now() where id=#{id}")
	public void refreshAccessTime(int id);
	
	@Update("update user_online_info set status=#{status} where userId=#{userId} and type=#{type}")
	public void quitAccess(@Param("userId")int userId, @Param("type")int type,@Param("status")int status);
	
	@Update("update user_online_info set status=#{status} where user_id=#{teacherId} and type=1")
	public void updateTeacherStatus(int teacherId,int status);
	
	@Update("update user_online_info set status=0 where access_time<#{lastAccessTime}")
	public void offlineAuto(String lastAccessTime);
	
	@Update("update user_online_info set channel=#{channel} where userId=#{userId} and type=#{type}")
	void addChannel(@Param("userId")int userId, @Param("type")int type,@Param("channel")String channel);
	
	@Select("select user_online_info.channel  from teach_course where teach_course.id=#{tcId} "
			+ " inner join user_online_info on user_online_info.user_id=teach_course.teacher_id where user_online_info.type=1 ")
	String getChannelByTCId(int tcId);

}
