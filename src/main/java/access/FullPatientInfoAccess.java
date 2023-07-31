package access;

import model.Patient;

import java.util.List;

public interface FullPatientInfoAccess extends BasicPatientInfoAccess {
    public List<String> getPatientMedications(Patient patient);

    public List<String> getProviderNotes(Patient patient);
}
