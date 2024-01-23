package dipendenti.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import account.bean.*;
import dipendenti.bean.*;

@WebServlet("/GenerateStats")
public class GenerateStats extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
		HttpSession session = request.getSession();
		String idAzienda = (String) session.getAttribute("idAzienda");
		Utente utente = (Utente) session.getAttribute("utente");

		UtenteDAO utenteDAO = new UtenteDAO(ds);
		
		try {
			if(utente.getRuolo().equals("dirigente")) {
				System.out.println("Generazione statistiche per dirigente");
				
				List<StatisticheResponsabile> statsResponsabili = new ArrayList<>();
				List<StatisticheSubordinato> statsSubordinati = new ArrayList<>();
				
				Collection<Utente> responsabili = utenteDAO.doRetrieveAllResponsabili(idAzienda);
				Collection<Utente> subordinati = utenteDAO.doRetrieveAllSubordinati(idAzienda);
				
				StatisticheResponsabileDAO statsResponsabileDAO = new StatisticheResponsabileDAO(ds);
				StatisticheResponsabile sr;
				
				for(Utente responsabile : responsabili) {
					sr = statsResponsabileDAO.getStatisticheResponsabile(responsabile.getEmail());
					statsResponsabili.add(sr);
				}
				
				StatisticheSubordinatoDAO statsSubordinatoDAO = new StatisticheSubordinatoDAO(ds);
				StatisticheSubordinato ss;
				
				for(Utente subordinato : subordinati) {
					ss = statsSubordinatoDAO.getStatisticheSubordinato(subordinato.getEmail());
					statsSubordinati.add(ss);
				}
				// ...........................
				
				request.setAttribute("statsResponsabili", statsResponsabili);
				request.setAttribute("statsSubordinati", statsSubordinati);
			}
			
			
			
			if(utente.getRuolo().equals("responsabile")) {
				System.out.println("Generazione statistiche per responsabile");
				
				StatisticheResponsabileDAO statsResponsabileDAO = new StatisticheResponsabileDAO(ds);
				StatisticheResponsabile myStats = statsResponsabileDAO.getStatisticheResponsabile(utente.getEmail());
				List<StatisticheSubordinato> statsSubordinati = new ArrayList<>();
				
				Collection<Utente> subordinatiACarico = utenteDAO.doRetrieveAllSubMngdByResp(idAzienda, utente.getEmail());
				
				StatisticheSubordinatoDAO statsSubordinatoDAO = new StatisticheSubordinatoDAO(ds);
				StatisticheSubordinato ss;
				
				for(Utente subordinato : subordinatiACarico) {
					ss = statsSubordinatoDAO.getStatisticheSubordinato(subordinato.getEmail());
					statsSubordinati.add(ss);
				}

				// ...........................
				
				request.setAttribute("myStatsResponsabile", myStats);
				request.setAttribute("statsMySubordinati", statsSubordinati);
			}
			
			
			
			if(utente.getRuolo().equals("subordinato")) {
				System.out.println("Generazione statistiche per subordinato");
				
				StatisticheSubordinatoDAO statsSubordinatoDAO = new StatisticheSubordinatoDAO(ds);
				StatisticheSubordinato myStats = statsSubordinatoDAO.getStatisticheSubordinato(utente.getEmail());
				// ...........................
				
				request.setAttribute("myStatsSubordinato", myStats);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Dipendenti/Stats.jsp");
        dispatcher.forward(request, response);
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
