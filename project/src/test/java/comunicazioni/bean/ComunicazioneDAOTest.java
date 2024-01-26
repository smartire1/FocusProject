package comunicazioni.bean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ComunicazioneDAOTest {

    private static final String DB_URL = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
    private static final String DB_USER = "sa";
    private static final String DB_PASSWORD = "";

    private ComunicazioneDAO comunicazioneDAO;

    @Mock
    private DataSource dataSource;

    @Mock
    private Connection connection;

    @Mock
    private PreparedStatement preparedStatement;

    @Mock
    private ResultSet resultSet;

    @BeforeEach
    public void setUp() throws SQLException {
        MockitoAnnotations.openMocks(this);

        when(dataSource.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(any(String.class))).thenReturn(preparedStatement);

        comunicazioneDAO = new ComunicazioneDAO(dataSource);

        // Creazione della tabella Comunicazione
        when(connection.createStatement()).thenReturn((Statement) mock(Statement.class));
    }

    @AfterEach
    public void tearDown() throws SQLException {
        // Non è necessario, poiché stiamo usando un database in memoria
    }

    @Test
    public void testDoSave() throws SQLException {
        Comunicazione comunicazione = new Comunicazione(1, "Titolo", "Corpo del messaggio", "mittente@example.com");

        // Salvataggio della comunicazione nel database
        comunicazioneDAO.doSave(comunicazione);

        // Verifica che la comunicazione sia stata salvata correttamente
        verify(preparedStatement).setString(1, "Titolo");
        verify(preparedStatement).setString(2, "Corpo del messaggio");
        verify(preparedStatement).setString(3, "mittente@example.com");
        verify(preparedStatement).executeUpdate();
    }

    @Test
    public void testDoRetrieveAll() throws SQLException {
        // Simulazione del risultato della query
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getInt("id")).thenReturn(1);
        when(resultSet.getString("titolo")).thenReturn("Titolo");
        when(resultSet.getString("corpo")).thenReturn("Corpo del messaggio");
        when(resultSet.getString("mittente_email")).thenReturn("mittente@example.com");

        // Recupero delle comunicazioni relative a un'azienda (nel nostro caso, è fittizio)
        List<Comunicazione> comunicazioni = comunicazioneDAO.doRetrieveAll("idAzienda");

        // Verifica che la lista contenga la comunicazione simulata
        assertEquals(1, comunicazioni.size());
        Comunicazione comunicazione = comunicazioni.get(0);
        assertEquals(1, comunicazione.getId());
        assertEquals("Titolo", comunicazione.getTitolo());
        assertEquals("Corpo del messaggio", comunicazione.getCorpo());
        assertEquals("mittente@example.com", comunicazione.getMittenteEmail());

        // Verifica che la query sia stata eseguita correttamente
        verify(preparedStatement).setString(1, "idAzienda");
        verify(preparedStatement).executeQuery();
    }
}
