package com.apcompany.api.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.apcompany.api.pojo.Student;

public interface StudentDao {
	
	final String table="student";
	
	@Select("select * from "+table+" where loginname=#{loginname} and password=#{password}")
	public Student loginin(String loginname,String password);
	
	@Select("select * from "+table+" where phone=#{phone} and password=#{password}")
	public Student loginin(Integer phone,String password);
	
	@Select("select count(1) from "+table+" where loginname=#{loginname}")
	public Integer isNameUsed(String loginname);
	
	@Insert("insert into "+table+" (name, password,sex,loginname,birthday,grade,phone,deviceId) "
			+ "values (#{name},#{password},#{sex},#{loginname},#{birthday},"
			+ "#{grade},#{phone},#{deviceId})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	public int insert(Student student);

	@Update("update "+table+" set name=#{name},password=#{password} sex=#{sex},"
			+ "loginname=#{loginname},birthday=#{birthday},"
			+ "grade=#{grade},phone=#{phone},deviceId=#{deviceId}"
			+ "where id=#{id}")
	public int update(Student student);


	@Delete(value = { "delete from "+table+" where id=#{id}" })
	public int delete(int id);
}
