package comunicazioni.servlet;

import java.io.IOException;
import java.sql.SQLException;
import comunicazioni.bean.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import account.bean.Utente;


@WebServlet("/AddNews")
public class AddNews extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
		Comunicazione com;
		ComunicazioneDAO comDao = new ComunicazioneDAO(ds);
		String titolo = request.getParameter("titolo");
		String testo = request.getParameter("testo");
		Utente dirigente = (Utente) session.getAttribute("utente");
		
		// Se l'utente non è il dirigente, allora OUTTTT!!
		if(!dirigente.getRuolo().equals("dirigente") && !dirigente.getRuolo().equals("responsabile")) {
			System.out.println("Non hai i permessi per effettuare una comunicazione!");
			response.sendRedirect(request.getContextPath() + "/LoadNews");
		}
		
		com = new Comunicazione(0, titolo, testo, dirigente.getEmail());
		
		try{
			comDao.doSave(com);
			System.out.println("Comunicazione effettuata con successo!");
		}
		catch(SQLException e){
			e.printStackTrace();
			System.out.println("Comunicazione fallita");
		}

		response.sendRedirect(request.getContextPath() + "/LoadNews");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
