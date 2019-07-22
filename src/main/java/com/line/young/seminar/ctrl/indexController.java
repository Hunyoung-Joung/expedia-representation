/**
 * Copyright@ LINE 
 * by jounghunyoung@gmail.com
 * 
 */
package com.line.young.seminar.ctrl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.line.young.seminar.entity.AdminQuestionInfo;
import com.line.young.seminar.entity.PersonalInfo;
import com.line.young.seminar.entity.QuestionInfo;
import com.line.young.seminar.entity.UserInfo;
import com.line.young.seminar.repo.QuestionInfoRepository;
import com.line.young.seminar.repo.UsersRepository;
import com.line.young.seminar.service.PersonalInfoService;
import com.line.young.seminar.service.QuestionInfoService;
import com.line.young.seminar.service.SurveyAnswerInfoService;
import com.line.young.seminar.service.SurveyInfoService;

/**
 * 
 * Index controller
 * 
 * @author jounghunyoung@gmail.com
 *
 */
@Controller
@RequestMapping(value = {""})
public class indexController {
    
    private final Logger logger = Logger.getLogger(this.getClass().getName());
    
    @Autowired
    private UsersRepository usersRepository;
    
    @Autowired
    private QuestionInfoService questionInfoService;
    
    @Autowired
    private SurveyAnswerInfoService surveyAnswerInfoService;
    
    @Autowired
    private PersonalInfoService personalInfoService;
    
    private UserInfo userInfo_ = new UserInfo();
    
    @GetMapping
    public String init(Model model) {

        model.addAttribute("personalInfo", new PersonalInfo());

        return "index";
    }
    
    /**
     * Show users, user's questions, and survey.
     * 
     * @param model
     * @param userInfo
     * @return
     */
    @PostMapping(value = {"/auth"})
    public String index(Model model, @ModelAttribute("userInfo") @Valid UserInfo userInfo) {
        
        Optional<UserInfo> obj = usersRepository.findById(userInfo.getId());
        String id = obj.get().getId();
        String password = obj.get().getPassword();
        
        if (null == id) {
            try{
                throw new Exception(); 
            }catch (Exception e){
                 e.printStackTrace(); 
            }finally{
                return "error";
            } 
        } else {
            if (!password.equals(userInfo.getPassword())) {
                return "error";
            }
            userInfo_ = userInfo;
        	List<AdminQuestionInfo> adminQuestionInfos = new ArrayList<AdminQuestionInfo>();
            if ((id.equals("admin")) && (password.equals(userInfo.getPassword()))) {
            	List<QuestionInfo> questionInfos = (List<QuestionInfo>) questionInfoService.findAllOfQuestionInfo();
            	for (QuestionInfo questionInfo: questionInfos) {
            		Optional<PersonalInfo> personalInfo = personalInfoService.findByEncryptId(questionInfo.getEncrypt_id());
            		AdminQuestionInfo adminQuestionInfo = new AdminQuestionInfo();
            		adminQuestionInfo.setPersonalInfo(personalInfo.get());
            		adminQuestionInfo.setQuestionInfo(questionInfo);
            		adminQuestionInfos.add(adminQuestionInfo);
            	}
            	model.addAttribute("displayName", "Admin");
            	model.addAttribute("adminQuestionInfos", adminQuestionInfos);
                model.addAttribute("personalInfos", personalInfoService.findAll());
                model.addAttribute("surveyAnswerInfos", surveyAnswerInfoService.findAllOfSurveyAnswerInfo());
                return "admin";
            } else {
                return "index";
            }
        }
    }
    
    /**
     * Update user question will answer or not.
     * 
     * @param model
     * @param q_no
     * @param questionInfo
     * @return
     */
    @PutMapping("{q_no}")
    public String update(Model model, @PathVariable Long q_no, @ModelAttribute QuestionInfo questionInfo) {
        boolean is_selected = questionInfo.isIs_selected();
        questionInfo = questionInfoService.findById(q_no).get();
        questionInfo.setIs_selected(is_selected);
        questionInfoService.saveOfQuestionInfo(questionInfo);
        model.addAttribute("userInfo", userInfo_);
        return index(model, userInfo_);
    }
}
