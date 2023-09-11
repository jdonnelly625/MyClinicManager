function deregisterStaff(staff) {
    var loggedIn = sessionStorage.getItem('loggedIn');
        if (loggedIn !== 'true') {
            // User not logged in, so redirect to the login page
            window.location.href = 'login.html';
    }

    fetch('http://localhost:8080/staff/deregister', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(staff)
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        return response.json();
    })
    .then(deregisteredStaff => {
        console.log('Staff deregistered:', deregisteredStaff);
        window.location.href = '/staff-list.html';
    })
    .catch(error => {
        console.error('Error:', error);
    });
}


document.addEventListener("DOMContentLoaded", function() {
    // Send a GET request to get the list of staff
    fetch('http://localhost:8080/staff', {
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
    .then(staff => {
        var staffTable = document.getElementById('staffTable');

        staff.forEach(staffMember => {
            var row = staffTable.insertRow();

            var cell1 = row.insertCell(0);
            cell1.innerHTML = staffMember.firstName;

            var cell2 = row.insertCell(1);
            cell2.innerHTML = staffMember.lastName;

            var cell3 = row.insertCell(2);
            cell3.innerHTML = staffMember.email;

            var cell4 = row.insertCell(3);
            cell4.innerHTML = staffMember.id;

            var cell5 = row.insertCell(4);
            cell5.innerHTML = staffMember.jobTitle;


            // Adding deregistration button
            var cell6 = row.insertCell(5);
            var btn = document.createElement('button');
            btn.innerHTML = 'Deregister';
            btn.className = "btn";
            btn.onclick = function() {
                deregisterStaff(staffMember);
            };
            cell6.appendChild(btn);
        });
    })
    .catch(error => {
        console.error('Error:', error);
    });

    document.getElementById('registerStaff').addEventListener('click', function() {
        window.location.href = 'register-staff.html';
    });

    document.getElementById('dashboardButton').addEventListener('click', function() {
        window.location.href = 'dashboard.html';
    });
});