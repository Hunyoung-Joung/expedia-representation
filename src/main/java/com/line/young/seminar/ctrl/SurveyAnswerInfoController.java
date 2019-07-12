package com.line.young.seminar.ctrl;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.line.young.seminar.entity.PersonalInfo;
import com.line.young.seminar.entity.SurveyAnswerInfo;
import com.line.young.seminar.service.PersonalInfoService;
import com.line.young.seminar.service.SurveyAnswerInfoService;




/**
 * 
 * @author JP22601
 *
 */
@Controller
@RequestMapping("/survey")
public class SurveyAnswerInfoController {
    
    private final Logger logger = Logger.getLogger(this.getClass().getName());
   
    @Autowired
    private SurveyAnswerInfoService surveyAnswerInfoService;
    
    @Autowired
    private PersonalInfoService personalInfoService;
    
//    private String userId_ = "";
    
    @GetMapping
    public String init(@RequestParam("userId") String userId, Model model) throws Exception {
//        this.userId_ = userId;
        PersonalInfo personalInfo = new PersonalInfo();
        List<SurveyAnswerInfo> surveyAnswerInfos = new ArrayList<SurveyAnswerInfo>();
        if (null == userId) {
            throw new Exception();
        } else {
            if (personalInfoService.findOne(userId).isPresent()) {
                personalInfo = personalInfoService.findOne(userId).get();
                
                logger.info("## init findAllAnswerByIds userId? "+userId+", seminarId? 4");
                surveyAnswerInfos = this.findByIds(userId, "3", surveyAnswerInfos); // TODO
//                surveyAnswerInfo.setUser_id(userId);
//                surveyAnswerInfo.setSeminar_id("3");// TODO
            } else {
                throw new Exception();
            }
            model.addAttribute("displayName", personalInfo.getDisplay_name());
            model.addAttribute("userId", userId);
            model.addAttribute("seminarId", "3"); // TODO
            model.addAttribute("surveyAnswerInfos", surveyAnswerInfos);
            logger.info("##### init survey information models? "+model.toString());
        }

        return "survey";
    }


    @RequestMapping(value="{userId, seminarId}", method=RequestMethod.GET)
    public List<SurveyAnswerInfo> findByIds(@PathVariable String userId, @PathVariable String seminarId, 
            @ModelAttribute List<SurveyAnswerInfo> surveyAnswerInfos) {
        
        surveyAnswerInfos = surveyAnswerInfoService.findAllAnswerByIds(userId, seminarId);
        return surveyAnswerInfos;
    }
    

//    @RequestMapping(method=RequestMethod.GET)
//    public String findAll(Model model) {
//        logger.info("##### find all of users");
//  
//        Iterable<questionInfo> questionInfos = questionInfoService.findAll();
//        model.addAttribute("questionInfos", questionInfos);
//
//        return "user_list";
//    }
    

//    @RequestMapping(method = RequestMethod.POST)
//    @PostMapping(value = "/ids") 
//    public String addtquestionInfo(Model model, @Valid String userId, @Valid List<SurveyAnswerInfo> surveyAnswerInfos) throws Exception  {
//        logger.info("##### add surveyAnswerInfo information");
//        
//        surveyAnswerInfo.setUser_id(this.userId_);
//        surveyAnswerInfo.setSeminar_id("4");
//        surveyAnswerInfos = (@Valid List<SurveyAnswerInfo>) surveyAnswerInfoService.saveOfSurveyAnswerInfos(surveyAnswerInfos);
////        model.addAttribute("questionInfos", this.findByUserId(questionInfo.getUser_id(), new ArrayList<QuestionInfo>()));
//
//        return init(userId, model);
//    }
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

@RestController
class SurveyAnswerInfoRestController {
    private final Logger logger = Logger.getLogger(this.getClass().getName());
    @Autowired
    private SurveyAnswerInfoService surveyAnswerInfoService;
    private SurveyAnswerInfoController surveyAnswerInfoController = new SurveyAnswerInfoController();
    @PostMapping("/survey/api/add")
    public String addAnswerInfo(Model model, @Validated @RequestBody String surveyAnswerInfoList) 
            throws Exception  {
        String userId = "";
        ObjectMapper objectMapper = new ObjectMapper();
        SurveyAnswerInfo[] surveyAnswerInfos = objectMapper.readValue(surveyAnswerInfoList, SurveyAnswerInfo[].class);
        List<SurveyAnswerInfo> list = Arrays.asList(surveyAnswerInfos);  
        userId = list.get(0).getUser_id();

        for (SurveyAnswerInfo surveyAnswerInfo: list) {
            logger.info("##### add surveyAnswerInfo information userId?"+userId+", getSurvey_answer? "+surveyAnswerInfo.getSurvey_answer());
        }

        list = (@Valid List<SurveyAnswerInfo>) surveyAnswerInfoService.saveOfSurveyAnswerInfos(list);
        model.addAttribute("surveyAnswerInfos", list);

        return "survey?userId="+userId;
    }
    
}
