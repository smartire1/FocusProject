<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
String error = (String) request.getAttribute("error");
if (error == null)
	error = "";
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registrazione</title>
<meta charset="ISO-8859-1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/Account/css/signin.css">
<script type="text/javascript" src="js/signin.js"></script>
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css2?family=Source+Sans+Pro&display=swap">
</head>
<body>

	<header>
		<a id="logo" href="../index.jsp">FOCUS PROJECT</a>
	</header>

	<div id="signinContainer" class="container">
		<div class="row">
			<div class="col-md-7">
				<form name="registrationForm"
					action="<%=request.getContextPath()%>/Signin" method="POST"
					onsubmit="return validateForm()">
					<div class="inputBox">
						<!-- Campo in cui inserire il nome -->
						<i>Nome</i> <input type="text" name="nome" maxlength="20"
							placeholder="Inserisci il tuo nome" required>
						<p class="error-message" id="errorMessageNome"></p>
					</div>

					<div class="inputBox">
						<!-- Campo in cui inserire il cognome -->
						<i>Cognome</i> <input type="text" name="cognome" maxlength="20"
							placeholder="Inserisci il tuo cognome" required>
						<p class="error-message" id="errorMessageCognome"></p>
					</div>

					<div class="inputBox">
						<!-- Campo in cui inserire la p. iva -->
						<i>P.IVA</i> <input type="text" name="piva" maxlength="11"
							placeholder="Inserisci la tua P.IVA" required>
						<p class="error-message" id="errorMessagePiva"></p>
					</div>

					<div class="inputBox">
						<!-- Campo in cui inserire il nome dell'azienda -->
						<i>Nome Azienda</i> <input type="text" name="nome_azienda"
							maxlength="50" placeholder="Inserisci il nome della tua azienda"
							required>
						<p class="error-message" id="errorMessageNomeAzienda"></p>
					</div>

					<div class="inputBox">
						<!-- Campo in cui inserire l'e-mail -->
						<i>E-mail</i> <input type="email" name="email"
							placeholder="Inserisci il tuo indirizzo e-mail" required />
						<p class="error-message" id="errorMessageEmail"></p>
					</div>

					<div class="inputBox">
						<!-- Campo in cui inserire la password -->
						<i>Password</i> <input id="passwordInput" type="password"
							name="password" placeholder="Inserisci una password" required
							maxlength="20">
						<button id="showPass" type="button"
							onclick="togglePasswordVisibility()">Mostra password</button>
						<p class="error-message" id="errorMessagePassword"></p>
						<%
						if (!error.isEmpty()) {
						%>
						<p class="error-message"><%=error%></p>
						<%
						}
						%>
					</div>

					<div class="links">
						<p>
							Hai già un account? <a href="login.jsp">Accedi</a>
						</p>
					</div>

					<input type="submit" value="Registrati" id="submit-button">
				</form>
			</div>
			<div class="col-md-5 imgsContainer">
				<img alt="foto" src="imgs/undraw_Group_hangout_re_4t8r.png">
			</div>
		</div>
	</div>
	<footer class="footer">
		<div class="container">
			<nav>
				<p class="copyright">
					(Copyright
					<script>
						document.write(new Date().getFullYear())
					</script>
					) <a href="">Focus Project.</a> Tutti i diritti sono riservati.
				</p>
			</nav>
		</div>
	</footer>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
		crossorigin="anonymous"></script>
</body>
</html>