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
    public String init(@RequestParam("encryptId") String encryptId, Model model) {
        this.encryptId_ = encryptId;
        PersonalInfo personalInfo = new PersonalInfo();

        if (null == encryptId) {
            try{
                throw new Exception(); 
            }catch (Exception e){
                 e.printStackTrace(); 
            }finally{
                return "error";
            } 
        } else {
            logger.info("#############"+personalInfoService.findByEncryptId(encryptId).isPresent());
            if (personalInfoService.findByEncryptId(encryptId).isPresent()) {
                personalInfo = personalInfoService.findByEncryptId(encryptId).get();
            } else {
            	return "error";
            }
            model.addAttribute("displayName", personalInfo.getDisplay_name());
            model.addAttribute("questionInfo", new QuestionInfo());
            model.addAttribute("questionInfos", this.findByEncryptId(encryptId, new ArrayList<QuestionInfo>()));
            
            return "seminar";
        }
    }


    @RequestMapping(value="{encryptId}", method=RequestMethod.GET)
    public List<QuestionInfo> findByEncryptId(@PathVariable String encryptId, @ModelAttribute List<QuestionInfo> questionInfos) {
        if (!questionInfoService.findByEncryptId(encryptId).isEmpty()) {
            questionInfos = questionInfoService.findByEncryptId(encryptId);
        } else {
            questionInfos = new ArrayList<QuestionInfo>();
        }
        return questionInfos;
    }
    

    @PostMapping("new")
    public String addtquestionInfo(Model model, @Valid QuestionInfo questionInfo) {
    	if (!questionInfo.getEncrypt_id().isEmpty()) {
    		return init(questionInfo.getEncrypt_id(), model);
    	}
        questionInfo.setEncrypt_id(encryptId_);
        questionInfo.setSeminar_id("3"); // TODO
        questionInfo = questionInfoService.saveOfQuestionInfo(questionInfo);
        return init(questionInfo.getEncrypt_id(), model);
    }
    
    @DeleteMapping("{q_no}")
    public String deleteById(Model model, @PathVariable Long q_no, @ModelAttribute QuestionInfo questionInfo) throws Exception {
    	questionInfoService.deleteById(q_no);
    	return init(encryptId_, model);
    }
}
