<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registrazione</title>
<link type="text/css" rel="stylesheet" href="css/signin.css">
<script type="text/javascript" src="js/signin.js"></script>
</head>
<body>

	<header>
		<div id="logo">
			<img src="imgs/.Logo.png">
		</div>
	</header>

	<div id="body">
		<form name="registrationForm"
			action="<%=request.getContextPath()%>/RegistrationServlet"
			method="POST" onsubmit="return validateForm()">
			<div class="inputBox">
				<i>Nome</i> <input type="text" name="nome" maxlength="20"
					placeholder="Inserisci il tuo nome" required>
				<p class="error-message" id="errorMessageNome"></p>
			</div>

			<div class="inputBox">
				<i>Cognome</i> <input type="text" name="cognome" maxlength="20"
					placeholder="Inserisci il tuo cognome" required>
				<p class="error-message" id="errorMessageCognome"></p>
			</div>

			<div class="inputBox">
				<i>P.IVA</i> <input type="text" name="piva" maxlength="11"
					placeholder="Inserisci la tua P.IVA" required>
				<p class="error-message" id="errorMessagePiva"></p>
			</div>

			<div class="inputBox">
				<i>Nome Azienda</i> <input type="text" name="nome_azienda"
					maxlength="50" placeholder="Inserisci il nome della tua azienda"
					required>
				<p class="error-message" id="errorMessageNomeAzienda"></p>
			</div>

			<div class="inputBox">
				<i>E-mail</i> <input type="email" name="email"
					placeholder="Inserisci il tuo indirizzo e-mail" required pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$"/>
				<p class="error-message" id="errorMessageEmail"></p>
			</div>

			<div class="inputBox">
				<i>Password</i> <input type="password" name="password"
					placeholder="Inserisci una password" required maxlength="20">
				<p class="error-message" id="errorMessagePassword"></p>
			</div>

			<div class="links">
				<p>
					Hai già un account? <a href="login.jsp">Accedi</a>
				</p>
			</div>

			<input type="submit" value="Registrati" id="submit-button">
		</form>

		<div class="imgsContainer">
			<img src="imgs/undraw_Group_hangout_re_4t8r.png">
		</div>
	</div>

</body>
</html>