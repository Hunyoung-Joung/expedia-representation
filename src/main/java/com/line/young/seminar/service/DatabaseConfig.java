package com.line.young.seminar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.SQLException;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {
    @Value("${spring.datasource.url}")
    private String dbUrl;

//    @Autowired
//    private DataSource dataSource;

    @Primary
//    @ConfigurationProperties(prefix = "spring.datasource")
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
