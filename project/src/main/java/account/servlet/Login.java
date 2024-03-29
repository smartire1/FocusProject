package account.servlet;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.MessageDigest;
import java.util.Base64;
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		Utente utente = (Utente) session.getAttribute("utente");

		String notification = "";

		// ERROR: Se l'utente è già autenticato!
		if (utente != null) {
			System.out.println("L'utente risulta già autenticato");
			request.getRequestDispatcher("/homePage.jsp").forward(request, response);
			return;
		}

		// Se l'utente non è già autenticato, allora si procede alla verifica delle credenziali
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");

		try {
			// ricerchiamo l'utente nel db
			UtenteDAO utenteDAO = new UtenteDAO(ds);
			utente = utenteDAO.doRetrieveByKey(email);

			// Se l'e-mail corrisponde
			if (utente != null && utente.getEmail().equals(email)) {
				String storedHashedPassword = utente.getPassword();
				String enteredHashedPassword = toHash(password);
				// Se la password corrisponde
				if (enteredHashedPassword.equals(storedHashedPassword)) {
					// Se l'account è attivo
					if (utente.isStato()) {
						System.out.println("Log in effettuato con successo");

						session.setAttribute("utente", utente);
						session.setAttribute("idAzienda", utente.getIdAzienda());

						request.getRequestDispatcher("/homePage.jsp").forward(request, response);
						return;
					}

					// ERROR: l'account non è attivo
					else {
						notification = "L'Account non è attivo";
						System.out.println("L'Account non è attivo");
					}
				}

				// ERROR: la password non corrisponde
				else {
					notification = "La Password non corrisponde";
					System.out.println("La Password non corrisponde");
				}

			// ERROR: se l'e-mail non corrisponde
			} else {
				notification = "Account inesistente";
				System.out.println("Account inesistente");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		request.setAttribute("notification", notification);
		request.getRequestDispatcher("/Account/login.jsp").forward(request, response);
	}

	public static String toHash(String password) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));
			return Base64.getEncoder().encodeToString(hashedPassword);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
