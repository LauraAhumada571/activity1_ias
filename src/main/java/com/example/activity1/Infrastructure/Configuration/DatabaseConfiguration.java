package com.example.activity1.Infrastructure.Configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfiguration {

    @Bean
    public DataSource dataSource (){
        HikariConfig hikariConf = new HikariConfig();
        hikariConf.setJdbcUrl("jdbc:postgresql://localhost:5432/training_db");
        hikariConf.setUsername("postgres_user");
        hikariConf.setPassword("postgres_password");

        return new HikariDataSource(hikariConf);
    }
}
