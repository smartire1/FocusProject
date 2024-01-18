package progetto.servlet;

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
import progetto.bean.*;

@WebServlet("/AddProject")
public class AddProject extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
        DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
		HttpSession session = request.getSession();
		
		// Otteniamo i parametri dal form
		// id: auto generato
        String nomeProgetto = request.getParameter("nome");
        String descrizione = request.getParameter("descrizione");
        String obiettivi = request.getParameter("obiettivi");
        String scadenza = request.getParameter("scadenza");
        // avvisi: nessuno
        Double budget = Double.parseDouble(request.getParameter("budget"));
        // numDipendenti: 0
        String responsabile = request.getParameter("responsabile");
        // stato: false
        String idAzienda = (String) session.getAttribute("idAzienda");

        // Creiamo e salviamo il progetto
        Progetto nuovoProgetto = new Progetto(0, nomeProgetto, descrizione, obiettivi, scadenza, "", budget, 0, responsabile, false, idAzienda);
        ProgettoDAO progettoDAO = new ProgettoDAO(ds);
        UtenteDAO utenteDAO = new UtenteDAO(ds);

        try {
        	Utente u = utenteDAO.doRetrieveByKey(responsabile, idAzienda);
        	if (u == null) {
        		System.out.println("Il subordinato inserito non esiste");
                response.sendRedirect(request.getContextPath() + "/Progetto/projectDashboard.jsp");
                return;
        	}
            progettoDAO.doSave(nuovoProgetto);
            System.out.println("Progetto aggiunto con successo!");
            response.sendRedirect(request.getContextPath() + "/Progetto/projectDashboard.jsp");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Progetto non aggiunto");
            response.sendRedirect(request.getContextPath() + "/Progetto/projectDashboard.jsp");
        }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
