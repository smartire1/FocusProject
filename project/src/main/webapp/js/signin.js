// validation.js
function validateForm() {
    var nomeInput = document.forms["registrationForm"]["nome"];
    var cognomeInput = document.forms["registrationForm"]["cognome"];
    var pivaInput = document.forms["registrationForm"]["piva"];
    var nomeAziendaInput = document.forms["registrationForm"]["nome_azienda"];
    var emailInput = document.forms["registrationForm"]["email"];
    var passwordInput = document.forms["registrationForm"]["password"];

    var regexOnlyLetters = /^[A-Za-z]+$/;
    var regexPiva = /^\d{11}$/;
    var regexEmail = /\S+@\S+\.\S+/;
    var regexPassword = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;

    validateField(nomeInput, regexOnlyLetters, "errorMessageNome", "Nome deve contenere solo lettere.");
    validateField(cognomeInput, regexOnlyLetters, "errorMessageCognome", "Cognome deve contenere solo lettere.");
    validateField(pivaInput, regexPiva, "errorMessagePiva", "La P.IVA deve contenere esattamente 11 cifre numeriche.");
    validateField(nomeAziendaInput, regexOnlyLetters, "errorMessageNomeAzienda", "Nome Azienda deve contenere solo lettere.");
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
