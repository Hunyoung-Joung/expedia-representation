/**
 * Copyright@ LINE 
 * by jounghunyoung@gmail.com
 * 
 */
package com.line.young.seminar.ctrl;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
 * Survey answer controller
 * 
 * @author jounghunyoung@gmail.com
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
    
    @GetMapping
    public String init(@RequestParam("encryptId") String encryptId, Model model) throws Exception {
//        this.userId_ = userId;
        PersonalInfo personalInfo = new PersonalInfo();
        List<SurveyAnswerInfo> surveyAnswerInfos = new ArrayList<SurveyAnswerInfo>();
        if (null == encryptId) {
        	return "error";
        } else {
            if (personalInfoService.findByEncryptId(encryptId).isPresent()) {
                personalInfo = personalInfoService.findByEncryptId(encryptId).get();
                
                logger.info("## init findAllAnswerByIds encryptId? "+encryptId+", seminarId? 4");
                surveyAnswerInfos = this.findByIds(encryptId, "3", surveyAnswerInfos); // TODO
            } else {
            	return "error";
            }
            model.addAttribute("displayName", personalInfo.getDisplay_name());
            model.addAttribute("encryptId", encryptId);
            model.addAttribute("seminarId", "3"); // TODO
            model.addAttribute("surveyAnswerInfos", surveyAnswerInfos);
            logger.info("##### init survey information models? "+model.toString());
        }

        return "survey";
    }


    @RequestMapping(value="{encryptId, seminarId}", method=RequestMethod.GET)
    public List<SurveyAnswerInfo> findByIds(@PathVariable String encryptId, @PathVariable String seminarId, 
            @ModelAttribute List<SurveyAnswerInfo> surveyAnswerInfos) {
        
        surveyAnswerInfos = surveyAnswerInfoService.findAllAnswerByIds(encryptId, seminarId);
        return surveyAnswerInfos;
    }
}

/**
 * 
 * Survey answer rest controller
 * 
 * @author jounghunyoung@gmail.com
 *
 */
@RestController
class SurveyAnswerInfoRestController {
    
    @Autowired
    private SurveyAnswerInfoService surveyAnswerInfoService;

    @PostMapping("/survey/api/add")
    public String addAnswerInfo(Model model, @Validated @RequestBody String surveyAnswerInfoList) 
            throws Exception  {
        String encryptId = "";
        ObjectMapper objectMapper = new ObjectMapper();
        SurveyAnswerInfo[] surveyAnswerInfos = objectMapper.readValue(surveyAnswerInfoList, SurveyAnswerInfo[].class);
        List<SurveyAnswerInfo> list = Arrays.asList(surveyAnswerInfos);  
        encryptId = list.get(0).getEncrypt_id();

        list = (@Valid List<SurveyAnswerInfo>) surveyAnswerInfoService.saveOfSurveyAnswerInfos(list);
        model.addAttribute("surveyAnswerInfos", list);

        return "survey?userId="+encryptId;
    }
    
}
