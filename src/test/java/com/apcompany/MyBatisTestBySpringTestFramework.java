package com.apcompany;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.apcompany.user.dao.TAnswersDao;
import com.apcompany.user.dao.TChoisesDao;
import com.apcompany.user.dao.TQuestionsDao;
import com.apcompany.user.pojo.TAnswers;
import com.apcompany.user.pojo.TChoises;
import com.apcompany.user.pojo.TQuestions;

@RunWith(SpringJUnit4ClassRunner.class)
//配置了@ContextConfiguration注解并使用该注解的locations属性指明spring和配置文件之后，
@ContextConfiguration(locations = {"classpath:spring.xml", "classpath:spring-mybatis.xml" })
public class MyBatisTestBySpringTestFramework {
	
	//注入userService
    @Autowired
    private TQuestionsDao questionsDao;
    
    @Autowired
    private TAnswersDao answersDao;
    
    
    @Autowired
    private TChoisesDao choiseDao;
    
    @Test
    public void insertTest(){
    	TQuestions tQuestions=new TQuestions();
    	tQuestions.setQuestion("2+1=? ∫xdx");
    	System.out.println(tQuestions.getId());
    	questionsDao.insert(tQuestions);
    	System.out.println(tQuestions.getId());
    }
    
//     @Test
//     public void testSearch(){
//    	 List <TQuestions> list=questionsDao.searchAll(0);
//    	 for(TQuestions tQuestions:list){
//    		 System.out.println(tQuestions);
//    	 }
//     }
    @Test
    public void insertTest1(){
    	TAnswers tAnswers=new TAnswers();
    	tAnswers.setQuestionid(2);
    	tAnswers.setAnswer("∫xdx");
    	System.out.println(tAnswers.getId());
    	int k=answersDao.insert(tAnswers);
    	System.out.println(k);
    	System.out.println(tAnswers.getId());
    }
    
    
    @Test
    public void insert2Test(){
    	TChoises tChoises=new TChoises();
    	tChoises.setQuestionid(1);
    	tChoises.setChoise("∫xdx");
    	
    	System.out.println(tChoises.getId());
    	choiseDao.insert(tChoises);
    	System.out.println(tChoises.getId());
    }
   

}
