package com.apcompany.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.apcompany.user.pojo.TLabelsQuestionRel;

public interface TLabelQuestionRelDao {
	@Select("select * from labels_question_rel where questionid=#{questionid}")
	public List<TLabelsQuestionRel> select(int questionid);

	@Insert("insert into labels_question_rel (labelid,questionid) values (#{labelid},#{questionid})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	public int insert(TLabelsQuestionRel tLabelsQuestionRel);

	@Update("update labels_question_rel set labelide=#{labelid} where questionid=#{questionid}")
	public int update(TLabelsQuestionRel tLabelsQuestionRel);

	@Update("update labels_question_rel set status=1 where id=#{id}")
	public int delete(int id);
}
