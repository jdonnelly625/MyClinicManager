package directories;

import model.Patient;
import model.Staff;
import org.springframework.stereotype.Service;

import java.util.LinkedList;

@Service
public class StaffDirectory {

    /** The list of all registered patients */
    private LinkedList<Staff> staffDirectory;

    /** Constructs a new empty list of patients */
    public StaffDirectory() {
        staffDirectory = new LinkedList<Staff>();
    }
    /** Adds a patient */
    public Staff addStaff(Staff staff) {
        staffDirectory.add(staff);
        return staff;
    }


    public Staff getStaffByEmail(String email) {
        for(Staff s : staffDirectory) {
            if(email.equals(s.getEmail())) return s;
        }
        System.out.println("Checked for email but couldn't find");
        return null;
    }
}
