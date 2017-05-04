package com.apcompany.user.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.apcompany.user.pojo.THits;

public interface THitsDao {

	@Select("select * from hits where question_id=#{question_id} and step=#{step}")
	public THits getTHits(int question_id,int step);

	@Insert("insert into hits (question_id,step,img_string) values (#{question_id},#{step},#{img_string})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	public int insert(THits tHits);

	@Update("update hits set question_id=#{question_id},step=#{step},img_string=#{img_string} where id=#{id}")
	public int update(THits tHits);

	@Delete("delete from hits where id=#{id}")
	public int delete(int id);

}
