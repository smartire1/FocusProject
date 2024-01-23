package dipendenti.servlet;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import account.bean.*;

@WebServlet(name = "RemoveEmployee", value = "/RemoveEmployee")
public class RemoveEmployee extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
		UtenteDAO utenteDAO = new UtenteDAO(ds);
		
		String emailToRemove = request.getParameter("email");
		
		try {
			utenteDAO.doDelete(emailToRemove);
			System.out.println("Subordinato rimosso con successo!");
			
		} catch (SQLException e) {
			System.out.println("Rimozione utente fallita");
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("/LoadEmployees").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
