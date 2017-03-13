package com.apcompany.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apcompany.user.dao.TAnswersDao;
import com.apcompany.user.dao.TChoisesDao;
import com.apcompany.user.dao.TQuestionsDao;
import com.apcompany.user.pojo.TAnswers;
import com.apcompany.user.pojo.TChoises;
import com.apcompany.user.pojo.TQuestions;
import com.apcompany.user.service.DataService;

@Service("dataService")
public class DataServiceImpl implements DataService {

	@Autowired
	private TQuestionsDao tQuestionsDao;

	@Autowired
	private TAnswersDao tAnswerdao;

	@Autowired
	private TChoisesDao tChoisedao;

	@Override
	public boolean addData(TQuestions tQuestions, List<TChoises> tChoises, TAnswers tAnswers) {
		int out = 0;
		out = tQuestionsDao.insert(tQuestions);
		if (out != 1) {
			return false;
		}
		
		for (TChoises choise : tChoises) {
			choise.setQuestionid(tQuestions.getId());
			out = tChoisedao.insert(choise);
			if (out != 1) {
				return false;
			}
		}
		tAnswers.setQuestionid(tQuestions.getId());
		out = tAnswerdao.insert(tAnswers);
		if (out != 1) {
			return false;
		}
		return true;
	}

	@Override
	public boolean delData(int id) {
		int out = 0;
		out = tQuestionsDao.delete(id);
		if (out != 1) {
			return false;
		}
		out = tChoisedao.deleteByQuestionId(id);
		if (out != 1) {
			return false;
		}
		out = tAnswerdao.deleteByQuestionId(id);
		if (out != 1) {
			return false;
		}
		return true;

	}

	@Override
	public boolean updateData(TQuestions tQuestions, List<TChoises> tChoises, TAnswers tAnswers) {
		int out = 0;
		out = tQuestionsDao.update(tQuestions);
		if (out != 1) {
			return false;
		}
		for (TChoises choise : tChoises) {
			out = tChoisedao.update(choise);
		}

		out = tAnswerdao.update(tAnswers);
		if (out != 1) {
			return false;
		}
		return true;
	}

	@Override
	public int getDataCount() {
		int out = tQuestionsDao.getQuestionsNum();
		return out;
	}

	@Override
	public TQuestions getData(int questionid) {
		List<TQuestions> outlist = tQuestionsDao.searchAll(questionid);
		if (outlist != null && outlist.size() > 0) {
			return outlist.get(0);
		}
		return null;
	}

	@Override
	public List<TQuestions> getDataList(int questionid) {
		return tQuestionsDao.searchAll(questionid);
	}

}
