package dipendenti.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import dipendenti.bean.*;

@WebServlet("/RemoveTurno")
public class RemoveTurno extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
		
		Turno turno = (Turno) request.getAttribute("turno");
		String email = (String) request.getAttribute("email");
		
		TurnoDAO turnoDAO = new TurnoDAO(ds);
		AssegnatoADAO assegnatoADAO = new AssegnatoADAO(ds);
		
		try {
			assegnatoADAO.doDelete(email);
			turnoDAO.doDelete(turno.getId());
			
			System.out.println("Rimozione turno avvenuta con successo!");
		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println("Rimozione turno fallita");
		}
		
		request.getRequestDispatcher("/LoadEmployees").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
