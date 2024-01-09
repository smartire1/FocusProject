package dipendenti.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import account.bean.*;

@WebServlet("/LoadEmployees")
public class LoadEmployees extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
		SubordinatoDAO subordinatoDAO = new SubordinatoDAO(ds);
		ResponsabileDAO responsabileDAO = new ResponsabileDAO(ds);
		UtenteDAO utenteDAO = new UtenteDAO(ds);
		
		HttpSession session = request.getSession();
		String piva = (String) session.getAttribute("piva");
		
		String employeeEmail = request.getParameter("id");
		
		// Caso in cui l'utente voglia visualizzare un dipendente specifico
		if (employeeEmail != null && !employeeEmail.isEmpty()) {
			try {
				Subordinato subordinato;
				subordinato = subordinatoDAO.doRetrieveByKey(employeeEmail, piva);
				
				if(subordinato != null)
					request.setAttribute("impiegato", subordinato);
				
				else {
					Responsabile responsabile;
					responsabile = responsabileDAO.doRetrieveByKey(employeeEmail, piva);
					request.setAttribute("impiegato", responsabile);
				}
			} catch (SQLException e) {
				System.out.println(e);
			}
			
			request.getRequestDispatcher("/Dipendenti/employeeDashboard.jsp").forward(request, response);
		}

		// Caso in cui l'utente voglia stampare la lista dei dipendenti
		else {
			Collection<Utente> responsabili = null;
			Collection<Utente> subordinati = null;

			try {
				responsabili = utenteDAO.doRetrieveAllResponsabili(piva);
				subordinati = utenteDAO.doRetrieveAllSubordinati(piva);
				
				request.setAttribute("responsabili", responsabili);
				request.setAttribute("subordinati", subordinati);

			} catch (SQLException e) {
				System.out.println(e);
			}

			if (responsabili == null) {
				System.out.println("progettiAttivi = null");
				responsabili = new ArrayList<>();
			}

			if (subordinati == null) {
				System.out.println("progettiConclusi = null");
				subordinati = new ArrayList<>();
			}

			RequestDispatcher dispatcher = request.getRequestDispatcher("/Dipendenti/employeeDashboard.jsp");
			try {
				dispatcher.forward(request, response);
			} catch (ServletException e) {
				System.err.println(e);
			} catch (IOException e) {
				System.err.println(e);
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
