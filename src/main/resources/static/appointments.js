document.addEventListener("DOMContentLoaded", function() {
    // Function to populate the appointments table
    function populateAppointmentTable(appointments) {
        var appointmentTable = document.getElementById('appointmentTable');
        clearAppointmentTable();

        appointments.forEach(appointment => {
            var row = appointmentTable.insertRow();

            var cell1 = row.insertCell(0);
            cell1.innerHTML = appointment.id;

            var cell2 = row.insertCell(1);
            cell2.innerHTML = appointment.day;

            var cell3 = row.insertCell(2);
            cell3.innerHTML = appointment.time;

            var cell4 = row.insertCell(3);
            cell4.innerHTML = appointment.patient;

            var cell5 = row.insertCell(4);
            cell5.innerHTML = appointment.clinician;

            var cell6 = row.insertCell(5);
            cell6.innerHTML = appointment.status;

            // ... other code ...

            var cell7 = row.insertCell(6);

            var editLink = document.createElement('a');
            editLink.href = 'edit-appointment.html?id=' + appointment.id;
            editLink.innerHTML = 'Edit';
            cell7.appendChild(editLink);

        });
    }

    // Function to clear the appointments table
    function clearAppointmentTable() {
        var appointmentTable = document.getElementById('appointmentTable');
        while (appointmentTable.rows.length > 1) {
            appointmentTable.deleteRow(1);
        }
    }

    // Fetching initial list of appointments
    fetch('http://localhost:8080/appointments', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        },
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        return response.json();
    })
    .then(appointments => {
        populateAppointmentTable(appointments);
    })
    .catch(error => {
        console.error('Error:', error);
    });

    fetch('http://localhost:8080/staff/clinicianNames', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        },

    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        return response.json();
    })
    .then(clinicians => {
        let clinicianDropdown = document.getElementById('clinicianFilter');
        clinicians.forEach(clinician => {
            let option = document.createElement('option');
            option.value = clinician.id;
            option.text = clinician.name;
            clinicianDropdown.appendChild(option);
        });
    })
    .catch(error => {
        console.error('Error fetching clinicians:', error);
    });

    // Filtering and fetching the appointments based on filters
    document.getElementById('filterForm').addEventListener('submit', async function(event) {
        event.preventDefault();

        let clinicianId = document.getElementById('clinicianFilter').value;
        let status = document.getElementById('statusFilter').value;
        let startDate = document.getElementById('startDate').value;
        let endDate = document.getElementById('endDate').value;

        // Construct the URL with query parameters
        const urlParams = new URLSearchParams({
            clinicianId: clinicianId,
            status: status,
            startDate: startDate,
            endDate: endDate
        });

        const url = 'appointments/getFilteredAppointments?' + urlParams.toString();

        try {
            const response = await fetch(url);

            if (!response.ok) {
                throw new Error('Network response was not ok');
            }

            const appointments = await response.json();
            populateAppointmentTable(appointments);

        } catch (error) {
            console.error('There was an error fetching filtered appointments:', error);
            alert('There was an error fetching filtered appointments');
        }
    });


    document.getElementById('dashboardButton').addEventListener('click', function() {
        window.location.href = 'dashboard.html';
    });

    document.getElementById('newAppointment').addEventListener('click', function() {
        window.location.href = 'make-appointment.html';
    });
});
