package com.line.young.seminar.ctrl;

import java.sql.SQLException;
import java.util.logging.Logger;


import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
//import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.line.young.seminar.entity.PersonalInfo;
import com.line.young.seminar.repo.PersonalInfoRepository;




@Controller
@RequestMapping("/personal_information")
public class PersonalInfoController {
    
    private final Logger logger = Logger.getLogger(this.getClass().getName());
   
    private PersonalInfoRepository personalInfoRepository;
    
    @Autowired
    public PersonalInfoController(PersonalInfoRepository repo) {
        logger.info("##### Construct");
        this.personalInfoRepository = repo;
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public String showPersonalInfos(Model model) {
        logger.info("##### insert personal information");
        Iterable<PersonalInfo> personalInfos = this.personalInfoRepository.findAll();
        model.addAttribute("PersonalInfos", personalInfos);
        model.addAttribute("newPersonalInfos", new PersonalInfo());
        
        return "personal_information";
    }

    @RequestMapping(method = RequestMethod.POST)
//    public String addtPersonalInfo(ModelMap model, 
//                                @ModelAttribute("newInsertPersonalInfo") 
//                                @Valid PersonalInfo personalInfo,
//                                BindingResult result) {
    public String addtPersonalInfo(@Valid PersonalInfo personalInfo, BindingResult result, Model model)  {
        logger.info("##### insert personal information");
        if (result.hasErrors()) {
            return "personal_information";
        }
        personalInfoRepository.save(personalInfo);
        model.addAttribute("personalInfos", personalInfoRepository.findAll());

//        return showPersonalInfo(model);
        return "personal_information";
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public String deleteTask(Model model, @RequestParam("userId") String id) {
      this.personalInfoRepository.deleteById(id);
      return showPersonalInfos(model);
    }
    
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
