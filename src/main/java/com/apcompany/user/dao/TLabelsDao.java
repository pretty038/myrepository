package com.apcompany.user.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.apcompany.user.pojo.TLabels;

public interface TLabelsDao {

	@Select("select * from labels where id=#{id}")
	public TLabels select(int id);

	@Insert("insert into labels (labelname) values (#{labelname})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	public int insert(TLabels tLabels);

	@Update("update labels set labelname=#{labelname} where id=#{id}")
	public int update(TLabels tLabels);

	@Update("update labels set status=1 where id=#{id}")
	public int delete(int id);

}
