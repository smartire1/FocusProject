function enableInput(inputId, checkboxId) {
	var inputField = document.getElementById(inputId);
	var checkbox = document.getElementById(checkboxId);

	if (checkbox.checked) {
		inputField.readOnly = false;
		inputField.focus();
	} else {
		inputField.readOnly = true;
	}
}

function togglePasswordVisibility() {
	var passwordInput = document.getElementById("password");
	var showPasswordCheckbox = document.getElementById("showPassword");

	if (showPasswordCheckbox.checked) {
		passwordInput.type = "text";
	} else {
		passwordInput.type = "password";
	}
}