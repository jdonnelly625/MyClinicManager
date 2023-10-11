function deregisterPatient(patient) {
    var loggedIn = sessionStorage.getItem('loggedIn');
        if (loggedIn !== 'true') {
            // User not logged in, so redirect to the login page
            window.location.href = 'login';
    }

    fetch('/api/patients/deregister', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(patient)
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        return response.json();
    })
    .then(deregisteredPatient => {
        console.log('Patient deregistered:', deregisteredPatient);
        window.location.href = '/patient-list';
    })
    .catch(error => {
        console.error('Error:', error);
    });
}


document.addEventListener("DOMContentLoaded", function() {
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
        return response.json();
    })
    .then(patients => {
        var patientTable = document.getElementById('patientTable');

        patients.forEach(patient => {
            var row = patientTable.insertRow();

            var cell1 = row.insertCell(0);
            cell1.innerHTML = patient.firstName;

            var cell2 = row.insertCell(1);
            cell2.innerHTML = patient.lastName;

            var cell3 = row.insertCell(2);
            cell3.innerHTML = patient.email;

            var cell4 = row.insertCell(3);
            cell4.innerHTML = patient.id;


            // Adding deregistration button
            var cell5 = row.insertCell(4);
            var btn = document.createElement('button');
            btn.innerHTML = 'Deregister';
            btn.onclick = function() {
                deregisterPatient(patient);
            };
            cell5.appendChild(btn);
        });
    })
    .catch(error => {
        console.error('Error:', error);
    });

    document.getElementById('backButton').addEventListener('click', function() {
        window.location.href = 'register_patient';
    });

    document.getElementById('dashboardButton').addEventListener('click', function() {
        window.location.href = 'dashboard';
    });
});
