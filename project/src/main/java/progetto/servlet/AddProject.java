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
        String avvisi = request.getParameter("avvisi");
        String budgetParameter = request.getParameter("budget");
        Double budget = (budgetParameter != null && !budgetParameter.isEmpty()) ? Double.parseDouble(budgetParameter) : 0.0; 
        // numDipendenti: 0
        String responsabile = request.getParameter("responsabile");
        // stato: false
        String idAzienda = (String) session.getAttribute("idAzienda");
        String op = request.getParameter("action");            
        
        // Creiamo e salviamo il progetto
        Progetto nuovoProgetto = new Progetto(0, nomeProgetto, descrizione, obiettivi, scadenza, avvisi, budget, responsabile, false, idAzienda);
        ProgettoDAO progettoDAO = new ProgettoDAO(ds);
        UtenteDAO utenteDAO = new UtenteDAO(ds);      
        
        if(op.equals("edit")) {
        	int idProject = Integer.parseInt(request.getParameter("idProject"));
        	nuovoProgetto.setIdProgetto(idProject);
        	try {
				progettoDAO.doUpdate(nuovoProgetto);
	            System.out.println("Progetto Modificato con successo!");
				request.setAttribute("id", String.valueOf(idProject));
	            request.getRequestDispatcher("/LoadProjects").forward(request, response);				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        
        else if(op.equals("add")){
	        try {
	        	Utente u = utenteDAO.doRetrieveByKey(responsabile, idAzienda);
	        	if (u == null) {
	        		System.out.println("Il Responsabile inserito non esiste");
	                response.sendRedirect(request.getContextPath() + "/Progetto/projectDashboard.jsp");
	                return;
	        	}
	        	if (nuovoProgetto.getAvvisi() == null) 
	        		nuovoProgetto.setAvvisi("");
	            int idprogetto = progettoDAO.doSave(nuovoProgetto);
	            System.out.println("Progetto aggiunto con successo!");
	            request.setAttribute("id", String.valueOf(idprogetto));
	            request.getRequestDispatcher("/LoadProjects").forward(request, response);
	
	        } catch (SQLException e) {
	            e.printStackTrace();
	            System.out.println("Progetto non aggiunto");
	            response.sendRedirect(request.getContextPath() + "/Progetto/projectDashboard.jsp");
	        }
        }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
