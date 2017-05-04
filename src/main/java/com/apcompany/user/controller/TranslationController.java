package com.apcompany.user.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.apcompany.user.pojo.TKeyWords;
import com.apcompany.user.pojo.TTranslations;
import com.apcompany.user.service.TransLateService;

@Controller
@RequestMapping("/translate")
public class TranslationController {
	@Autowired
	private TransLateService transLateService;
	
	@RequestMapping("/insertOrUpdateTranslations")
	@ResponseBody
	public String insertOrUpdateTranslations(TTranslations tTranslations){
		if(tTranslations.getId()>0){
			transLateService.updateTranslations(tTranslations);
		}else{
			transLateService.insertTranslations(tTranslations);
		}
		return "successfully!!";
	}
	
	@RequestMapping("/insertOrUpdateKeyWords")
	@ResponseBody
	public String insertOrUpdateKeyWords(TKeyWords tKeyWords){
		if(tKeyWords.getId()>0){
			transLateService.updateKeyWords(tKeyWords);
			ArrayList<String> list=null;
		}else{
			transLateService.insertKeyWords(tKeyWords);
		}
		return "successfully!!";
	}
	
	@RequestMapping("/deleleTranslations")
	@ResponseBody
	public String deleleTranslations(int id){
		return transLateService.deleteTranslations(id)>0?"successfully!!":"error";
	}
	
	@RequestMapping("/deleleKeyWords")
	@ResponseBody
	public String deleleKeyWords(int id){
		return transLateService.deleteKeyWords(id)>0?"successfully!!":"error";
	}
	
	@RequestMapping("/selectKeyWords")
	@ResponseBody
	public String selectKeyWords(int id){
		TKeyWords tKeyWords=transLateService.getKeyWords(id);
		String jsonText = JSON.toJSONString(tKeyWords, true);
		return jsonText;
	}
	
	@RequestMapping("/selectTranslationsByquestionId")
	@ResponseBody
	public String selectTranslationsByquestionId(int questionId){
		TTranslations tTranslations=transLateService.getTranslationsByQuestionId(questionId);
		String jsonText = JSON.toJSONString(tTranslations, true);
		return jsonText;
	}
	
	@RequestMapping("/selectTranslationsById")
	@ResponseBody
	public String selectTranslationsById(int id){
		TTranslations tTranslations=transLateService.getTranslations(id);
		String jsonText = JSON.toJSONString(tTranslations, true);
		return jsonText;
	} 
	
}
