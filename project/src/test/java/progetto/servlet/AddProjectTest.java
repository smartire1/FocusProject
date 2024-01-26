package progetto.servlet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import progetto.bean.ProgettoDAO;
import account.bean.UtenteDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AddProjectTest {

    @InjectMocks
    private AddProject servlet;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private HttpSession session;

    @Mock
    private DataSource mockDataSource;

    @Mock
    private ProgettoDAO mockProgettoDAO;

    @Mock
    private UtenteDAO mockUtenteDAO;

    @Mock
    private RequestDispatcher mockRequestDispatcher;

    @Mock
    private ServletContext mockServletContext;

    @BeforeEach
    public void setUp() {
        // Mock ServletConfig and initialize the servlet
        ServletConfig servletConfig = mock(ServletConfig.class);
        when(servletConfig.getServletContext()).thenReturn(mockServletContext);
        when(request.getParameter(anyString())).thenReturn("Progetto Test", "Descrizione di test", "Obiettivi di test", "Scadenza di test", "Avvisi di test", "1000.0", "Responsabile di test", "1");
        when(request.getParameter("action")).thenReturn("add");
        try {
            servlet.init(servletConfig);
        } catch (ServletException e) {
            e.printStackTrace();
        }

        // Inizializza la richiesta HTTP
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        when(request.getSession()).thenReturn(session);

        // Inizializza il campo progettoDAO
        setPrivateField(servlet, "progettoDAO", mockProgettoDAO);

        // Inizializza il campo utenteDAO
        setPrivateField(servlet, "utenteDAO", mockUtenteDAO);

        // Configura il mockDataSource
        mockDataSource = mock(DataSource.class);

        // Imposta il DataSource nei campi appropriati
        setPrivateField(servlet, "ds", mockDataSource);
        setPrivateField(mockProgettoDAO, "ds", mockDataSource);
        setPrivateField(mockUtenteDAO, "ds", mockDataSource);
    }

    @Test
    void testDoGetAddProject() throws ServletException, IOException, SQLException {
        // Configura il comportamento del mock per simulare una richiesta di aggiunta di progetto
        when(request.getParameter("action")).thenReturn("add");
        when(request.getParameter("nome")).thenReturn("Progetto Test");
        when(request.getParameter("descrizione")).thenReturn("Descrizione di test");
        when(request.getParameter("obiettivi")).thenReturn("Obiettivi di test");
        when(request.getParameter("scadenza")).thenReturn("Scadenza di test");  // Aggiungi questa riga
        // ... Configura il resto dei parametri ...

        // Configura il comportamento del mock per simulare una risposta positiva dalla doSave del DAO
        when(mockProgettoDAO.doSave(any())).thenReturn(1);

        // Simula la chiamata alla servlet
        servlet.doGet(request, response);

        // Verifica che il metodo doSave sia stato chiamato correttamente
        verify(mockProgettoDAO, times(1)).doSave(any());

        // Verifica che la servlet abbia inoltrato la richiesta alla destinazione corretta
        verify(request, times(1)).getRequestDispatcher("/LoadProjects");

        // Verifica che il dispatcher sia stato chiamato con il metodo forward
        verify(mockRequestDispatcher, times(1)).forward(request, response);
    }




    // Metodo per impostare un campo privato utilizzando la riflessione
    private void setPrivateField(Object target, String fieldName, Object value) {
        try {
            Field field = target.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(target, value);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}