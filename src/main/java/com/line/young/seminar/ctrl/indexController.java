/**
 * Copyright@ LINE 
 * by jounghunyoung@gmail.com
 * 
 */
package com.line.young.seminar.ctrl;

import java.util.Optional;
import java.util.logging.Logger;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.line.young.seminar.entity.PersonalInfo;
import com.line.young.seminar.entity.UserInfo;
import com.line.young.seminar.repo.QuestionInfoRepository;
import com.line.young.seminar.repo.UsersRepository;
import com.line.young.seminar.service.PersonalInfoService;
import com.line.young.seminar.service.QuestionInfoService;

/**
 * 
 * Index controller without service
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
    private PersonalInfoService personalInfoService;
    
    @GetMapping
    public String init(Model model) {

        model.addAttribute("personalInfo", new PersonalInfo());

        return "index";
    }
    
    @PostMapping(value = {"/auth"})
    public String index(Model model, @ModelAttribute("userInfo") @Valid UserInfo userInfo, BindingResult result) throws Exception {
        
        Optional<UserInfo> userInfos = usersRepository.findById(userInfo.getId());
        String id = userInfos.get().getId();
        String password = userInfos.get().getPassword();
        
        if (null == id) {
//            throw new Exception();
            return "index";
        } else {
            logger.info(id+" : "+userInfo.getId()+" -- "+password+" : "+userInfo.getPassword());

            if ((id == "admin") && (password == userInfo.getPassword())) {
                return "admin";
            } else {
                return "index";
            }
        }
    }
}
