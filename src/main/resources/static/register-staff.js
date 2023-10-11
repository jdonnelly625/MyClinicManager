document.addEventListener('DOMContentLoaded', function () {

    var loggedIn = sessionStorage.getItem('loggedIn');
    if (loggedIn !== 'true') {
        // User not logged in, so redirect to the login page
        window.location.href = 'login.html';
    }
    const registrationForm = document.getElementById('registrationForm');

    registrationForm.addEventListener('submit', function (event) {
        event.preventDefault();

        const firstName = document.getElementById('firstName').value;
        const lastName = document.getElementById('lastName').value;
        const email = document.getElementById('email').value;
        const password = document.getElementById('password').value;
        const repeatPassword = document.getElementById('repeatPassword').value;
        const jobTitle = document.getElementById('jobTitle').value;
        const selectedRole = document.getElementById('staffRole').value;

        if (password !== repeatPassword) {
            alert("Passwords do not match.");
            return;
        }

        const staff = {
            firstName: firstName,
            lastName: lastName,
            email: email,
            password: password,
            jobTitle: jobTitle,
            role: selectedRole
        };

        fetch('/staff/register', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(staff)
        })
        .then(response => {
            if (!response.ok) {
                throw new Error("Network response was not ok");
            }
            return response.json();
        })
        .then(data => {
            console.log('Success:', data);
            window.location.href = 'staff-list.html';
        })
        .catch((error) => {
            console.error('Error:', error);
        });
    });

    document.getElementById('staffListButton').addEventListener('click', function() {
        window.location.href = 'staff-list.html';
    });
});