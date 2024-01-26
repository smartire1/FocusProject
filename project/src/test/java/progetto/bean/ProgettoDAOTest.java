package progetto.bean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;        
import java.sql.SQLException;
import java.util.Collection;

import javax.sql.DataSource;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProgettoDAOTest {

    private DataSource dataSource;
    private ProgettoDAO progettoDAO;
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    @BeforeEach
    void setUp() throws SQLException {
        dataSource = mock(DataSource.class);
        progettoDAO = new ProgettoDAO(dataSource);
        connection = mock(Connection.class);
        preparedStatement = mock(PreparedStatement.class);
        resultSet = mock(ResultSet.class);

        // Configura il comportamento del mock per restituire la connessione
        when(dataSource.getConnection()).thenReturn(connection);

        // Configura il comportamento del mock per restituire il PreparedStatement
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);

        // Configura il comportamento del mock per restituire il ResultSet simulato
        when(preparedStatement.executeQuery()).thenReturn(resultSet);

        // Configura il comportamento del mock per restituire il ResultSet simulato anche per il doSave
        when(connection.prepareStatement(anyString(), anyInt())).thenReturn(preparedStatement);
        when(preparedStatement.executeUpdate()).thenReturn(1);
        when(preparedStatement.getGeneratedKeys()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getInt(1)).thenReturn(1);
    }


    @Test
    void testDoSave() throws SQLException {
        // Arrange
    	Progetto progetto = new Progetto(1, "Progetto2", "Descrizione del progetto", "Obiettivi del progetto", "2024-01-31", "Avvisi sul progetto", 10000.0, "responsabile@example.com", true, "Azienda1");
        progetto.setNome("Test Progetto");
        progetto.setDescrizione("Descrizione di test");
        progetto.setObbiettivi("Obiettivi di test");
        progetto.setStato(true);
        progetto.setScadenza("2024-12-31");
        progetto.setBudget(1000.0);
        progetto.setAvvisi("Avvisi di test");
        progetto.setIdAzienda("test_company");
        progetto.setResponsabile_email("test@example.com");

        // Configura il comportamento del mock per restituire un risultato positivo
        when(preparedStatement.executeUpdate()).thenReturn(1);
        when(preparedStatement.getGeneratedKeys()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getInt(1)).thenReturn(1);

        // Act
        int generatedId = progettoDAO.doSave(progetto);

        // Assert
        // Verifica che il metodo executeUpdate sia stato chiamato correttamente
        verify(preparedStatement).setString(1, progetto.getNome());
        verify(preparedStatement).setString(2, progetto.getDescrizione());
        // ... continua con tutte le chiamate a setXXX()
        verify(preparedStatement).executeUpdate();

        // Verifica che il metodo getGeneratedKeys sia stato chiamato correttamente
        verify(preparedStatement).getGeneratedKeys();

        // Verifica che il metodo getInt sia stato chiamato correttamente sull'oggetto ResultSet
        verify(resultSet).getInt(1);

        // Verifica che l'ID generato corrisponda a ciò che ci si aspetta
        assertEquals(1, generatedId);
    }

    @Test
    void testDoDelete() throws SQLException {
        // Arrange
        int idProgettoToDelete = 1;

        // Configura il comportamento del mock per restituire un risultato positivo
        when(preparedStatement.executeUpdate()).thenReturn(1);

        // Act
        progettoDAO.doDelete(idProgettoToDelete);

        // Assert
        // Verifica che il metodo executeUpdate sia stato chiamato correttamente
        verify(preparedStatement).setInt(1, idProgettoToDelete);
        verify(preparedStatement).executeUpdate();
    }
    
    @Test
    void testDoUpdate() throws SQLException {
        // Crea un oggetto Progetto per il test
        Progetto progetto = new Progetto(1, "Progetto2", "Descrizione del progetto", "Obiettivi del progetto",
                "2024-01-31", "Avvisi sul progetto", 10000.0, "responsabile@example.com", true, "Azienda1");

        // Esegui il metodo doUpdate() e verifica che non ci siano eccezioni
        assertDoesNotThrow(() -> progettoDAO.doUpdate(progetto));

        // Verifica che i metodi appropriati siano stati chiamati
        verify(connection, times(1)).prepareStatement(anyString());
        // ... Verifica i set dei parametri del PreparedStatement ...
        verify(preparedStatement, times(1)).executeUpdate();
    }
    
    @Test
    void testDoRetrieveByKey() throws SQLException {
        // Configura il comportamento del mock per restituire un ResultSet simulato
        when(resultSet.next()).thenReturn(true, false);
        when(resultSet.getInt("id_progetto")).thenReturn(1);
        // ... Configura il resto dei dettagli del ResultSet ...

        // Esegui il metodo doRetrieveByKey()
        Progetto result = progettoDAO.doRetrieveByKey(1, "idAzienda");

        // Verifica che il metodo appropriato sia stato chiamato
        verify(connection, times(1)).prepareStatement(anyString());
        verify(preparedStatement, times(1)).setInt(1, 1);
        verify(preparedStatement, times(1)).setString(2, "idAzienda");
        verify(preparedStatement, times(1)).executeQuery();

        // Verifica il risultato
        assertNotNull(result);
        // ... Verifica i dettagli del risultato ...
    }

    @Test
    void testDoRetrieveAllByStato() throws SQLException {
        // Configura il comportamento del mock per restituire un ResultSet simulato
        when(resultSet.next()).thenReturn(true, false);
        // ... Configura il resto dei dettagli del ResultSet ...

        // Esegui il metodo doRetrieveAllByStato()
        Collection<Progetto> result = progettoDAO.doRetrieveAllByStato("idAzienda", true);

        // Verifica che il metodo appropriato sia stato chiamato
        verify(connection, times(1)).prepareStatement(anyString());
        verify(preparedStatement, times(1)).setBoolean(1, true);
        verify(preparedStatement, times(1)).setString(2, "idAzienda");
        verify(preparedStatement, times(1)).executeQuery();

        // Verifica il risultato
        assertEquals(1, result.size());
        // ... Verifica i dettagli del risultato ...
    }
    
    @Test
    void testDoRetrieveAllByRespAndStato() throws SQLException {
        // Configura il comportamento del mock per restituire un ResultSet simulato
        when(resultSet.next()).thenReturn(true, false);
        // ... Configura il resto dei dettagli del ResultSet ...

        // Esegui il metodo doRetrieveAllByRespAndStato()
        Collection<Progetto> result = progettoDAO.doRetrieveAllByRespAndStato("responsabileEmail", "idAzienda", true);

        // Verifica che il metodo appropriato sia stato chiamato
        verify(connection, times(1)).prepareStatement(anyString());
        verify(preparedStatement, times(1)).setString(1, "responsabileEmail");
        verify(preparedStatement, times(1)).setString(2, "idAzienda");
        verify(preparedStatement, times(1)).setBoolean(3, true);
        verify(preparedStatement, times(1)).executeQuery();

        // Verifica il risultato
        assertEquals(1, result.size());
        // ... Verifica i dettagli del risultato ...
    }

    @Test
    void testDoRetrieveAllBySubAndStato() throws SQLException {
        // Configura il comportamento del mock per restituire un ResultSet simulato
        when(resultSet.next()).thenReturn(true, false);
        // ... Configura il resto dei dettagli del ResultSet ...

        // Esegui il metodo doRetrieveAllBySubAndStato()
        Collection<Progetto> result = progettoDAO.doRetrieveAllBySubAndStato("subordinatoEmail", "idAzienda", true);

        // Verifica che il metodo appropriato sia stato chiamato
        verify(connection, times(1)).prepareStatement(anyString());
        verify(preparedStatement, times(1)).setString(1, "subordinatoEmail");
        verify(preparedStatement, times(1)).setBoolean(2, true);
        verify(preparedStatement, times(1)).setString(3, "idAzienda");
        verify(preparedStatement, times(1)).executeQuery();

        // Verifica il risultato
        assertEquals(1, result.size());
        // ... Verifica i dettagli del risultato ...
    }   
}
