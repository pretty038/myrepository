package com.apcompany.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.apcompany.user.pojo.SuperData;
import com.apcompany.user.pojo.TAnswers;
import com.apcompany.user.pojo.TChoises;
import com.apcompany.user.pojo.TLabels;
import com.apcompany.user.pojo.TLabelsRel;
import com.apcompany.user.pojo.TQuestions;
import com.apcompany.user.service.DataService;

@Controller
@RequestMapping("/data")
public class DataController {

	@Autowired
	private DataService dataService;

	@RequestMapping("/preinsert")
	public String test(Model model) {
		// List<TQuestions> datalist = dataService.getDataList(0);
		// model.addAttribute("datalist", datalist);
		return "test";
	}

	@RequestMapping("/showquestion")
	public String testshow(Model model) {
		return "show-question";
	}

	@RequestMapping("/insert")
	public String insert(SuperData superData, Model model) {
		System.out.println(superData);
		if (superData != null) {
			boolean out = dataService.addData(superData.gettQuestions(),
					superData.gettChoises(), superData.gettAnswers(),
					superData.gettLabelsQuestionRel());
			if (out) {
				model.addAttribute("message", "true");
			} else {
				model.addAttribute("message", "insert failed !!");
			}

		} else {
			model.addAttribute("message",
					"super data is null!!!, please check the parameter !!!");
		}
		return "outcome";
	}

	@RequestMapping("/insertLabel")
	@ResponseBody
	public String insertLabel(TLabels tLabels, Model model) {
		boolean outcome = dataService.addLabel(tLabels);
		if (outcome) {
			model.addAttribute("message", "true");
		} else {
			model.addAttribute("message", "insert failed !!");
		}
		return "outcome";
	}

	@RequestMapping(value = "/select", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String selectAll(Model model, Integer curPage, Integer pageSize) {
		int totalcount = dataService.getDataCount();
		curPage = curPage == null ? 0 : curPage;
		pageSize = pageSize == null ? 20 : pageSize;
		List<TQuestions> datalist = dataService.getDataList(0, totalcount,
				curPage, pageSize);
		model.addAttribute("datalist", datalist);
		String jsonText = JSON.toJSONString(datalist, true);
		return jsonText;
	}

	@RequestMapping("/updateQuesion")
	@ResponseBody
	public String updateQuesion(TQuestions tQuestions) {
		int count = dataService.updateQuestion(tQuestions);
		return count > 0 ? "true" : "false";
	}

	@RequestMapping("/updateAnswers")
	@ResponseBody
	public String updateAnswers(TAnswers tAnswers) {
		int count = dataService.updateAnswers(tAnswers);
		return count > 0 ? "true" : "false";
	}

	@RequestMapping("/updateCimei'yihoise")
	@ResponseBody
	public String updateChoise(TChoises tChoises) {
		int count = dataService.updateChiose(tChoises);
		return count > 0 ? "true" : "false";
	}

	@RequestMapping("/delLabel")
	@ResponseBody
	public String delLabel(int id) {
		int count = dataService.delLabel(id);
		return count > 0 ? "true" : "false";
	}

	@RequestMapping("/updateLabel")
	@ResponseBody
	public String updateLabe(TLabels tLabels) {
		int count = dataService.updateLabel(tLabels);
		return count > 0 ? "true" : "false";
	}

	@RequestMapping(value = "/labelByName", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String labelByName(Integer curPage, Integer pageSize, String names) {
		curPage = curPage == null ? 0 : curPage;
		pageSize = pageSize == null ? 20 : pageSize;
		int totalcount = dataService.countSelectByName(names);
		List<TLabels> lables = dataService.selectByName(totalcount, curPage,
				pageSize, names);
		String jsonText = JSON.toJSONString(lables, true);
		return jsonText;
	}

	@RequestMapping("/labelAll")
	@ResponseBody
	public String labelAll(Integer curPage, Integer pageSize) {
		curPage = curPage == null ? 0 : curPage;
		pageSize = pageSize == null ? 200 : pageSize;
		int totalcount = dataService.countLabels();
		List<TLabels> lables = dataService.selectAllLabels(totalcount, curPage,
				pageSize);
		String jsonText = JSON.toJSONString(lables, true);
		return jsonText;
	}

	@RequestMapping(value = "/labelRelAll", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String labelAllHao() {
		List<TLabelsRel> lables = dataService.selectAllLabelsRel();
		String jsonText = JSON.toJSONString(lables, true);
		return jsonText;
	}

	@RequestMapping("/delLabelRel")
	@ResponseBody
	public String delLabelRel(int id) {
		int count = dataService.delTLabelsRel(id);
		return count > 0 ? "true" : "false";
	}

	@RequestMapping("/labelUpdateOrInsert")
	@ResponseBody
	public String labelUpdateOrInsert(TLabelsRel tlabelsrel, Model model) {
		int count = dataService.insertOrUpateTLabelsRel(tlabelsrel);
		return String.valueOf(count);
	}

	@RequestMapping("/labelManager")
	public String labelManager(Model model) {
		return "label-manager";
	}

	@RequestMapping("/labelTest")
	public String labelTest(Model model) {

		model.addAttribute("message", "true");

		return "labeltest";
	}

}
