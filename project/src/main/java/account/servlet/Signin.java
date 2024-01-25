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
import javax.sql.DataSource;
import account.bean.*;

@WebServlet(name = "Signin", value = "/Signin")
public class Signin extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
        String notification = "";

        String nome = request.getParameter("nome");
        String cognome = request.getParameter("cognome");
        String piva = request.getParameter("piva");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String nome_azienda = request.getParameter("nome_azienda");

        // Aggiungi hashing della password
        String hashedPassword = toHash(password);

        System.out.println("Hashed Password: " + hashedPassword);

        Utente utente = new Utente(email, hashedPassword, nome, cognome, piva, true, "dirigente");
        Azienda azienda = new Azienda(piva, nome_azienda);

        // Controllo formato dati
        if (!utente.isValidInput(nome, cognome, email, password) || !azienda.isValidInput(piva, nome_azienda)) {
            notification = "I dati inseriti non rispettano il formato";
            System.out.println("I dati inseriti non rispettano il formato");
            request.getRequestDispatcher("/Account/signin.jsp").forward(request, response);
            return;
        }

        UtenteDAO utenteDAO = new UtenteDAO(ds);
        AziendaDAO aziendaDAO = new AziendaDAO(ds);

        // Controlliamo se l'utente è già registrato
        try {
            Azienda a = aziendaDAO.doRetrieveByKey(piva);
            if (a != null) {
                notification = "l'azienda inserita risulta già registrata";
                System.out.println("l'azienda inserita risulta già registrata");
                request.getRequestDispatcher("/Account/signin.jsp").forward(request, response);
                return;
            }

            Utente d = utenteDAO.doRetrieveByKey(email, piva);
            if (d != null) {
                notification = "l'e-mail inserita risulta già registrata";
                System.out.println("L'e-mail inserita risulta già registrata!");
                request.getRequestDispatcher("/Account/signin.jsp").forward(request, response);
                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Registriamo l'utente e l'azienda
        try {
            aziendaDAO.doSave(azienda);
            utenteDAO.doSave(utente);

            notification = "Azienda registrata con successo!";

        } catch (SQLException e) {
            System.err.println(e);
            request.getRequestDispatcher("/Account/signin.jsp").forward(request, response);
            return;
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
