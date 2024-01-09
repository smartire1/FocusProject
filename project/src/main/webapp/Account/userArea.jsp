<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.sql.*" import="account.bean.*" 
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>

<html>
<head>	
	<meta charset="ISO-8859-1">
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>User Area</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" 
		  rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
		  
	<link type="text/css" rel="stylesheet" href="../css/style.css">
	<link type="text/css" rel="stylesheet" href="css/userArea.css">
	
	<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Source+Sans+Pro&display=swap">
</head>

<body>

	<%if(session.getAttribute("utente") != null){ 
		Utente user = (Utente) session.getAttribute("utente");	
	%>

	<header>
		<a id="logo" href="../homePage.jsp">FOCUS PROJECT</a>	
	</header>

	<jsp:include page="../navbar.jsp" />
	
	 <div class="welcome-message">
        <h2>Benvenuto nella tua area personale</h2>
    </div>
	
	 <div class="form-container mx-auto">
	    <form action="updateDetails.jsp" method="post">
	        <div class="form-group">
	            <input type="text" id="nome" name="nome" placeholder="<%=user.getNome() %>" readonly>
	        </div>
	        <div class="form-group">
	            <input type="text" id="cognome" name="cognome" placeholder="<%=user.getCognome() %>" readonly>
	        </div>
	        <div class="form-group">
	            <input type="text" id="email" name="email" placeholder="<%=user.getEmail() %>" readonly>
	            <input class="btns" type="submit" value="Modifica E-mail">
	        </div>
	        <div class="form-group">
	            <input type="password" id="password" name="password" placeholder="<%=user.getPassword() %>" readonly>
	            <input class="btns" type="submit" value="Modifica Password">
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
		
	<% } else {%>
		<script>
        	window.location.href = '../index.jsp';
    	</script>
	<% }%>
</body>
</html>