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
		UtenteDAO utenteDAO = new UtenteDAO(ds);
		
		HttpSession session = request.getSession();
		String idAzienda = (String) session.getAttribute("idAzienda");
		
			Collection<Utente> responsabili = null;
			Collection<Utente> subordinati = null;
			System.out.println(idAzienda);
			try {
				responsabili = utenteDAO.doRetrieveAllResponsabili(idAzienda);
				subordinati = utenteDAO.doRetrieveAllSubordinati(idAzienda);								
				
				request.setAttribute("responsabili", responsabili);
				request.setAttribute("subordinati", subordinati);				
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("/Dipendenti/employeeDashboard.jsp");
				try {
					dispatcher.forward(request, response);
				} catch (ServletException e) {
					System.err.println(e);
				} catch (IOException e) {
					System.err.println(e);
				}				
			} catch (SQLException e) {
				System.out.println(e);
			}
			
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
