package main.ClinicRegistration;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"manager", "controller", "directories", "main.ClinicRegistration", "model", "proxies", "payment"})
@EnableFeignClients(basePackages = {"payment", "main.ClinicRegistration"})
public class ProjectConfig {

}
