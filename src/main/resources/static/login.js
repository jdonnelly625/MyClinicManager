document.addEventListener("DOMContentLoaded", function() {
    document.getElementById('loginForm').addEventListener('submit', function(e) {
        e.preventDefault();  // Prevent the form from submitting normally

        var username = document.getElementById('username').value;
        var password = document.getElementById('password').value;

        // Prepare the data to send
        var data = {
            username: username,
            password: password
        };

        // Send a POST request
        fetch('http://localhost:8080/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();  // This returns a promise
        })
        .then(responseData => {
            if (responseData.loggedIn) {
                // Login was successful
                alert('Login successful! Welcome, ' + responseData.name + '!');
                sessionStorage.setItem('loggedIn', 'true');
                sessionStorage.setItem('username', responseData.name);
                window.location.href = 'dashboard.html';
            } else {
                // Login failed
                alert('Login failed. Please check your username and password.');
            }
        })
        .catch(error => {
            console.error('Error:', error);
        });
    });
});