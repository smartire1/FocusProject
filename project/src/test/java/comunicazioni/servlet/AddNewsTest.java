package comunicazioni.servlet;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import account.bean.Utente;
import comunicazioni.bean.Comunicazione;
import comunicazioni.bean.ComunicazioneDAO;
import comunicazioni.servlet.AddNews;

class AddNewsTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private HttpSession session;

    @Mock
    private ServletContext servletContext;

    @Mock
    private DataSource dataSource;

    @Mock
    private ComunicazioneDAO comDao;

    @Mock
    private Utente dirigente;

    private AddNews servlet;

    @BeforeEach
    void setUp() throws ServletException, SQLException {
        MockitoAnnotations.openMocks(this);

        // Inizializza la servlet manualmente
        servlet = new AddNews();
        
        // Crea un mock di ServletConfig
        ServletConfig servletConfig = mock(ServletConfig.class);
        
        // Inizializza la servlet con il mock di ServletConfig
        servlet.init(servletConfig);

        // Mocking per il resto del codice
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("utente")).thenReturn(dirigente);
        
        // Creazione di un mock di DataSource
        DataSource mockDataSource = mock(DataSource.class);
        when(request.getServletContext()).thenReturn(servletContext);
        when(servletContext.getAttribute("DataSource")).thenReturn(mockDataSource);
        
        // Simulazione del lancio di SQLException
        doThrow(new SQLException("Errore simulato")).when(mockDataSource).getConnection();
        
        when(dirigente.getRuolo()).thenReturn("dirigente");
    }

    @Test
    void testDoGet() throws ServletException, IOException, SQLException {
        when(request.getParameter("titolo")).thenReturn("Titolo");
        when(request.getParameter("testo")).thenReturn("Corpo del messaggio");
        when(session.getAttribute("utente")).thenReturn(dirigente);

        servlet.doGet(request, response);

        // Verifica che la servlet reindirizzi correttamente
        verify(response).sendRedirect(request.getContextPath() + "/LoadData");

        // Verifica che il metodo doSave del DAO sia stato chiamato
        verify(comDao).doSave(any(Comunicazione.class));
    }

    @Test
    void testDoGetNoPermission() throws ServletException, IOException, SQLException {
        when(dirigente.getRuolo()).thenReturn("altroRuolo"); // Ruolo diverso da dirigente
        servlet.doGet(request, response);

        // Verifica che la servlet reindirizzi correttamente
        verify(response).sendRedirect(request.getContextPath() + "/LoadNews");

        // Verifica che il metodo doSave del DAO non sia stato chiamato
        verify(comDao, never()).doSave(any(Comunicazione.class));
    }

    // Puoi aggiungere altri test se necessario

}
