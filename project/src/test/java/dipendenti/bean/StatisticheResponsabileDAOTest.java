package dipendenti.bean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StatisticheResponsabileDAOTest {

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
    public void testGetStatisticheResponsabile() throws SQLException {
        // Crea un oggetto StatisticheResponsabileDAO con il mock DataSource
        StatisticheResponsabileDAO dao = new StatisticheResponsabileDAO(mockDataSource);

        // Configura il mock PreparedStatement e il mock ResultSet per il metodo getStatisticheResponsabile
        when(mockConnection.prepareStatement(any(String.class))).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);

        // Configura il mock ResultSet per restituire risultati
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getInt("num_progetti_completati")).thenReturn(3);
        when(mockResultSet.getInt("num_progetti_in_corso")).thenReturn(2);
        when(mockResultSet.getInt("num_permessi_richiesti")).thenReturn(5);
        when(mockResultSet.getInt("num_subordinati_gestiti")).thenReturn(7);

        // Esegui il metodo getStatisticheResponsabile
        StatisticheResponsabile result = dao.getStatisticheResponsabile("responsabile@example.com");

        // Verifica che il risultato sia corretto
        assertEquals("responsabile@example.com", result.getEmail());
        assertEquals(3, result.getNum_progetti_completati());
        assertEquals(2, result.getNum_progetti_in_corso());
        assertEquals(5, result.getNum_permessi_richiesti());
        assertEquals(7, result.getNum_subordinati_gestiti());
    }
}
