package com.apcompany.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apcompany.user.dao.TAnswersDao;
import com.apcompany.user.dao.TChoisesDao;
import com.apcompany.user.dao.TLabelQuestionRelDao;
import com.apcompany.user.dao.TLabelsDao;
import com.apcompany.user.dao.TQuestionsDao;
import com.apcompany.user.pojo.TAnswers;
import com.apcompany.user.pojo.TChoises;
import com.apcompany.user.pojo.TLabels;
import com.apcompany.user.pojo.TLabelsQuestionRel;
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
	@Autowired
	private TLabelsDao tLabelsDao;
	@Autowired
	private TLabelQuestionRelDao tLabelQuestionRelDao;

	@Override
	public boolean addData(TQuestions tQuestions, List<TChoises> tChoises, TAnswers tAnswers,
			List<TLabelsQuestionRel> tLabelsQuestionRel) {
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
		for (TLabelsQuestionRel tQuestionRel : tLabelsQuestionRel) {
			tQuestionRel.setQuestionid((int) tQuestions.getId());
			out = tLabelQuestionRelDao.insert(tQuestionRel);
			if (out != 1) {
				return false;
			}
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
		List<TQuestions> outlist = tQuestionsDao.searchAll(questionid, 0, 20);
		if (outlist != null && outlist.size() > 0) {
			return outlist.get(0);
		}
		return null;
	}

	@Override
	public List<TQuestions> getDataList(int questionid, Integer totalcount, Integer curPage, Integer pageSize) {
		curPage = curPage < 1 ? 1 : curPage;
		int pageStart = (curPage - 1) * pageSize;
		if (pageStart <= totalcount) {
			return tQuestionsDao.searchAll(questionid, pageStart, pageSize);
		} else {
			return null;
		}

	}

	@Override
	public int updateQuestion(TQuestions tQuestions) {
		return tQuestionsDao.update(tQuestions);
	}

	@Override
	public int updateChiose(TChoises tChoises) {
		return tChoisedao.update(tChoises);
	}

	@Override
	public int updateAnswers(TAnswers tAnswers) {
		return tAnswerdao.update(tAnswers);
	}

	@Override
	public boolean addLabel(TLabels tLabels) {
		int k = tLabelsDao.insert(tLabels);
		return k > 0;
	}

	@Override
	public int updateLabel(TLabels tLabels) {
		return tLabelsDao.update(tLabels);
	}

	@Override
	public int delLabel(int id) {
		return tLabelsDao.delete(id);
	}

	@Override
	public int updateQuestionLabel(TLabelsQuestionRel tLabelsQuestionRel) {
		return tLabelQuestionRelDao.update(tLabelsQuestionRel);
	}

	@Override
	public int delQuestionLabel(int id) {
		return tLabelQuestionRelDao.delete(id);
	}

	@Override
	public List<TLabels> selectByName(Integer totalcount, Integer curPage, Integer pageSize, String names) {
		curPage = curPage < 1 ? 1 : curPage;
		int pageStart = (curPage - 1) * pageSize;
		if (pageStart <= totalcount) {
			return tLabelsDao.selectByName(pageStart, pageSize, names);
		} else {
			return null;
		}

	}

	@Override
	public int countSelectByName(String names) {
		return tLabelsDao.countByName(names);
	}

	@Override
	public List<TLabels> selectAllLabels(Integer totalcount, Integer curPage, Integer pageSize) {
		curPage = curPage < 1 ? 1 : curPage;
		int pageStart = (curPage - 1) * pageSize;
		if (pageStart <= totalcount) {
			return tLabelsDao.selectAll(pageStart, pageSize);
		} else {
			return null;
		}
	}

	@Override
	public int countLabels() {
		return tLabelsDao.countAll();
	}

}
