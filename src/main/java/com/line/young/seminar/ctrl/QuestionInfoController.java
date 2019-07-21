package com.line.young.seminar.ctrl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.line.young.seminar.entity.PersonalInfo;
import com.line.young.seminar.entity.QuestionInfo;
import com.line.young.seminar.entity.SurveyAnswerInfo;
import com.line.young.seminar.service.PersonalInfoService;
import com.line.young.seminar.service.QuestionInfoService;
import com.line.young.seminar.service.SurveyAnswerInfoService;



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
    
    private String encryptId_ = "";
    
    @GetMapping
    public String init(@RequestParam("encryptId") String encryptId, Model model) throws Exception {
        this.encryptId_ = encryptId;
        PersonalInfo personalInfo = new PersonalInfo();
        logger.info("##### init question information encryptId? "+encryptId);
        if (null == encryptId) {
            throw new Exception();
        } else {
            if (personalInfoService.findByEncryptId(encryptId).isPresent()) {
                personalInfo = personalInfoService.findByEncryptId(encryptId).get();
            } else {
                throw new Exception(); // TODO
            }
            model.addAttribute("displayName", personalInfo.getDisplay_name());
            model.addAttribute("questionInfo", new QuestionInfo());
            model.addAttribute("questionInfos", this.findByEncryptId(encryptId, new ArrayList<QuestionInfo>()));
            logger.info("##### init question information models? "+model.toString());
        }

        return "seminar";
    }


    @RequestMapping(value="{encryptId}", method=RequestMethod.GET)
    public List<QuestionInfo> findByEncryptId(@PathVariable String encryptId, @ModelAttribute List<QuestionInfo> questionInfos) {
        if (!questionInfoService.findByEncryptId(encryptId).isEmpty()) {
            questionInfos = questionInfoService.findByEncryptId(encryptId);
            
            logger.info("##### find by encryptId? "+questionInfos.size());
        } else {
            questionInfos = new ArrayList<QuestionInfo>();
        }
        return questionInfos;
    }
    

    @PostMapping("/all_questions")
    public String findAll(@RequestParam("password") String password, Model model) {
        logger.info("##### find all of users");
  
        Iterable<QuestionInfo> questionInfos = questionInfoService.findAllOfQuestionInfo();
        model.addAttribute("questionInfos", questionInfos);

        return "all_questions";
    }
    

    @RequestMapping(method = RequestMethod.POST)
    public String addtquestionInfo(Model model, @Valid QuestionInfo questionInfo) throws Exception  {
        logger.info("##### add question information");
        questionInfo.setEncrypt_id(encryptId_);
        questionInfo.setSeminar_id("3"); // TODO
        questionInfo = questionInfoService.saveOfQuestionInfo(questionInfo);

        return init(questionInfo.getEncrypt_id(), model);
    }
    
    @DeleteMapping("{q_no}")
    public String deleteById(Model model, @PathVariable Long q_no, @ModelAttribute QuestionInfo questionInfo) throws Exception {
    	questionInfoService.deleteById(q_no);
    	return init(questionInfo.getEncrypt_id(), model);
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
