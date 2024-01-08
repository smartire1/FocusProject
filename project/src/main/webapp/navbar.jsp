<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.sql.*" import="account.bean.*" 
    pageEncoding="UTF-8"%>
<link href="css/navbar.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet">
<script type="text/javascript" src="js/navbar.js"></script>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <div class="container">
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse justify-content-center flex-grow-1" id="navbarNav">
      <ul class="navbar-nav ml-auto align-items-center">
     
        <%if(session.getAttribute("utente") == null) {%>
        <li class="nav-item">
          <a class="nav-link" href="index.jsp" onclick="scrollToContacts()">Contatti</a>
        </li>    
        <li class="nav-item buttons" id="access">
			<a id="loginButton" href="Account/login.jsp">Accedi</a>


          <% } else { Utente user = (Utente) session.getAttribute("utente");%>
	        <li class="nav-item buttons">
	        	<a id="PareaBtn"href="Account/userArea.jsp" class="btns">Area Personale</a>
	        </li>          
	        <li class="nav-item">
				<div class="buttons">
					<a id="logoutButton" href="<%= request.getContextPath()%>/Logout">Esci</a>
				</div>						
          <% } %>
        </li>

      </ul>
    </div>
  </div>
</nav>
