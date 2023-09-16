package directories;

import jakarta.persistence.EntityNotFoundException;
import model.Administrator;
import model.Clinician;
import model.Staff;
import org.springframework.stereotype.Service;
import repository.AdministratorRepository;
import repository.ClinicianRepository;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class StaffDirectory {

    private final ClinicianRepository clinicianRepository;
    private final AdministratorRepository administratorRepository;

    public StaffDirectory(ClinicianRepository clinicianRepository, AdministratorRepository administratorRepository) {

        this.clinicianRepository = clinicianRepository;
        this.administratorRepository = administratorRepository;
    }




    /** Gets all staff from the directory */
    public List<Staff> getAllStaff() {

        List<Staff> allStaff = new LinkedList<Staff>();
        allStaff.addAll(getAllClinicians());
        allStaff.addAll(getAllAdministrators());
        return allStaff;



    }

    /** Gets all clinicians from the directory */
    public List<Clinician> getAllClinicians() {

        return clinicianRepository.findAll();


    }

    /** Gets all administrators from the directory */
    public List<Administrator> getAllAdministrators() {

        return administratorRepository.findAll();


    }


    public Staff getStaffByEmail(String email) {
        for(Staff s : getAllStaff()) {
            if(email.equals(s.getEmail())) return s;
        }
        return null;
    }

    public List<Clinician> getCliniciansByLastName(String lastName) {

        List<Clinician> clinicians = clinicianRepository.findByLastNameIgnoreCase(lastName);
        return clinicians;
    }

    public Staff addClinician(Clinician clinician) {
        clinicianRepository.save(clinician);
        return clinician;

    }

    public Clinician getClinicianById(String clinicianId) {

        return clinicianRepository.findById(clinicianId).get();
    }

    public Staff addAdministrator(Administrator administrator) {
        System.out.println("Adding administrator");
        administratorRepository.save(administrator);
        System.out.println("added");
        return administrator;
    }



    public Staff removeStaffById(String staffId) {
        Optional<Clinician> clinician = clinicianRepository.findById(staffId);

        if (clinician.isPresent()) {
            clinicianRepository.deleteById(staffId);
            return clinician.get();
        }

        Optional<Administrator> administrator = administratorRepository.findById(staffId);

        if (administrator.isPresent()) {
            administratorRepository.deleteById(staffId);
            return administrator.get();
        }
        throw new EntityNotFoundException("No such staff member found");

    }
}
