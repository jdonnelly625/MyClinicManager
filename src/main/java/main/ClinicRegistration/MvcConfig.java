package main.ClinicRegistration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("forward:/login.html");
        registry.addViewController("/dashboard").setViewName("forward:/dashboard.html");
        registry.addViewController("/edit-appointment").setViewName("forward:/edit-appointment.html");
        registry.addViewController("/make-appointment").setViewName("forward:/make-appointment.html");
        registry.addViewController("/patient-list").setViewName("forward:/patient-list.html");
        registry.addViewController("/register-staff").setViewName("forward:/register-staff.html");
        registry.addViewController("/register_patient").setViewName("forward:/register_patient.html");
        registry.addViewController("/staff-dashboard").setViewName("forward:/staff-dashboard.html");
        registry.addViewController("/staff-list").setViewName("forward:/staff-list.html");
    }
}
