package com.line.young.seminar.ctrl;

import java.sql.SQLException;
import java.util.logging.Logger;


import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.line.young.seminar.entity.PersonalInfo;
import com.line.young.seminar.repo.PersonalInfoRepository;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.SQLException;
import java.util.logging.Logger;

import javax.sql.DataSource;

@Controller
@RequestMapping("/personal_information")
public class PersonalInfoController {
    
    private final Logger logger = Logger.getLogger(this.getClass().getName());
    
//    @Value("${spring.datasource.url}")
//    private String dbUrl;
//
//    @Autowired
//    private DataSource dataSource;
   
    private PersonalInfoRepository personalInfoRepository;
    
    @Autowired
    public PersonalInfoController(PersonalInfoRepository repo) {
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
    public String newTask(ModelMap model, @ModelAttribute("newPersonalInfo") @Valid PersonalInfo personalInfo,
                          BindingResult result) {
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
