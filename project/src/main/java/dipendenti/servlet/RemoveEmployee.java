package dipendenti.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import account.bean.*;

@WebServlet(name = "RemoveEmployee", value = "/RemoveEmployee")
public class RemoveEmployee extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
		UtenteDAO utenteDAO = new UtenteDAO(ds);
		SubordinatoDAO subordinatoDAO = new SubordinatoDAO(ds);
		ResponsabileDAO responsabileDAO = new ResponsabileDAO(ds);
		
		HttpSession session = request.getSession();
		String piva = (String) session.getAttribute("piva");
		
		String emailToRemove = request.getParameter("email");
		
		try {
			if(subordinatoDAO.doRetrieveByKey(emailToRemove, piva) != null) {
				subordinatoDAO.doDelete(emailToRemove);
				System.out.println("Subordinato rimosso con successo!");
			}
			else {
				responsabileDAO.doDelete(emailToRemove);
				System.out.println("Responsabile rimosso con successo!");
			}
			
			utenteDAO.doDelete(emailToRemove);
			
		} catch (SQLException e) {
			System.out.println("Rimozione utente fallita");
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("/Dipendenti/employeeDashboard.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
