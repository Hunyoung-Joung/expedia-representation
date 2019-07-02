package com.line.young.seminar.ctrl;

import java.sql.SQLException;
import java.util.Optional;
import java.util.logging.Logger;


import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
//import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.line.young.seminar.entity.PersonalInfo;
import com.line.young.seminar.repo.PersonalInfoRepository;
import com.line.young.seminar.service.PersonalInfoService;



@Controller
@RequestMapping("/personalInfo")
public class PersonalInfoController {
    
    private final Logger logger = Logger.getLogger(this.getClass().getName());
   
    @Autowired
    private PersonalInfoService personalInfoService;
    
    /**
     * 
     * @param userId
     * @param model
     * @return
     */
    @RequestMapping(method=RequestMethod.GET)
    public String index(@RequestParam("userId") String userId, Model model) {
        logger.info("##### init personal information: userId?"+userId);
        
        if (null == userId) {
            model.addAttribute("personalInfo", new PersonalInfo());
        } else {
            PersonalInfo personalInfo = this.findById(userId, new PersonalInfo());
            model.addAttribute("personalInfo", personalInfo);
        }

        return "personal_information";
    }

    /**
     * 
     * @param userId
     * @param personalInfo
     * @return
     */
    @RequestMapping(value="{userId}", method=RequestMethod.GET)
    public PersonalInfo findById(@PathVariable String userId, @ModelAttribute PersonalInfo personalInfo) {
        logger.info("##### find by id: userId? "+userId);
        if (personalInfoService.findOne(userId).isPresent()) {
            personalInfo = personalInfoService.findOne(userId).get();
        } else {
            personalInfo = new PersonalInfo();
        }
        logger.info("##### find personalInfo? "+personalInfo.toString());
        return personalInfo;
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
        model.addAttribute("personalInfo", this.findById(personalInfo.getUser_id(), new PersonalInfo()));

        return index(personalInfo.getUser_id(), model);
    }
    
    @RequestMapping(method= {RequestMethod.GET, RequestMethod.POST}, value={"/"}, params={"userId"})
    public String selectPersonalInfo(@PathVariable String userId, Model model) {
        logger.info("##### find personal information? userId = "+userId);
        Optional<PersonalInfo> personalInfo = personalInfoService.findOne(userId);
        model.addAttribute("personalInfo", personalInfo);
        
        return "personal_information";
    }
    
    @RequestMapping(method=RequestMethod.GET, value={"/api/{userId}"})
    public String selectPersonalInfo(Model model, @PathVariable String userId) {
        logger.info("##### find personal information? id = "+userId);
//        Iterable<PersonalInfo> personalInfos = this.personalInfoRepository.findAll();
//        model.addAttribute("PersonalInfos", personalInfos);
//        model.addAttribute("personalInfo", new PersonalInfo());
        
        return "personal_information";
    }

//    @RequestMapping(method = RequestMethod.DELETE)
//    public String deleteTask(Model model, @RequestParam("userId") String id) {
//      this.personalInfoRepository.deleteById(id);
//      return selectPersonalInfos(model);
//    }
    
//    @Bean
//    public DataSource dataSource() throws SQLException {
//      if (dbUrl == null || dbUrl.isEmpty()) {
//        return new HikariDataSource();
//      } else {
//        HikariConfig config = new HikariConfig();
//        config.setJdbcUrl(dbUrl);
//        return new HikariDataSource(config);
//      }
//    }
}
