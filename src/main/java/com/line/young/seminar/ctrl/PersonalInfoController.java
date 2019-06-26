package com.line.young.seminar.ctrl;

import java.sql.SQLException;
import java.util.logging.Logger;


import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
    public String showAllPersonalInfos(ModelMap model) {
        Iterable<PersonalInfo> personalInfos = this.personalInfoRepository.findAll();
        model.addAttribute("PersonalInfos", personalInfos);
        model.addAttribute("newPersonalInfos", new PersonalInfo());
        
        return "personal_information";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String insertPersonalInfo(ModelMap model, 
                                @ModelAttribute("newInsertPersonalInfo") 
                                @Valid PersonalInfo personalInfo,
                                BindingResult result) {
        logger.info("##### insert personal information");
        if (!result.hasErrors()) {
            this.personalInfoRepository.save(personalInfo);
        }
        return showAllPersonalInfos(model);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public String deleteTask(ModelMap model, @RequestParam("userId") String id) {
      this.personalInfoRepository.deleteById(id);
      return showAllPersonalInfos(model);
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
