document.getElementById('loginForm').addEventListener('submit', function(event) {
    var turnstileResponse = document.querySelector('.cf-turnstile input[name="cf-turnstile-response"]').value;

    if (!turnstileResponse) {
        event.preventDefault();
        alert('Please complete the CAPTCHA.');
    }
});

document.addEventListener('DOMContentLoaded', function() {
    // Check if there is a failed login, and if so, reinitialize Turnstile
    if (document.querySelector('.cf-turnstile')) {
        Turnstile.render('.cf-turnstile'); // Re-render Turnstile widget
    }
});
