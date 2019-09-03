/**
 * Copyright@ LINE 
 * by jounghunyoung@gmail.com
 * 
 */
package com.expedia.young.demo.ctrl;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.expedia.young.demo.entity.PersonalInfo;
import com.expedia.young.demo.entity.QuestionInfo;
import com.expedia.young.demo.service.PersonalInfoService;
import com.expedia.young.demo.service.QuestionInfoService;

/**
 * 
 * Question controller
 * 
 * @author jounghunyoung@gmail.com
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
    
    /**
     * Get index
     * 
     * @param encryptId
     * @param model
     * @return
     */
    @SuppressWarnings("finally")
	@GetMapping
    public String init(@RequestParam("encryptId") String encryptId, Model model) {
        this.encryptId_ = encryptId;
        PersonalInfo personalInfo = new PersonalInfo();

        if (null == encryptId) {
            try {
                throw new Exception(); 
            } catch (Exception e){
                 logger.log(Level.ALL, "ERROR", e.getCause()); 
            } finally{
                return "error";
            } 
        } else {
            
            if (personalInfoService.findByEncryptId(encryptId).isPresent()) {
                personalInfo = personalInfoService.findByEncryptId(encryptId).get();
            } else {
            	return "error";
            }
            model.addAttribute("encrypt_id", encryptId);
            model.addAttribute("displayName", personalInfo.getDisplay_name());
            model.addAttribute("questionInfo", new QuestionInfo());
            model.addAttribute("questionInfos", this.findByEncryptId(encryptId, new ArrayList<QuestionInfo>()));
            
            return "seminar";
        }
    }

    /**
     * Find by user id
     * 
     * @param encryptId
     * @param questionInfos
     * @return
     */
    @RequestMapping(value="{encryptId}", method=RequestMethod.GET)
    public List<QuestionInfo> findByEncryptId(@PathVariable String encryptId, @ModelAttribute List<QuestionInfo> questionInfos) {
        if (!questionInfoService.findByEncryptId(encryptId).isEmpty()) {
            questionInfos = questionInfoService.findByEncryptId(encryptId);
        } else {
            questionInfos = new ArrayList<QuestionInfo>();
        }
        return questionInfos;
    }
    
    /**
     * Post save
     * 
     * @param model
     * @param questionInfo
     * @return
     */
    @PostMapping
    public String addtquestionInfo(Model model, @ModelAttribute QuestionInfo questionInfo) {
        questionInfo.setEncrypt_id(this.encryptId_);
        questionInfo.setSeminar_id("3"); // TODO
        questionInfo = questionInfoService.saveOfQuestionInfo(questionInfo);
        return "redirect:/seminar?encryptId="+this.encryptId_; // PRG pattern
    }
    
    /**
     * Delete by question no
     * 
     * @param model
     * @param q_no
     * @param questionInfo
     * @return
     * @throws Exception
     */
    @DeleteMapping("{q_no}")
    public String deleteById(Model model, @PathVariable Long q_no, @ModelAttribute QuestionInfo questionInfo) throws Exception {
    	questionInfoService.deleteById(q_no);
    	return init(encryptId_, model);
    }
}