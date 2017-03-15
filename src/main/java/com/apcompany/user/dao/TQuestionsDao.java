package com.apcompany.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.apcompany.user.pojo.TQuestions;

public interface TQuestionsDao {
	
	@Select("select * from questions where id=#{id}")
	public TQuestions getQuestions(int id);

	@Insert("insert into questions (question) values (#{question})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	public int insert(TQuestions questions);

	@Update("update questions set question=#{question} where id=#{id}")
	public int update(TQuestions questions);

	@Update("update questions set status=1 where id=#{id}")
	public int delete(int id);
	
	@Select("select count(1) from questions ")
	public Integer getQuestionsNum();
	
	public List<TQuestions> searchAll(@Param("questionid") int questionid);

}
