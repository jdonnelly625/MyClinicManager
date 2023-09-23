package proxies;

import model.Patient;

/**
 * An interface that sends registration notifications based on whichever concrete implementation.
 * Note: Work in progress
 */
public interface RegistrationNotificationProxy {

    void notifyPatient(Patient patient);
}
