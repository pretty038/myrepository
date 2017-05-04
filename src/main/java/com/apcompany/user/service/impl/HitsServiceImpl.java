package com.apcompany.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apcompany.user.dao.THitsDao;
import com.apcompany.user.pojo.THits;
import com.apcompany.user.service.HitsService;
@Service
public class HitsServiceImpl implements HitsService {

	@Autowired
	private THitsDao tHitsDao;

	@Override
	public THits getTHits(int question_id, int step) {
		// TODO Auto-generated method stub
		return tHitsDao.getTHits(question_id, step);
	}

	@Override
	public int insertTHits(THits tHits) {
		return tHitsDao.insert(tHits);
	}

	@Override
	public int updateTHits(THits tHits) {
		return tHitsDao.update(tHits);
	}

	@Override
	public int deleteTHits(int id) {
		return tHitsDao.delete(id);
	}

}
