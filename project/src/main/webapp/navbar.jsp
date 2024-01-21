<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.sql.*" import="account.bean.*" %>

<% Utente u = (Utente) session.getAttribute("utente"); %>

<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/css/navbar.css">

<nav class="navbar navbar-expand-lg bg-body-tertiary">
  <div class="container-fluid">
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse justify-content-end text-center" id="navbarNavDropdown">
      <ul class="navbar-nav d-flex align-items-center">
        <%if(u == null) {%>
        <li class="nav-item ">
          <a class="nav-link active" aria-current="page" href="#">Home</a>
        </li>
        <li class="nav-item ">
          <a class="nav-link" href="#">Contatti</a>
        </li>            
        <li class="nav-item btnNav">
          <a href="Account/login.jsp">Accedi</a>
        </li>
        <%} else if(request.getRequestURL().toString().contains("Account/userArea.jsp")){%>  
	    <li class="nav-item ">
	      	<a class="nav-link" href="<%= request.getContextPath()%>/homePage.jsp" >Home</a>
	    </li>               
	    <li class="nav-item btnNav">
			<a id="logoutButton" href="<%= request.getContextPath()%>/Logout">Esci</a>
		</li>        	        
        <% } else {%>
	    <li class="nav-item ">
	      	<a class="nav-link" href="<%= request.getContextPath()%>/homePage.jsp" >Home</a>
	    </li>        
	    <li class="nav-item ">
	      	<a class="nav-link" href="<%= request.getContextPath()%>/Account/userArea.jsp" ><%=u.getNome()%> (<%=u.getRuolo()%>)</a>
	    </li>  
	    <li class="nav-item btnNav">
			<a id="logoutButton" href="<%= request.getContextPath()%>/Logout">Esci</a>
		</li> 
		<% } %>        
      </ul>
    </div>
  </div>
</nav>
