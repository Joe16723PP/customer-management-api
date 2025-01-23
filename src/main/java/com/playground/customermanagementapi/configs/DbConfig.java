package com.playground.customermanagementapi.configs;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories
public class DbConfig {
    @Bean
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }
}
