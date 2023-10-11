document.addEventListener("DOMContentLoaded", function() {
    var loggedIn = sessionStorage.getItem('loggedIn');
    if (loggedIn !== 'true') {
        // User not logged in, so redirect to the login page
        window.location.href = 'login.html';
    }

    var name = sessionStorage.getItem('username');
    document.getElementById('welcome').innerHTML = 'Welcome, ' + name;

    // Send a GET request to get the list of patients
    fetch('/api/patients', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        },
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        return response.json();  // This returns a promise
    })
    .then(patients => {
        // Here, you can handle the list of patients
        var patientTable = document.getElementById('patientTable');

        patients.forEach(patient => {
            var row = patientTable.insertRow();

            var cell1 = row.insertCell(0);
            cell1.innerHTML = patient.firstName;

            var cell2 = row.insertCell(1);
            cell2.innerHTML = patient.lastName;

            var cell3 = row.insertCell(2);
            cell3.innerHTML = patient.email;
        });
    })
    .catch(error => {
        console.error('Error:', error);
    });

    document.getElementById('patientListButton').addEventListener('click', function() {
        window.location.href = 'patient-list';
    });

    document.getElementById('logout').addEventListener('click', function() {
            sessionStorage.clear();
            window.location.href = 'login';
    });

    document.getElementById('manageAppointments').addEventListener('click', function() {

        window.location.href = 'appointments';
    });

    document.getElementById('viewStaff').addEventListener('click', function() {
        window.location.href = 'staff-list';
    });
});