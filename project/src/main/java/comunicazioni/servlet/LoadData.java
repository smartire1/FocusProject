package comunicazioni.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import comunicazioni.bean.*;
import account.bean.*;

@WebServlet("/LoadData")
public class LoadData extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Questa Servlet carica le informazioni, quali 'comunicazioni' e 'permessi'.
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
	    HttpSession session = request.getSession();
	    String piva = (String) session.getAttribute("idAzienda");
	    Utente utente = (Utente) session.getAttribute("utente");
	    
	    if(utente == null) {
	    	response.sendRedirect(request.getContextPath() + "/Account/login.jsp");
	    	return;
	    }
	    
	    // Per comunicazioni
	    List<Comunicazione> news = new ArrayList<>();
	    ComunicazioneDAO comunicazioneDAO = new ComunicazioneDAO(ds);
	    
	    // Per permessi (da gestire)
	    List<Permesso> permessiResponsabili = new ArrayList<>();
	    List<Permesso> permessiSubordinati = new ArrayList<>();
	    
	    List<Permesso> permessiRespGestiti = new ArrayList<>();
	    List<Permesso> permessiSubGestiti = new ArrayList<>();
	    
	    List<Permesso> permessiRichiesti = new ArrayList<>();
	    
	    PermessoDAO permessoDAO = new PermessoDAO(ds);
	    
	    try {
	    	// PER COMUNICAZIONI
	    	news = comunicazioneDAO.doRetrieveAll(piva);
	    	
	    	if(news.isEmpty()) {
	    		System.out.println("La lista delle news è vuota!");
	    	} else {
	    		Collections.reverse(news);
	    	}
	    	
	    	request.setAttribute("news", news);
	    	
	    	// ---------------------------
	    	
	    	// PER PERMESSI (RESPONSABILI)
	    	permessiResponsabili = permessoDAO.doRetrieveAllNotManagedAndByRuolo(piva, "responsabile");
	    	permessiRespGestiti = permessoDAO.doRetrieveAllManagedAndByRuolo(piva, "responsabile");
	    	
	    	if(permessiResponsabili.isEmpty()) {
	    		System.out.println("La lista dei permessi responsabili è vuota!");
	    	} else {
	    		Collections.reverse(permessiResponsabili);
	    	}
	    	
	    	if(permessiRespGestiti.isEmpty()) {
	    		System.out.println("La lista dei permessi responsabili gestiti è vuota!");
	    	} else {
	    		Collections.reverse(permessiRespGestiti);
	    	}
	    	
	    	// PER PERMESSI (SUBORDINATI)
	    	permessiSubordinati = permessoDAO.doRetrieveAllNotManagedAndByRuolo(piva, "subordinato");
	    	permessiSubGestiti = permessoDAO.doRetrieveAllManagedAndByRuolo(piva, "subordinato");
	    	
	    	if(permessiSubordinati.isEmpty()) {
	    		System.out.println("La lista dei permessi subordinati è vuota!");
	    	} else {
	    		Collections.reverse(permessiSubordinati);
	    	}
	    	
	    	if(permessiSubGestiti.isEmpty()) {
	    		System.out.println("La lista dei permessi subordinati è vuota!");
	    	} else {
	    		Collections.reverse(permessiSubGestiti);
	    	}
	    	
	    	// PERSONALI
	    	if(utente.getRuolo().equals("responsabile") || utente.getRuolo().equals("subordinato")) {
	    		permessiRichiesti = permessoDAO.doRetrieveAllByUser(piva, utente.getEmail());
		    	
		    	if(permessiRichiesti.isEmpty()) {
		    		System.out.println("La lista dei permessi richiesti è vuota!");
		    	} else {
		    		Collections.reverse(permessiRichiesti);
		    	}
	    	}
	    	
	    	request.setAttribute("permessiResponsabili", permessiResponsabili);
	    	request.setAttribute("permessiRespGestiti", permessiRespGestiti);
	    	
	    	request.setAttribute("permessiSubordinati", permessiSubordinati);
	    	request.setAttribute("permessiSubGestiti", permessiSubGestiti);
	    	
	    	request.setAttribute("permessiRichiesti", permessiRichiesti);
	    }
	    catch(SQLException e) {
	    	e.printStackTrace();
	    }
	    
	    String notification = (String) request.getAttribute("notification");
	    
	    request.getRequestDispatcher("/Comunicazioni/communicationDashboard.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
