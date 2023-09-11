document.addEventListener("DOMContentLoaded", function() {
    // Send a GET request to get the list of appointments
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
        var appointmentTable = document.getElementById('appointmentTable');

        appointments.forEach(appointment => {
            var row = appointmentTable.insertRow();

            var cell1 = row.insertCell(0);
            cell1.innerHTML = appointment.id;

            var cell2 = row.insertCell(1);
            cell2.innerHTML = appointment.appointmentTime;

            var cell3 = row.insertCell(2);
            cell3.innerHTML = appointment.status;

            var cell4 = row.insertCell(3);
            cell4.innerHTML = appointment.clinician.id;

            var cell5 = row.insertCell(4);
            cell5.innerHTML = appointment.patient.id;
        });
    })
    .catch(error => {
        console.error('Error:', error);
    });


    document.getElementById('dashboardButton').addEventListener('click', function() {
        window.location.href = 'dashboard.html';
    });

    document.getElementById('newAppointment').addEventListener('click', function() {
            window.location.href = 'make-appointment.html';
    });
});
