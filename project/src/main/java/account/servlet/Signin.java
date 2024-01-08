package account.servlet;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import account.bean.*;

@WebServlet(name = "Signin", value = "/Signin")
public class Signin extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String nome = request.getParameter("nome");
		String cognome = request.getParameter("cognome");
		String piva = request.getParameter("piva");
		String nome_azienda = request.getParameter("nome_azienda");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String error = "";
		
		Utente utente = new Utente(nome, cognome, email, password);
		Dirigente dirigente = new Dirigente(email, nome_azienda, piva);
		
		// Controllo dati
		if(!utente.isValidInput(nome, cognome, email, password) || !dirigente.isValidInput(piva, nome_azienda)) {
			if (error.equals(""))
				error += "i dati inseriti non rispettano il formato";
			System.out.println("I dati inseriti non rispettano il formato richiesto");
			request.getRequestDispatcher("/Account/signin.jsp").forward(request, response);
		}
		
		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
		UtenteDAO utenteDAO = new UtenteDAO(ds);
		DirigenteDAO dirigenteDAO = new DirigenteDAO(ds);
		
		Dirigente d = null;
		
		// Controlliamo se l'utente è già registrato
		try {
			d = dirigenteDAO.doRetrieveByKey(email);
			if( d != null) {
				if (error.equals(""))
					error += "l'e-mail inserita risulta già registrata";
				System.out.println("L'e-mail inserita risulta già registrata!");
				request.getRequestDispatcher("/Account/signin.jsp").forward(request, response);
				return;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			utenteDAO.doSave(utente);
			dirigenteDAO.doSave(dirigente);
		} catch (SQLException e) {
			System.err.println(e);
			response.sendRedirect(request.getContextPath() + "/Account/signin.jsp");
			return;
		}
		
		response.sendRedirect(request.getContextPath() + "/Account/login.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}