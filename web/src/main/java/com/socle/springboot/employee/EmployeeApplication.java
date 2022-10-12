package com.socle.springboot.employee;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@Configuration
@EntityScan("models")
@EnableJpaRepositories("repository")
@ComponentScan(basePackages = {"com.socle.springboot.employee", "com.socle.springboot.security", "com.socle.springboot.controller", "services", "com.socle.springboot.testUnitaire"})
@PropertySource(value = {"classpath:config.properties", "file:config.properties"}, ignoreResourceNotFound = true)
public class EmployeeApplication {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    public static void main(String[] args) {
        SpringApplication.run(EmployeeApplication.class, args);
    }
}
