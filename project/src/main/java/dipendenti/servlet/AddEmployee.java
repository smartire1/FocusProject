package dipendenti.servlet;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import account.bean.*;

@WebServlet("/AddEmployee")
public class AddEmployee extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
		UtenteDAO utenteDAO = new UtenteDAO(ds);
		
		HttpSession session = request.getSession();
		String idAzienda = (String) session.getAttribute("idAzienda");
		
		String nome = request.getParameter("nome");
		String cognome = request.getParameter("cognome");
		String email = request.getParameter("email");
		String ruolo = request.getParameter("ruolo");
		
		String password = generatePassword();
		System.out.println("Password generata: " + password);
		
		
		Utente utente = new Utente(email, toHash(password), nome, cognome, idAzienda, true, ruolo);
		
		try { 
			Utente u = utenteDAO.doRetrieveByKey(email, idAzienda);
			
			// Se l'utente fa già parte dell'azienda
			if(u != null) {
				// Se l'utente ha un account disabilitato
				if(!u.isStato()) {
					utenteDAO.doReHire(email);
					System.out.println("Account utente riattivato con successo");
				}
				// Se l'utente ha un account già attivo
				else {
					System.out.println("Si sta tentando di aggiungere un utente già presente!");
					request.setAttribute("notification", "Utente già registrato!");
				}
			}
			
			// Se l'utente non fa parte dell'azienda
			else {
				utenteDAO.doSave(utente);
				request.setAttribute("password", password);
				System.out.println("Utente aggiunto con successo!");
			}
			
		} catch (SQLException e) {
			System.out.println("Aggiunta utente fallita");
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("/LoadEmployees").forward(request, response);
	}
	
    public static String generatePassword() {
        String uppercaseChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowercaseChars = "abcdefghijklmnopqrstuvwxyz";
        String numericChars = "0123456789";
        String specialChars = "@$!%*?&.-_";

        String allChars = uppercaseChars + lowercaseChars + numericChars + specialChars;

        SecureRandom random = new SecureRandom();

        StringBuilder password = new StringBuilder();

        // Assicura che ci sia almeno un carattere da ciascuna categoria
        password.append(uppercaseChars.charAt(random.nextInt(uppercaseChars.length())));
        password.append(lowercaseChars.charAt(random.nextInt(lowercaseChars.length())));
        password.append(numericChars.charAt(random.nextInt(numericChars.length())));
        password.append(specialChars.charAt(random.nextInt(specialChars.length())));

        // Completa la password fino a raggiungere la lunghezza minima (8 caratteri)
        while (password.length() < 8) {
            password.append(allChars.charAt(random.nextInt(allChars.length())));
        }

        // Mescola la password in modo casuale
        char[] passwordArray = password.toString().toCharArray();
        for (int i = passwordArray.length - 1; i > 0; i--) {
            int index = random.nextInt(i + 1);
            char temp = passwordArray[index];
            passwordArray[index] = passwordArray[i];
            passwordArray[i] = temp;
        }

        return new String(passwordArray);
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
