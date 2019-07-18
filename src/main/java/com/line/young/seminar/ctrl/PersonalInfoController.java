/**
 * Copyright@ LINE 
 * by jounghunyoung@gmail.com
 * 
 */
package com.line.young.seminar.ctrl;

import java.util.logging.Logger;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.line.young.seminar.entity.PersonalInfo;
import com.line.young.seminar.service.PersonalInfoService;


/**
 * 
 * Personal information controller with service and entity
 * 
 * @author jounghunyoung@gmail.com
 *
 */
@Controller
@RequestMapping("/personalInfo")
public class PersonalInfoController {
    
    private final Logger logger = Logger.getLogger(this.getClass().getName());
   
    @Autowired
    private PersonalInfoService personalInfoService;

    
    /**
     * Initial method
     * 
     * @param userId
     * @param model
     * @return
     */
    @GetMapping
    public String init(@RequestParam("userId") String userId, @RequestParam("encryptId") String encryptId, Model model) {
        PersonalInfo personalInfo = new PersonalInfo();
        if (null == userId) {
            model.addAttribute("personalInfo", personalInfo);
        } else {
            personalInfo = this.findById(userId, new PersonalInfo());
            personalInfo.setUser_id(userId);
            personalInfo.setEncrypt_id(encryptId);
            personalInfoService.save(personalInfo);
            model.addAttribute("personalInfo", personalInfo);
        }

        return "personal_information";
    }

    /**
     * 
     * @param encryptId
     * @param personalInfo
     * @return
     */
    @RequestMapping(value="/{encryptId}", method=RequestMethod.GET)
    public PersonalInfo findById(@PathVariable String encryptId, @ModelAttribute PersonalInfo personalInfo) {
        if (personalInfoService.findOne(encryptId).isPresent()) {
            personalInfo = personalInfoService.findOne(encryptId).get();
        } else {
            personalInfo = new PersonalInfo();
        }
        return personalInfo;
    }
    
    /**
     * 
     * @param model
     * @return
     */
    @RequestMapping(value="/findAll", method=RequestMethod.GET)
    public String findAll(Model model) {
        Iterable<PersonalInfo> personalInfos = personalInfoService.findAll();
        model.addAttribute("personalInfos", personalInfos);

        return "user_list";
    }
    
    /**
     * 
     * @param model
     * @param personalInfo
     * @param result
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public String addtPersonalInfo(Model model, @ModelAttribute("personalInfo") @Valid PersonalInfo personalInfo, BindingResult result)  {
        logger.info("##### add personal information");
        personalInfo = personalInfoService.save(personalInfo);
        model.addAttribute("personalInfo", this.findById(personalInfo.getEncrypt_id(), new PersonalInfo()));

        return init(personalInfo.getUser_id(), personalInfo.getEncrypt_id(), model);
    }

}
