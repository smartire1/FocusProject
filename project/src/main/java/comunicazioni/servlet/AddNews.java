package comunicazioni;

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
		// TODO Auto-generated method stub
	
		HttpSession session = request.getSession();
		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
		Comunicazione com1;
		ComunicazioneDAO comDao = new ComunicazioneDAO(ds);
		String titolo = request.getParameter("titolo");
		String testo = request.getParameter("testo");
		Utente dirigente = (Utente) session.getAttribute("utente");
		
		com1 = new Comunicazione(0, titolo, testo, dirigente.getEmail());
		
		try{
			comDao.doSave(com1);
			System.out.println("Erroe 1");
			response.sendRedirect(request.getContextPath() + "/Comunicazioni/communicationDashbord.jsp");
		}
		catch(SQLException e){
			e.printStackTrace();
			System.out.println("Erroe 2");
			response.sendRedirect(request.getContextPath() + "/Comunicazioni/communicationDashbord.jsp");
			 
		}
	}
	

	DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
