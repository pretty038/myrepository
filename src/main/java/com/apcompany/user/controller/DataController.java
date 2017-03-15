package com.apcompany.user.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.apcompany.user.pojo.SuperData;
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

	// @RequestMapping(value = "/insert")
	public String test2(HttpServletRequest request) {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		// Enumeration e = multipartRequest.getParameterNames();

		// Map<String, String> mapRequest = multipartRequest.getParameterMap();
		java.util.Enumeration<String> e = multipartRequest.getParameterNames();
		while (e.hasMoreElements()) {
			String paramName = e.nextElement();
			String[] values = multipartRequest.getParameterValues(paramName);
			for (int i = 0; i < values.length; i++) {
				System.out.println("[" + i + "]   " + paramName + "  "
						+ values[i]);
			}
		}
		return "true";
	}

	@RequestMapping(value = "/insert")
	public String insert(SuperData superData, Model model) {
		System.out.println(superData);
		if (superData != null) {
			boolean out = dataService.addData(superData.gettQuestions(),
					superData.gettChoises(), superData.gettAnswers());
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

	@RequestMapping("/select")
	@ResponseBody
	public String selectAll(Model model) {
		List<TQuestions> datalist = dataService.getDataList(0);
		model.addAttribute("datalist", datalist);
		String jsonText = JSON.toJSONString(datalist, true);
		return jsonText;
	}

}
