package dipendenti.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import account.bean.*;
import progetto.bean.Progetto;
import progetto.bean.ProgettoDAO;

@WebServlet(name = "RemoveEmployee", value = "/RemoveEmployee")
public class RemoveEmployee extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
        HttpSession session = request.getSession();
        String piva = (String) session.getAttribute("idAzienda");
		UtenteDAO utenteDAO = new UtenteDAO(ds);
		
		String emailToRemove = request.getParameter("email");
		ProgettoDAO progettoDAO = new ProgettoDAO(ds);

		
		try {
			Collection<Progetto> progettiResp = progettoDAO.doRetrieveAllByRespAndStato(emailToRemove, piva, false);
			if(progettiResp.isEmpty()) {
				utenteDAO.doDelete(emailToRemove);
				System.out.println("Dipendente rimosso con successo!");
			}
			else {
				String notification = "Impossibile rimuovere il responsabile, progetti ancora attivi";
				request.setAttribute("notification", notification);
				System.out.println("Impossibile rimuovere il responsabile, progetti ancora attivi");
				request.getRequestDispatcher("/LoadProjects").forward(request, response);
				return;
			}
			
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
