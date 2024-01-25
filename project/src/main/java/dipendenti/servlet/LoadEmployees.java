package dipendenti.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Map;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import account.bean.*;
import dipendenti.bean.*;

@WebServlet("/LoadEmployees")
public class LoadEmployees extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
		HttpSession session = request.getSession();
		String idAzienda = (String) session.getAttribute("idAzienda");
		Utente utente = (Utente) session.getAttribute("utente");
		
	    if(utente == null) {
	    	response.sendRedirect(request.getContextPath() + "/Account/login.jsp");
	    	return;
	    }
		
		Collection<Utente> responsabili = null;
		Collection<Utente> subordinati = null;
		Collection<Utente> mieiSubordinati = null;
		UtenteDAO utenteDAO = new UtenteDAO(ds);
		
		// Caricare i turni di tutti i dipendenti
		TurnoDAO turnoDAO = new TurnoDAO(ds);
		Map<String, Collection<Turno>> turniResponsabili = new HashMap<>();
		Map<String, Collection<Turno>> turniMieiSubordinati = new HashMap<>();

		if(utente.getRuolo().equals("dirigente")) {
			try {
				Collection<Turno> turniResp;
				responsabili = utenteDAO.doRetrieveAllResponsabili(idAzienda);
				
				for(Utente u : responsabili) {
					turniResp = turnoDAO.doRetrieveAllByUser(idAzienda, u.getEmail());
					turniResponsabili.put(u.getEmail(), turniResp);
				}
				
				request.setAttribute("turniResponsabili", turniResponsabili);
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		if(utente.getRuolo().equals("responsabile")) {
			try {
				Collection<Turno> turniSub;
				mieiSubordinati = utenteDAO.doRetrieveAllSubMngdByResp(idAzienda, utente.getEmail());
				
				for(Utente u : mieiSubordinati) {
					turniSub = turnoDAO.doRetrieveAllByUser(idAzienda, u.getEmail());
					turniMieiSubordinati.put(u.getEmail(), turniSub);
				}
				
				request.setAttribute("turniMieiSubordinati", turniMieiSubordinati);
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		// Caricare i propri turni
		Collection<Turno> mieiTurni = null;
		if(!utente.getRuolo().equals("dirigente")) {
			try {
				mieiTurni = turnoDAO.doRetrieveAllByUser(idAzienda, utente.getEmail());
				
				request.setAttribute("mieiTurni", mieiTurni);
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		try {
			// Se l'utente è il Dirigente
			if(utente.getRuolo().equals("dirigente")) {
				responsabili = utenteDAO.doRetrieveAllResponsabili(idAzienda);
				subordinati = utenteDAO.doRetrieveAllSubordinati(idAzienda);
				
				request.setAttribute("responsabili", responsabili);
				request.setAttribute("subordinati", subordinati);
			}
			
			// Se l'utente è un Responsabile allora gli verranno mostrati solo i subordianti che lavorano ai suoi progetti
			if(utente.getRuolo().equals("responsabile")) {
				mieiSubordinati = utenteDAO.doRetrieveAllSubMngdByResp(idAzienda, utente.getEmail());
				
				request.setAttribute("mieiSubordinati", mieiSubordinati);
			}
			
		} catch (SQLException e) {
			System.out.println(e);
		}
		
		// Se ci sono eventualmente delle notifiche da mostrare:
		String notifica = (String) request.getAttribute("notifica");
		request.setAttribute("notifica", notifica);
		
		request.getRequestDispatcher("/Dipendenti/employeeDashboard.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
