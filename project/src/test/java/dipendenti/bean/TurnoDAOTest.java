package dipendenti.bean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import javax.sql.DataSource;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TurnoDAOTest {

    private DataSource mockDataSource;
    private Connection mockConnection;
    private PreparedStatement mockPreparedStatement;
    private ResultSet mockResultSet;

    @BeforeEach
    public void setUp() throws SQLException {
        mockDataSource = mock(DataSource.class);
        mockConnection = mock(Connection.class);
        mockPreparedStatement = mock(PreparedStatement.class);
        mockResultSet = mock(ResultSet.class);

        when(mockDataSource.getConnection()).thenReturn(mockConnection);
    }

    @Test
    public void testDoSave() throws SQLException {
        // Crea un oggetto TurnoDAO con il mock DataSource
        TurnoDAO turnoDAO = new TurnoDAO(mockDataSource);

        // Crea un oggetto Turno da salvare
        Turno turno = new Turno(1, "Lunedì", "09:00", "17:00");

        // Configura il mock PreparedStatement per il metodo doSave
        when(mockConnection.prepareStatement(any(String.class), eq(PreparedStatement.RETURN_GENERATED_KEYS)))
            .thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeUpdate()).thenReturn(1);
        when(mockPreparedStatement.getGeneratedKeys()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getInt(1)).thenReturn(1);

        // Esegui il metodo doSave
        Turno resultTurno = turnoDAO.doSave(turno);

        // Verifica che il risultato sia corretto
        assertEquals(1, resultTurno.getId());
    }

    @Test
    public void testDoDelete() throws SQLException {
        // Crea un oggetto TurnoDAO con il mock DataSource
        TurnoDAO turnoDAO = new TurnoDAO(mockDataSource);

        // Configura il mock PreparedStatement per il metodo doDelete
        when(mockConnection.prepareStatement(any(String.class))).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeUpdate()).thenReturn(1);

        // Esegui il metodo doDelete
        turnoDAO.doDelete(1);

        // Verifica che il metodo sia stato chiamato correttamente
        verify(mockPreparedStatement).setInt(1, 1);
        verify(mockPreparedStatement).executeUpdate();
    }

    @Test
    public void testDoRetrieveAllByUser() throws SQLException {
        // Crea un oggetto TurnoDAO con il mock DataSource
        TurnoDAO turnoDAO = new TurnoDAO(mockDataSource);

        // Configura il mock PreparedStatement per il metodo doRetrieveAllByUser
        when(mockConnection.prepareStatement(any(String.class))).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);

        // Configura il mock ResultSet per restituire risultati
        when(mockResultSet.next()).thenReturn(true, false);
        when(mockResultSet.getInt("id")).thenReturn(1);
        when(mockResultSet.getString("giorno")).thenReturn("Lunedì");
        when(mockResultSet.getString("ora_inizio")).thenReturn("09:00");
        when(mockResultSet.getString("ora_fine")).thenReturn("17:00");

        // Esegui il metodo doRetrieveAllByUser
        Collection<Turno> resultTurni = turnoDAO.doRetrieveAllByUser("piva123", "utente@example.com");

        // Verifica che il risultato sia corretto
        assertEquals(1, resultTurni.size());
        Turno resultTurno = resultTurni.iterator().next();
        assertEquals(1, resultTurno.getId());
        assertEquals("Lunedì", resultTurno.getGiorno());
        assertEquals("09:00", resultTurno.getOraInizio());
        assertEquals("17:00", resultTurno.getOraFine());
    }

    @Test
    public void testDoRetrieveAllResp() throws SQLException {
        // Crea un oggetto TurnoDAO con il mock DataSource
        TurnoDAO turnoDAO = new TurnoDAO(mockDataSource);

        // Configura il mock PreparedStatement per il metodo doRetrieveAllResp
        when(mockConnection.prepareStatement(any(String.class))).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);

        when(mockResultSet.next()).thenReturn(true, false);
        when(mockResultSet.getInt("id")).thenReturn(1);
        when(mockResultSet.getString("giorno")).thenReturn("Lunedì");
        when(mockResultSet.getString("ora_inizio")).thenReturn("09:00");
        when(mockResultSet.getString("ora_fine")).thenReturn("17:00");

        Collection<Turno> resultTurni = turnoDAO.doRetrieveAllResp("piva123");

        assertEquals(1, resultTurni.size());
        Turno resultTurno = resultTurni.iterator().next();
        assertEquals(1, resultTurno.getId());
        assertEquals("Lunedì", resultTurno.getGiorno());
        assertEquals("09:00", resultTurno.getOraInizio());
        assertEquals("17:00", resultTurno.getOraFine());
    }
}
