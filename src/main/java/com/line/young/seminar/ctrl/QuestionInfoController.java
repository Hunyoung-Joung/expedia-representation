package com.line.young.seminar.ctrl;

import java.util.logging.Logger;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.line.young.seminar.entity.QuestionInfo;
import com.line.young.seminar.service.QuestionInfoService;



/**
 * 
 * @author JP22601
 *
 */
@Controller
@RequestMapping("/questionInfo")
public class QuestionInfoController {
    
    private final Logger logger = Logger.getLogger(this.getClass().getName());
   
    @Autowired
    private QuestionInfoService questionInfoService;

    @RequestMapping(value="/{userId, seminarId}", method=RequestMethod.GET)
    public String init(@RequestParam("userId") String userId, @RequestParam("seminarId") String seminarId, Model model) 
            throws Exception {
        logger.info("##### init question information: seminarId?"+seminarId+", userId?"+userId);
        
        if (null == userId || null == seminarId) {
            throw new Exception();
        } else {
            model.addAttribute("questionInfo", new QuestionInfo());
//            QuestionInfo questionlInfo = this.findById(userId, new QuestionInfo());
//            model.addAttribute("questionlInfo", questionlInfo);
        }

        return "question_information";
    }


//    @RequestMapping(value="{userId}", method=RequestMethod.GET)
//    public QuestionInfo findById(@PathVariable String userId, @ModelAttribute questionlInfo questionlInfo) {
//        logger.info("##### find by id: userId? "+userId);
//        if (questionlInfoService.findOne(userId).isPresent()) {
//            questionlInfo = questionlInfoService.findOne(userId).get();
//        } else {
//            questionlInfo = new questionlInfo();
//        }
//        logger.info("##### find questionlInfo? "+questionlInfo.toString());
//        return questionlInfo;
//    }
    

//    @RequestMapping(method=RequestMethod.GET)
//    public String findAll(Model model) {
//        logger.info("##### find all of users");
//  
//        Iterable<questionlInfo> questionlInfos = questionlInfoService.findAll();
//        model.addAttribute("questionlInfos", questionlInfos);
//
//        return "user_list";
//    }
//    
//
//    @RequestMapping(method = RequestMethod.POST)
//    public String addtquestionlInfo(Model model, @ModelAttribute("questionlInfo") @Valid questionlInfo questionlInfo, BindingResult result)  {
//        logger.info("##### add questionl information");
//        questionlInfo = QuestionlInfoService.save(questionlInfo);
//        model.addAttribute("questionlInfo", this.findById(questionlInfo.getUser_id(), new questionlInfo()));
//
//        return index(questionlInfo.getUser_id(), model);
//    }
//    
//    @RequestMapping(method= {RequestMethod.GET, RequestMethod.POST}, value={"/"}, params={"userId"})
//    public String selectquestionlInfo(@PathVariable String userId, Model model) {
//        logger.info("##### find questionl information? userId = "+userId);
//        Optional<questionlInfo> questionlInfo = questionlInfoService.findOne(userId);
//        model.addAttribute("questionlInfo", questionlInfo);
//        
//        return "questionl_information";
//    }
//    
//    @RequestMapping(method=RequestMethod.GET, value={"/api/{userId}"})
//    public String selectquestionlInfo(Model model, @PathVariable String userId) {
//        logger.info("##### find questionl information? id = "+userId);
////        Iterable<questionlInfo> questionlInfos = this.questionlInfoRepository.findAll();
////        model.addAttribute("questionlInfos", questionlInfos);
////        model.addAttribute("questionlInfo", new questionlInfo());
//        
//        return "questionl_information";
//    }
}
