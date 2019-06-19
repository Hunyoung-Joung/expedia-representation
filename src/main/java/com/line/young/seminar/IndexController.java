package com.line.young.seminar;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.logging.Logger;

@Controller
public class IndexController {
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    @GetMapping("/")
    public String index(Model model) {

        logger.info("------------------ index");

        return "index";
    }
    
    @GetMapping("/personal_nformation")
    public String personalInformation(Model model) {

        logger.info("------------------ personalInformation");

        return "personal_nformation";
    }
    
    @GetMapping("/seminar")
    public String seminar(Model model) {

        logger.info("------------------ seminar");

        return "seminar";
    }
    
    @GetMapping("/private_policy")
    public String privatePolicy(Model model) {

        logger.info("------------------ privatePolicy");

        return "private_policy";
    }
    
    @GetMapping("/survey")
    public String survey(Model model) {

        logger.info("------------------ survey");

        return "survey";
    }
}
