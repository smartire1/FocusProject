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

import account.bean.Utente;
import account.bean.UtenteDAO;
import dipendenti.bean.*;


@WebServlet("/AddTurno")
public class AddTurno extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
		HttpSession session = request.getSession();
		String idAzienda = (String) session.getAttribute("idAzienda");
		Utente utente = (Utente) session.getAttribute("utente");

		UtenteDAO utenteDAO = new UtenteDAO(ds);
		
		String dataTurno = request.getParameter("dataTurno");
		String oraInizio = request.getParameter("oraInizio");
		String oraFine = request.getParameter("oraFine");
		String emailUser = request.getParameter("utenteTurno");
		
		try {
			TurnoDAO turnoDAO = new TurnoDAO(ds);
			Turno turno = new Turno(0, dataTurno, oraInizio, oraFine);
			turno = turnoDAO.doSave(turno);
			
			//COntrolli sulle date DA IMPLEMENTARE
			AssegnatoADAO assegnatoaDAO = new AssegnatoADAO(ds);
			assegnatoaDAO.doSave(new AssegnatoA(turno.getId(), emailUser));
			
			System.out.println("Turno Assegnato con successo");
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		request.setAttribute("success", "turno assegnato con successo");
		request.getRequestDispatcher("/LoadEmployees").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
