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

@WebServlet("/LoadData")
public class LoadData extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Questa Servlet carica le informazioni, quali 'comunicazioni' e 'permessi'
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
	    HttpSession session = request.getSession();
	    String piva = (String) session.getAttribute("idAzienda");
	    
	    // Per comunicazioni
	    List<Comunicazione> news = new ArrayList<>();
	    ComunicazioneDAO comunicazioneDAO = new ComunicazioneDAO(ds);
	    
	    // Per permessi
	    List<Permesso> permessiResponsabili = new ArrayList<>();
	    List<Permesso> permessiSubordinati = new ArrayList<>();
	    PermessoDAO permessoDAO = new PermessoDAO(ds);
	    
	    try {
	    	// PER COMUNICAZIONI
	    	news = comunicazioneDAO.doRetrieveAll(piva);
	    	
			if(news == null) {
				news = new ArrayList<>();
			}
	    	
	    	if(news.isEmpty()) {
	    		System.out.println("La lista delle news è vuota!");
	    	} else {
	    		Collections.reverse(news);
	    	}
	    	
	    	request.setAttribute("news", news);
	    	
	    	// ---------------------------
	    	
	    	// PER PERMESSI (RESPONSABILI)
	    	permessiResponsabili = permessoDAO.doRetrieveAllNotManagedAndByRuolo(piva, "responsabile");
	    	
	    	if(permessiResponsabili == null) {
	    		permessiResponsabili = new ArrayList<>();
	    	}
	    	
	    	if(permessiResponsabili.isEmpty()) {
	    		System.out.println("La lista dei permessi responsabili è vuota!");
	    	} else {
	    		Collections.reverse(news);
	    	}
	    	
	    	// PER PERMESSI (SUBORDINATI)
	    	permessiSubordinati = permessoDAO.doRetrieveAllNotManagedAndByRuolo(piva, "subordinato");
	    	
	    	if(permessiSubordinati == null) {
	    		permessiSubordinati = new ArrayList<>();
	    	}
	    	
	    	if(permessiSubordinati.isEmpty()) {
	    		System.out.println("La lista dei permessi subordinati è vuota!");
	    	} else {
	    		Collections.reverse(news);
	    	}
	    	
	    	request.setAttribute("permessiResponsabili", permessiResponsabili);
	    	request.setAttribute("permessiSubordinati", permessiSubordinati);
	    }
	    catch(SQLException e) {
	    	e.printStackTrace();
	    }
	    
	    request.getRequestDispatcher("/Comunicazioni/communicationDashboard.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
