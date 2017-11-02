package com.apcompany.api.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.apcompany.api.pojo.TeacherClass;

public interface TeacherClassDao {
	
	final String table="teacher_class";
	
	@Select("select * from teacher_class where tid = #{tid}")
	@Results({
		@Result(id=true,column="id",property="id"),
		@Result(column="tid",property="tid"),
		@Result(column="type",property="type")
	})
	public List<TeacherClass> selectByTeacher(Integer tid);
	
	@Insert("insert into "+table+"(tid,type) values(#{tid},#{type})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	public int insert(TeacherClass teacherClass);
	
	@Update("update "+table+ "set tid=#{tid},type=#{type} where id=#{id}")
	public int updateByObject(TeacherClass teacherClass);
	
	
	
	@Update("update "+table+ "set tid=#{tid}, type=#{type} where id=#{id}")
	public int updateByPara(@Param("tid") Integer tid,@Param("type") Integer type);
	
	@Select("select type from teacher_class where tid = #{tid}")
	public List<Integer> selectIdsByTeacher(Integer tid);
	
	@Delete("delete from "+table+" where tid=#{tid}")
	public int delByTid(Integer tid);

}
