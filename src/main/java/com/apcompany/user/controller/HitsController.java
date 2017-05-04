package com.apcompany.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.apcompany.user.pojo.THits;
import com.apcompany.user.service.HitsService;

@Controller
@RequestMapping("/hits")
public class HitsController {

	@Autowired
	private HitsService hitsService;

	@RequestMapping("/insertOrUpdateHits")
	@ResponseBody
	public String insertOrUpdateHits(THits tHits) {
		if (tHits.getId() > 0) {
			hitsService.updateTHits(tHits);
		} else {
			hitsService.insertTHits(tHits);
		}
		return "successfully!!";
	}

	@RequestMapping("/deleleHits")
	@ResponseBody
	public String deleleHits(int id) {
		return hitsService.deleteTHits(id) > 0 ? "successfully!!" : "error";
	}
	
	@RequestMapping("/selectHits")
	@ResponseBody
	public String selectHits(@RequestParam("questionId")String question_id,@RequestParam("step")String step) {
		String jsonText="";
		if(question_id!=null&&step!=null&&!"".equals(question_id)&&!"".equals(step)){
			THits tHits =hitsService.getTHits(Integer.valueOf(question_id), Integer.valueOf(step));
			jsonText = JSON.toJSONString(tHits, true);
		}
		return jsonText;
	}

}
