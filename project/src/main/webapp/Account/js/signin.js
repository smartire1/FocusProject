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
    var regexPassword = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&.\-_])[A-Za-z\d@$!%*?&.\-_]+$/;

    var isNomeValid = validateField(nomeInput, regexOnlyLetters, "errorMessageNome", "Nome deve contenere solo lettere.");
    var isCognomeValid = validateField(cognomeInput, regexOnlyLetters, "errorMessageCognome", "Cognome deve contenere solo lettere.");
    var isPIVAValid = validateField(pivaInput, regexPiva, "errorMessagePiva", "La P.IVA deve contenere esattamente 11 cifre numeriche.");
    var isNomeAziendaValid = validateField(nomeAziendaInput, regexOnlyLetters, "errorMessageNomeAzienda", "Nome Azienda deve contenere solo lettere.");
    var isEmailValid = validateField(emailInput, regexEmail, "errorMessageEmail", "Inserisci un indirizzo email valido.");
    var isPasswordValid = validateField(passwordInput, regexPassword, "errorMessagePassword", "La password deve contenere almeno 8 caratteri, lettere maiuscole, minuscole, numeri e caratteri speciali.");

    return isNomeValid && isCognomeValid && isPIVAValid && isNomeAziendaValid && isEmailValid && isPasswordValid;
}



function validateField(input, regex, errorMessageId, errorMessage) {
	
    var errorMessageElement = document.getElementById(errorMessageId);

    if (!regex.test(input.value)) {
        errorMessageElement.innerHTML = errorMessage;
        return false;
    }
    else {
        errorMessageElement.innerHTML = "";
        return true;
    }
}



function togglePasswordVisibility() {
    var passwordInput = document.getElementById("passwordInput");
    var passwordButton = document.getElementById("showPass");
    if (passwordInput.type === "password") {
        passwordInput.type = "text";
        passwordButton.innerText = "Nascondi password"
    } else {
        passwordInput.type = "password";
        passwordButton.innerText = "Mostra password"
    }
}