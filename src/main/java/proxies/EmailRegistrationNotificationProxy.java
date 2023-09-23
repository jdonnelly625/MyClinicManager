package proxies;

import model.Patient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


/**
 * A concrete implementation of the RegistrationNotificationProxy interface that sends registration notifications
 * via email.
 * It is distinguished by the "EMAIL" qualifier with the intent to provide the mechanism to send email notifications to a given patient upon
 * registration.
 * Note: The actual email sending mechanism is a placeholder and would require a full implementation for a real-world scenario. Work in progress
 */
@Component
@Qualifier("EMAIL")
public class EmailRegistrationNotificationProxy implements RegistrationNotificationProxy {

    @Override
    public void notifyPatient(Patient patient) {
        //System.out.println("Sending email notification to patient: " + patient.getName());
        // actual implementation to send an email would go here
    }
}
