package account.servlet;

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

@WebServlet(name = "Login", value = "/Login")
public class Login extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Controllo se l'utente è già autenticato
		HttpSession session = request.getSession();
		Utente utente = (Utente) session.getAttribute("utente");

		if (utente != null) {
			System.out.println("L'utente risulta già autenticato");
			response.sendRedirect(request.getContextPath() + "/homePage.jsp");
			return;
		}

		// Se l'utente non è già autenticato, allora si procede alla verifica delle
		// credenziali
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String error = "";

		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");

		try {
			// ricerchiamo l'utente nel db
			UtenteDAO utenteDAO = new UtenteDAO(ds);
			utente = utenteDAO.doRetrieveByKey(email);
			
			// Salviamo la piva per identificare l'azienda presso cui lavora l'utente
			session.setAttribute("idAzienda", utente.getIdAzienda());

			// se l'e-mail corrisponde
			if (utente.getEmail().equals(email)) {
				// se la password corrisponde
				if (utente.getPassword().equals(password)) {
					if(!utente.isStato()) {
						System.out.println("Stato disattivo");
						error += "Il tuo Account non è attivo";
						request.setAttribute("error", error);
						request.getRequestDispatcher("/Account/login.jsp").forward(request, response);
						return;
					}
					System.out.println("Log in effettuato con successo");
					session.setAttribute("utente", utente);
					response.sendRedirect(request.getContextPath() + "/homePage.jsp");
				}
				// se la password non corrisponde
				else {
					if (error.equals(""))
						error += "La Password non corrisponde";

					System.out.println("La Password non corrisponde");

					// temp
					System.out.println("Password corretta: " + utente.getPassword());
					System.out.println(password);
					/*
					 * 
					 * 
					 */

					request.setAttribute("error", error);
					request.getRequestDispatcher("/Account/login.jsp").forward(request, response);
				}
				// se l'e-mail non corrisponde
			} else {
				if (error.equals(""))
					error += "Account inesistente";

				System.out.println("Account inesistente");
				request.setAttribute("error", error);
				request.getRequestDispatcher("/Account/login.jsp").forward(request, response);
			}
		} catch (SQLException e) {
			System.err.println(e);
			if (error.equals("")) {
				error += "Login fallito";
			}
			System.out.println("Login fallito");
			request.setAttribute("error", error);
			request.getRequestDispatcher("/Account/login.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}