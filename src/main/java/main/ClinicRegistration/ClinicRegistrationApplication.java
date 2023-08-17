package main.ClinicRegistration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ClinicRegistrationApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClinicRegistrationApplication.class, args);
	}

}
