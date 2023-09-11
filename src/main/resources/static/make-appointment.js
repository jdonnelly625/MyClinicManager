document.addEventListener("DOMContentLoaded", function() {
    var patientToAdd;
    var clinicianToAdd;

    document.getElementById('searchPatient').addEventListener('click', function() {
        var lastName = document.getElementById('patientLastName').value;
        fetch('http://localhost:8080/patients/searchPatient?lastName=' + lastName)
            .then(response => response.json())
            .then(patients => {
                var patientTBody = document.getElementById('patientList').getElementsByTagName('tbody')[0];
                patientTBody.innerHTML = ''; // Clear existing rows

                patients.forEach(patient => {
                    var row = patientTBody.insertRow();

                    var cell1 = row.insertCell(0);
                    cell1.textContent = patient.firstName;

                    var cell2 = row.insertCell(1);
                    cell2.textContent = patient.lastName;

                    var cell3 = row.insertCell(2);
                    cell3.textContent = patient.email;

                    var cell4 = row.insertCell(3);
                    cell4.textContent = patient.id;

                    var cell5 = row.insertCell(4);
                    var selectButton = document.createElement('button');
                    selectButton.setAttribute('type', 'button');
                    selectButton.textContent = 'Select';
                    selectButton.addEventListener('click', function() {
                        patientToAdd = patient;
                        alert('Selected patient: ' + patient.firstName + ' ' + patient.lastName);
                    });
                    cell5.appendChild(selectButton);
                });
            });

    });

    document.getElementById('searchClinician').addEventListener('click', function() {
        var lastName = document.getElementById('clinicianLastName').value;
        fetch('http://localhost:8080/staff/searchClinician?lastName=' + lastName)
            .then(response => response.json())
            .then(clinicians => {
                var clinicianTBody = document.getElementById('clinicianList').getElementsByTagName('tbody')[0];
                clinicianTBody.innerHTML = ''; // Clear existing rows

                clinicians.forEach(clinician => {
                    var row = clinicianTBody.insertRow();

                    var cell1 = row.insertCell(0);
                    cell1.textContent = clinician.firstName;

                    var cell2 = row.insertCell(1);
                    cell2.textContent = clinician.lastName;

                    var cell3 = row.insertCell(2);
                    cell3.textContent = clinician.email;

                    var cell4 = row.insertCell(3);
                    cell4.textContent = clinician.id;

                    var cell5 = row.insertCell(4);
                    var selectButton = document.createElement('button');
                    selectButton.setAttribute('type', 'button');
                    selectButton.textContent = 'Select';
                    selectButton.addEventListener('click', function() {
                        clinicianToAdd = clinician;
                        alert('Selected clinician: ' + clinician.firstName + ' ' + clinician.lastName);
                    });
                    cell5.appendChild(selectButton);
                });
            });
    });


    document.getElementById('registrationForm').addEventListener('submit', function(e) {
        e.preventDefault();

        if (!patientToAdd || !clinicianToAdd) {
            alert('Please select a patient and a clinician.');
            return;
        }

        var datetime = document.getElementById('datetime').value;

         if (!datetime) {
            alert('Please select a date and time.');
            return;
         }

        var data = {
            patientId: patientToAdd.id,
            clinicianId: clinicianToAdd.id,
            datetime: datetime
        };

        fetch('http://localhost:8080/appointments/schedule', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        })
        .then(response => response.json())
        .then(responseData => {
            if (responseData.success) {
                alert('Appointment registered successfully.');
            } else {
                alert('Failed to register appointment. ' + (responseData.message || ''));
            }
        })
        .catch(error => {
            console.error('Error:', error);
        });
    });

    document.getElementById('appointmentListButton').addEventListener('click', function() {
        window.location.href = 'appointments.html';
    });
});
