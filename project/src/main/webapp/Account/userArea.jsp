<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>

<html>
<head>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">	
	<meta charset="ISO-8859-1">
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>User Area</title>

<link type="text/css" rel="stylesheet" href="../css/style.css">
<link type="text/css" rel="stylesheet" href="css/userArea.css">

</head>

<body>
	<header>
		<a id="logo" href="../index.jsp">FOCUS PROJECT</a>

		<div class="buttons">
			<a id="logoutButton" href="Account/logout.jsp">Esci</a>
		</div>

	</header>
	
	 <div class="welcome-message">
        <h2>Benvenuto nella tua area personale</h2>
    </div>
	
	 <div class="form-container">
    <form action="updateDetails.jsp" method="post">
        <div class="form-group">
            <input type="text" id="nome" name="nome" placeholder="Nome">
        </div>
        <div class="form-group">
            <input type="text" id="cognome" name="cognome" placeholder="Cognome">
        </div>
        <div class="form-group">
            <input type="text" id="email" name="email" placeholder="E-mail">
            <input type="submit" value="Modifica E-mail">
        </div>
        <div class="form-group">
            <input type="password" id="password" name="password" placeholder="Password">
            <input type="submit" value="Modifica Password">
        </div>
    </form>
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