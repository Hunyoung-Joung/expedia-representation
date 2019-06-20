package com.line.young.seminar;

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
public class IndexController {
    private final Logger logger = Logger.getLogger(this.getClass().getName());
    
    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Autowired
    private DataSource dataSource;

    @GetMapping("/")
    public String index(Model model) {

        logger.info("------------------ index");

        return "index";
    }
    
    @GetMapping("/personal_information")
    public String personalInformation(Model model) {

        logger.info("------------------ personalInformation");

        return "personal_information";
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
    
    @Bean
    public DataSource dataSource() throws SQLException {
      if (dbUrl == null || dbUrl.isEmpty()) {
        return new HikariDataSource();
      } else {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(dbUrl);
        return new HikariDataSource(config);
      }
    }
}
