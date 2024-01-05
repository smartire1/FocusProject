<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Focus Project</title>


<link type="text/css" rel="stylesheet" href="css/style.css">
<link type="text/css" rel="stylesheet" href="css/index.css">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css2?family=Source+Sans+Pro&display=swap">


</head>

<body>
	<header>
		<a id="logo" href="index.jsp">FOCUS PROJECT</a>

		<div class="buttons">
			<a id="loginButton" href="Account/login.jsp">Accedi</a>
		</div>

	</header>

	<div class="container text-center">
		<div class="row">
			<div class="col-md-6">
				<h2>Benvenuto su Focus Project</h2>
				<p>La soluzione ideale per ottimizzare la gestione delle risorse
					umane nella tua azienda. Semplice, intuitivo e progettato per
					adattarsi alle tue esigenze, Focus Project ti aiuta a coordinare
					progetti, assegnare compiti e monitorare il progresso in modo
					efficiente.</p>
			</div>

			<div class="col-md-6">
				<div class="imgsContainer">
					<img src="imgs/undraw_React_re_g3ui.png">
				</div>
			</div>
		</div>
	</div>

	<div class="container text-center">
		<div class="row">
			<div class="col-md-6">
				<div class="imgsContainer">
					<img src="imgs/undraw_Engineering_team_a7n2.png">
				</div>

			</div>

			<div class="col-md-6">
				<div class="imgsContainer">
					<p>Che tu sia un dirigente che gestisce l'intera azienda, un
						responsabile che coordina progetti o un subordinato impegnato
						nelle attività quotidiane, Focus Project è qui per semplificare il
						tuo lavoro e migliorare l'efficienza.</p>
				</div>
			</div>
		</div>
	</div>

	<div class="buttons">
		<a id="register-button" href="Account/signin.jsp">Registra la tua
			Azienda</a>
	</div>

	<div id="contactsContainer">
		<h2>Contatti</h2>
		<hr />
		<div class="contact-info">
			<p>
				<strong>E-mail:</strong> <a href="mailto:focusproject@mail.com">focusproject@mail.com</a>
			</p>
			<p>
				<strong>Telefono:</strong> +123 456 789
			</p>
			<p>
				<strong>Indirizzo:</strong> Via Innovazione, 12345 Città Moderna,
				CMP 001
			</p>
		</div>

		<p>Per ulteriori informazioni o per ricevere assistenza, puoi
			contattarci tramite il box sottostante</p>

		<div id="contuctUs">
			<form action="" method="post">
				<label for="nome">Nome:</label> <input type="text" id="nome"
					name="nome" required> <label for="email">E-mail:</label> <input
					type="email" id="email" name="email" required> <label
					for="messaggio">Messaggio:</label>
				<textarea id="messaggio" name="messaggio" rows="4" required></textarea>

				<button id="supportButton" type="submit">Invia Messaggio</button>
			</form>

			<div id="contactImg">
				<img src="imgs/undraw_articles_wbpb.png">
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
