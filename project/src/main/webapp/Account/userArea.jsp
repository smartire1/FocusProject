<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.sql.*" import="account.bean.*"  pageEncoding="UTF-8"%>

<%
	// SE L'UTENTE NON E' AUTENTICATO
	Utente u = (Utente) session.getAttribute("utente");
	if(u == null) {
		response.sendRedirect("login.jsp");
	}
%>

<!DOCTYPE html>

<html>
<head>	
	<meta charset="ISO-8859-1">
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>User Area</title>
	
	<!-- Bootstrap -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
		
	<!-- CSS --> 
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">	
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/Account/css/userArea.css">	
	
	<!-- JavaScript -->
	<script type="text/javascript" src="<%= request.getContextPath()%>/Account/js/userArea.js"></script>
	
	<!-- font -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css"> 	
	<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Source+Sans+Pro&display=swap">
</head>

<body>

	<%if(session.getAttribute("utente") != null){ 
		Utente user = (Utente) session.getAttribute("utente");	
	%>

	<header>
		<a id="logo" href="<%=request.getContextPath()%>/homePage.jsp">FOCUS PROJECT</a>	
	</header>
	
	<!-- Stampiamo eventuali notifiche di errore -->
	<%
	String notification = (String) request.getAttribute("notification");
	System.out.println(notification);
	if (notification != null && !notification.isEmpty()) {
	%>
	<div id="notification" class="notification">
		<span><%=notification%></span>
		<button onclick="closeNotification()">Chiudi</button>
	</div>
	<%
	}
	%>
	
	<jsp:include page="../navbar.jsp" />
	
	 <div class="welcome-message">
        <h2>Benvenuto nella tua area personale</h2>
    </div>
    
    <div class="container">
        <div class="row">
            <div class="col-lg-6">
                <div class="form-container">
					<form id="userDetailsForm" action="<%=request.getContextPath()%>/ChangeCredentials" method="post">
					    <div class="form-group">
					        <input type="text" id="nome" name="nome" value="<%=user.getNome() %>" readonly>
					    </div>
					    <div class="form-group">
					        <input type="text" id="cognome" name="cognome" value="<%=user.getCognome() %>" readonly>
					    </div>
					    <div class="form-group">
					        <input type="text" id="email" name="email" value="<%=user.getEmail() %>" readonly>
					        <input type="checkbox" id="enableEmail" onchange="enableInput('email', 'enableEmail')"> 
					        <label for="enableEmail">Abilita Modifica E-mail</label>
					    </div>
					    <div class="form-group">
					        <input type="password" id="password" name="password" placeholder="Inserisci nuova password..." readonly>
					        <input type="checkbox" id="enablePassword" onchange="enableInput('password', 'enablePassword')"> 
					        <label for="enablePassword">Abilita Modifica Password</label>
					        <input type="checkbox" id="showPassword" onchange="togglePasswordVisibility()"> 
					        <label for="showPassword">Mostra Password</label>
					    </div>
					    <input class="btns" type="submit" value="Apporta Modifiche">
					</form>
                </div>
            </div>
            <div class="col-lg-5">
                <img id="myCredentials" src="<%=request.getContextPath()%>/Account/imgs/undraw_My_password_re_ydq7.png" class="img-fluid">
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
					</script>) <a href="">Focus Project.</a> Tutti i diritti sono riservati.
				</p>
			</nav>
		</div>
	</footer>

	<!-- Bootstrap -->
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
		
	<% } else {%>
		<script>
        	window.location.href = '../index.jsp';
    	</script>
	<% }%>
</body>
</html>