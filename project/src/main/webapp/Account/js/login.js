function validateForm() {
    var emailInput = document.forms["log_in"]["email"];
    var passwordInput = document.forms["log_in"]["password"];

	console.log("emailInput:", emailInput);
	console.log("passwordInput:", passwordInput);

    var regexEmail = /\S+@\S+\.\S+/;
    var regexPassword = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&.\-_])[A-Za-z\d@$!%*?&.\-_]+$/;

    var isEmailValid = validateField(emailInput, regexEmail, "errorMessageEmail", "Inserisci un indirizzo email valido.");
    var isPasswordValid = validateField(passwordInput, regexPassword, "errorMessagePassword", "La password deve contenere almeno 8 caratteri, lettere maiuscole, minuscole, numeri e caratteri speciali.");

	console.log("isEmailValid:", isEmailValid);
	console.log("isPasswordValid:", isPasswordValid);

    return isEmailValid && isPasswordValid;
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