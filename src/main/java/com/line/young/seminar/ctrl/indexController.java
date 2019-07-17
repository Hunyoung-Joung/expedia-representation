/**
 * Copyright@ LINE 
 * by jounghunyoung@gmail.com
 * 
 */
package com.line.young.seminar.ctrl;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    private QuestionInfoService questionInfoService;
    
    @Autowired
    private PersonalInfoService personalInfoService;
    
    @GetMapping(value = {""})
    public String index(@RequestParam("id") String id, @RequestParam("password") String password, Model model) {
        
        logger.info(id+" : "+password);
        
        return "index";
    }
}
