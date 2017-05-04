package com.apcompany.user.service;

import com.apcompany.user.pojo.THits;

public interface HitsService {

	public THits getTHits(int question_id,int step);

	public int insertTHits(THits tHits);

	public int updateTHits(THits tHits);

	public int deleteTHits(int id);
}
