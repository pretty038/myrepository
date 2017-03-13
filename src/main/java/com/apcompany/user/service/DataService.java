package com.apcompany.user.service;

import java.util.List;

import com.apcompany.user.pojo.TAnswers;
import com.apcompany.user.pojo.TChoises;
import com.apcompany.user.pojo.TQuestions;

public interface DataService {

	public boolean addData(TQuestions tQuestions,List<TChoises> tChoises,TAnswers tAnswers);

	public boolean delData(int id);

	public boolean updateData(TQuestions tQuestions,List<TChoises> tChoises,TAnswers tAnswers);

	public int getDataCount();

	public TQuestions getData(int questionid);

	public List<TQuestions> getDataList(int questionid);

}
