package com.line.young.seminar.ctrl;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.line.young.seminar.entity.PersonalInfo;
import com.line.young.seminar.entity.QuestionInfo;
import com.line.young.seminar.service.PersonalInfoService;
import com.line.young.seminar.service.QuestionInfoService;



/**
 * 
 * @author JP22601
 *
 */
@Controller
@RequestMapping("/seminar")
public class QuestionInfoController {
    
    private final Logger logger = Logger.getLogger(this.getClass().getName());
   
    @Autowired
    private QuestionInfoService questionInfoService;
    
    @Autowired
    private PersonalInfoService personalInfoService;
    
    private String userId_ = "";
    
    @GetMapping
    public String init(@RequestParam("userId") String userId, Model model) throws Exception {
        this.userId_ = userId;
        PersonalInfo personalInfo = new PersonalInfo();
//        List<QuestionInfo> personalInfos = new ArrayList<QuestionInfo>();
        if (null == userId) {
            throw new Exception();
        } else {
            if (personalInfoService.findOne(userId).isPresent()) {
                personalInfo = personalInfoService.findOne(userId).get();
//                personalInfos = questionInfoService.findAllUserQuestion(userId);
            } else {
                throw new Exception();
            }
            model.addAttribute("displayName", personalInfo.getDisplay_name());
            model.addAttribute("questionInfo", new QuestionInfo());
            model.addAttribute("questionInfos", this.findByUserId(userId, new ArrayList<QuestionInfo>()));
            logger.info("##### init question information models? "+model.toString());
        }

        return "seminar";
    }


    @RequestMapping(value="{userId}", method=RequestMethod.GET)
    public List<QuestionInfo> findByUserId(@PathVariable String userId, @ModelAttribute List<QuestionInfo> questionInfos) {
        if (!questionInfoService.findByUserId(userId).isEmpty()) {
            questionInfos = questionInfoService.findByUserId(userId);
            
            logger.info("##### findByUserId? "+questionInfos.size());
        } else {
            questionInfos = new ArrayList<QuestionInfo>();
        }
        return questionInfos;
    }
    

    @RequestMapping(method=RequestMethod.POST, value={"/all_questions"}, params={"userId"})
    public String findAll(@RequestParam("password") String password, Model model) {
        logger.info("##### find all of users");
  
        Iterable<QuestionInfo> questionInfos = questionInfoService.findAllOfQuestionInfo();
        model.addAttribute("questionInfos", questionInfos);

        return "all_questions";
    }
    

    @RequestMapping(method = RequestMethod.POST)
    public String addtquestionInfo(Model model, @Valid QuestionInfo questionInfo) throws Exception  {
        logger.info("##### add question information");
        questionInfo.setUser_id(this.userId_);
        questionInfo.setSeminar_id("3");
        questionInfo = questionInfoService.saveOfQuestionInfo(questionInfo);
//        model.addAttribute("questionInfos", this.findByUserId(questionInfo.getUser_id(), new ArrayList<QuestionInfo>()));

        return init(questionInfo.getUser_id(), model);
    }
//    
//    @RequestMapping(method= {RequestMethod.GET, RequestMethod.POST}, value={"/"}, params={"userId"})
//    public String selectquestionInfo(@PathVariable String userId, Model model) {
//        logger.info("##### find question information? userId = "+userId);
//        Optional<questionInfo> questionInfo = questionInfoService.findOne(userId);
//        model.addAttribute("questionInfo", questionInfo);
//        
//        return "question_information";
//    }
//    
//    @RequestMapping(method=RequestMethod.GET, value={"/api/{userId}"})
//    public String selectquestionInfo(Model model, @PathVariable String userId) {
//        logger.info("##### find question information? id = "+userId);
////        Iterable<questionInfo> questionInfos = this.questionInfoRepository.findAll();
////        model.addAttribute("questionInfos", questionInfos);
////        model.addAttribute("questionInfo", new questionInfo());
//        
//        return "question_information";
//    }
}
