package main.ClinicRegistration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(basePackages = {"manager", "controller", "directories", "main.ClinicRegistration", "model", "proxies", "repository", "payment"})
@EnableFeignClients(basePackages = {"payment", "main.ClinicRegistration"})
@EnableJpaRepositories(basePackages = "repository")
@EntityScan(basePackages = {"model"})
public class ProjectConfig {

}
