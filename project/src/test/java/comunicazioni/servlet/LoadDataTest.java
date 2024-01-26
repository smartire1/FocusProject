package comunicazioni.servlet;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
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

import comunicazioni.bean.Comunicazione;
import comunicazioni.bean.ComunicazioneDAO;
import comunicazioni.bean.Permesso;
import comunicazioni.bean.PermessoDAO;

class LoadDataTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private HttpSession session;

    @Mock
    private DataSource dataSource;

    @Mock
    private ComunicazioneDAO comunicazioneDAO;

    @Mock
    private PermessoDAO permessoDAO;

    @Mock
    private RequestDispatcher requestDispatcher;

    private LoadData servlet;

    @BeforeEach
    void setUp() throws ServletException {
        MockitoAnnotations.openMocks(this);
        servlet = new LoadData();

        // Inizializza il servlet config (puoi utilizzare Mockito per simulare il ServletConfig)
        ServletConfig servletConfig = mock(ServletConfig.class);
        when(servletConfig.getServletContext()).thenReturn((ServletContext) mock(ServletContext.class));
        servlet.init(servletConfig);

        when(request.getSession()).thenReturn(session);
        when(request.getRequestDispatcher("/Comunicazioni/communicationDashboard.jsp")).thenReturn(requestDispatcher);
        when(session.getAttribute("idAzienda")).thenReturn("piva");
        when(request.getAttribute("notification")).thenReturn(null);
    }


    @Test
    void testDoGet() throws ServletException, IOException, SQLException {
        List<Comunicazione> mockComunicazioni = new ArrayList<>();
        List<Permesso> mockPermessi = new ArrayList<>();

        when(comunicazioneDAO.doRetrieveAll("piva")).thenReturn(mockComunicazioni);
        when(permessoDAO.doRetrieveAllNotManagedAndByRuolo("piva", "responsabile")).thenReturn(mockPermessi);
        when(permessoDAO.doRetrieveAllManagedAndByRuolo("piva", "responsabile")).thenReturn(mockPermessi);
        when(permessoDAO.doRetrieveAllNotManagedAndByRuolo("piva", "subordinato")).thenReturn(mockPermessi);
        when(permessoDAO.doRetrieveAllManagedAndByRuolo("piva", "subordinato")).thenReturn(mockPermessi);
        when(permessoDAO.doRetrieveAllByUser("piva", "utente@example.com")).thenReturn(mockPermessi);

        when(request.getAttribute("news")).thenReturn(mockComunicazioni);
        when(request.getAttribute("permessiResponsabili")).thenReturn(mockPermessi);
        when(request.getAttribute("permessiRespGestiti")).thenReturn(mockPermessi);
        when(request.getAttribute("permessiSubordinati")).thenReturn(mockPermessi);
        when(request.getAttribute("permessiSubGestiti")).thenReturn(mockPermessi);
        when(request.getAttribute("permessiRichiesti")).thenReturn(mockPermessi);

        servlet.doGet(request, response);

        // Verifica che la servlet abbia impostato gli attributi correttamente
        verify(request).setAttribute("news", mockComunicazioni);
        verify(request).setAttribute("permessiResponsabili", mockPermessi);
        verify(request).setAttribute("permessiRespGestiti", mockPermessi);
        verify(request).setAttribute("permessiSubordinati", mockPermessi);
        verify(request).setAttribute("permessiSubGestiti", mockPermessi);
        verify(request).setAttribute("permessiRichiesti", mockPermessi);

        // Verifica che la servlet abbia fatto il dispatch alla giusta pagina
        verify(requestDispatcher).forward(request, response);
    }
}
