package proxies;

import model.Patient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("EMAIL")
public class EmailRegistrationNotificationProxy implements RegistrationNotificationProxy {

    @Override
    public void notifyPatient(Patient patient) {
        //System.out.println("Sending email notification to patient: " + patient.getName());
        // actual implementation to send an email would go here
    }
}
