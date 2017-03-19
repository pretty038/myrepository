package com.apcompany.user.service;

import java.util.List;

import com.apcompany.user.pojo.TAnswers;
import com.apcompany.user.pojo.TChoises;
import com.apcompany.user.pojo.TLabels;
import com.apcompany.user.pojo.TLabelsQuestionRel;
import com.apcompany.user.pojo.TQuestions;

public interface DataService {

	public boolean addData(TQuestions tQuestions, List<TChoises> tChoises, TAnswers tAnswers, List<TLabelsQuestionRel> tLabelsQuestionRel);

	public boolean delData(int id);

	public boolean updateData(TQuestions tQuestions, List<TChoises> tChoises, TAnswers tAnswers);

	public int getDataCount();

	public TQuestions getData(int questionid);

	public List<TQuestions> getDataList(int questionid,Integer totalcount,Integer curPage, Integer pageSize);

	public int updateQuestion(TQuestions tQuestions);

	public int updateChiose(TChoises tChoises);

	public int updateAnswers(TAnswers tAnswers);

	public boolean addLabel(TLabels tLabels);

	public int updateLabel(TLabels tLabels);

	public int delLabel(int id);

	public int updateQuestionLabel(TLabelsQuestionRel tLabelsQuestionRel);
	
	public int delQuestionLabel(int id);
	
	
	public List<TLabels> selectByName(Integer totalcount,Integer pageStart,Integer pageSize,String names);
	
	public int countSelectByName(String names);

}
