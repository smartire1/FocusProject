function validateForm() {
    var emailInput = document.forms["log_in"]["email"];
    var passwordInput = document.forms["log_in"]["pass"];

   
    var regexEmail = /\S+@\S+\.\S+/;
    var regexPassword = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;


    validateField(emailInput, regexEmail, "errorMessageEmail", "Inserisci un indirizzo email valido.");
    validateField(passwordInput, regexPassword, "errorMessagePassword", "La password deve contenere almeno 8 caratteri, lettere maiuscole, minuscole, numeri e caratteri speciali.");

    // Altri controlli se necessario...

    return false; // Modulo non inviato in automatico per mostrare gli errori
}

function validateField(input, regex, errorMessageId, errorMessage) {
    var errorMessageElement = document.getElementById(errorMessageId);

    if (!regex.test(input.value)) {
        errorMessageElement.innerHTML = errorMessage;
    } else {
        errorMessageElement.innerHTML = "";
    }
}
