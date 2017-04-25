package com.apcompany.user.service;

import com.apcompany.user.pojo.TKeyWords;
import com.apcompany.user.pojo.TTranslations;

public interface TransLateService {

	public TKeyWords getKeyWords(int id);

	public int insertKeyWords(TKeyWords tKeyWords);

	public int updateKeyWords(TKeyWords tKeyWords);

	public int deleteKeyWords(int id);

	public TTranslations getTranslations(int id);

	public TTranslations getTranslationsByQuestionId(int questionId);

	public int insertTranslations(TTranslations tTranslations);

	public int updateTranslations(TTranslations tTranslations);

	public int updateTranslationsByQuestionId(TTranslations tTranslations);

	public int deleteTranslations(int id);
}
