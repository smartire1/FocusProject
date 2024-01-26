package account.bean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.*;

class UtenteDAOTest {

    private UtenteDAO utenteDAO;
    private DataSource mockDataSource;

    @BeforeEach
    void setUp() {
        mockDataSource = Mockito.mock(DataSource.class);
        utenteDAO = new UtenteDAO(mockDataSource);
    }

    @Test
    void testDoSave() throws SQLException {
        // Creare un utente di prova
        Utente utente = new Utente("test@email.com", "password", "Nome", "Cognome", "azienda123", true, "ruolo");

        // Mockare la connessione, il prepared statement e l'esecuzione dell'update
        Connection mockConnection = Mockito.mock(Connection.class);
        PreparedStatement mockPreparedStatement = Mockito.mock(PreparedStatement.class);

        when(mockDataSource.getConnection()).thenReturn(mockConnection);
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);

        // Eseguire il metodo doSave
        assertDoesNotThrow(() -> utenteDAO.doSave(utente));

        // Verificare che il prepared statement sia stato chiamato con i parametri corretti
        verify(mockPreparedStatement).setString(1, utente.getEmail());
        verify(mockPreparedStatement).setString(2, utente.getPassword());
        verify(mockPreparedStatement).setString(3, utente.getNome());
        verify(mockPreparedStatement).setString(4, utente.getCognome());
        verify(mockPreparedStatement).setString(5, utente.getIdAzienda());
        verify(mockPreparedStatement).setString(6, utente.getRuolo());

        // Verificare che executeUpdate sia stato chiamato
        verify(mockPreparedStatement).executeUpdate();

        // Verificare che le connessioni siano state chiuse
        verify(mockPreparedStatement).close();
        verify(mockConnection).close();
    }
    
    @Test
    void testDoDelete() throws SQLException {
        // Mockare la connessione, il prepared statement e l'esecuzione dell'update
        Connection mockConnection = Mockito.mock(Connection.class);
        PreparedStatement mockPreparedStatement = Mockito.mock(PreparedStatement.class);

        when(mockDataSource.getConnection()).thenReturn(mockConnection);
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);

        // Eseguire il metodo doDelete
        assertDoesNotThrow(() -> utenteDAO.doDelete("test@email.com"));

        // Verificare che il prepared statement sia stato chiamato con il parametro corretto
        verify(mockPreparedStatement).setString(1, "test@email.com");

        // Verificare che executeUpdate sia stato chiamato
        verify(mockPreparedStatement).executeUpdate();

        // Verificare che le connessioni siano state chiuse
        verify(mockPreparedStatement).close();
        verify(mockConnection).close();
    }

    @Test
    void testDoReHire() throws SQLException {
        // Mockare la connessione, il prepared statement e l'esecuzione dell'update
        Connection mockConnection = Mockito.mock(Connection.class);
        PreparedStatement mockPreparedStatement = Mockito.mock(PreparedStatement.class);

        when(mockDataSource.getConnection()).thenReturn(mockConnection);
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);

        // Eseguire il metodo doReHire
        assertDoesNotThrow(() -> utenteDAO.doReHire("test@email.com"));

        // Verificare che il prepared statement sia stato chiamato con il parametro corretto
        verify(mockPreparedStatement).setString(1, "test@email.com");

        // Verificare che executeUpdate sia stato chiamato
        verify(mockPreparedStatement).executeUpdate();

        // Verificare che le connessioni siano state chiuse
        verify(mockPreparedStatement).close();
        verify(mockConnection).close();
    }

    @Test
    void testDoUpdateCredentials() throws SQLException {
        // Creare un utente di prova
        Utente utente = new Utente("test@email.com", "password", "Nome", "Cognome", "azienda123", true, "ruolo");

        // Mockare la connessione, il prepared statement e l'esecuzione dell'update
        Connection mockConnection = Mockito.mock(Connection.class);
        PreparedStatement mockPreparedStatement = Mockito.mock(PreparedStatement.class);

        when(mockDataSource.getConnection()).thenReturn(mockConnection);
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);

        // Eseguire il metodo doUpdateCredentials
        assertDoesNotThrow(() -> utenteDAO.doUpdateCredentials(utente, "new@email.com", "newpassword"));

        // Verificare che il prepared statement sia stato chiamato con i parametri corretti
        verify(mockPreparedStatement).setString(1, "new@email.com");
        verify(mockPreparedStatement).setString(2, "newpassword");
        verify(mockPreparedStatement).setString(3, "test@email.com");

        // Verificare che executeUpdate sia stato chiamato
        verify(mockPreparedStatement).executeUpdate();

        // Verificare che le connessioni siano state chiuse
        verify(mockPreparedStatement).close();
        verify(mockConnection).close();
    }

    @Test
    void testDoRetrieveByKey() throws SQLException {
        // Creare un utente di prova
        Utente utente = new Utente("test@email.com", "password", "Nome", "Cognome", "azienda123", true, "ruolo");

        // Mockare la connessione, il prepared statement, il result set e l'esecuzione della query
        Connection mockConnection = Mockito.mock(Connection.class);
        PreparedStatement mockPreparedStatement = Mockito.mock(PreparedStatement.class);
        ResultSet mockResultSet = Mockito.mock(ResultSet.class);

        when(mockDataSource.getConnection()).thenReturn(mockConnection);
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);

        // Configurare il result set per restituire l'utente di prova
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getString("email")).thenReturn(utente.getEmail());
        when(mockResultSet.getString("pwd")).thenReturn(utente.getPassword());
        when(mockResultSet.getString("nome")).thenReturn(utente.getNome());
        when(mockResultSet.getString("cognome")).thenReturn(utente.getCognome());
        when(mockResultSet.getString("idAzienda")).thenReturn(utente.getIdAzienda());
        when(mockResultSet.getBoolean("stato")).thenReturn(utente.isStato());
        when(mockResultSet.getString("ruolo")).thenReturn(utente.getRuolo());

        // Eseguire il metodo doRetrieveByKey
        Utente retrievedUtente = utenteDAO.doRetrieveByKey("test@email.com");

        // Verificare che il risultato sia non nullo e contenga i valori attesi
        assertNotNull(retrievedUtente);
        assertEquals(utente, retrievedUtente);

        // Verificare che le connessioni siano state chiuse
        verify(mockResultSet).close();
        verify(mockPreparedStatement).close();
        verify(mockConnection).close();
    }

    @Test
    void testDoRetrieveByKeyWithAzienda() throws SQLException {
        // Creare un utente di prova
        Utente utente = new Utente("test@email.com", "password", "Nome", "Cognome", "azienda123", true, "ruolo");

        // Mockare la connessione, il prepared statement, il result set e l'esecuzione della query
        Connection mockConnection = Mockito.mock(Connection.class);
        PreparedStatement mockPreparedStatement = Mockito.mock(PreparedStatement.class);
        ResultSet mockResultSet = Mockito.mock(ResultSet.class);

        when(mockDataSource.getConnection()).thenReturn(mockConnection);
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);

        // Configurare il result set per restituire l'utente di prova
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getString("email")).thenReturn(utente.getEmail());
        when(mockResultSet.getString("pwd")).thenReturn(utente.getPassword());
        when(mockResultSet.getString("nome")).thenReturn(utente.getNome());
        when(mockResultSet.getString("cognome")).thenReturn(utente.getCognome());
        when(mockResultSet.getString("idAzienda")).thenReturn(utente.getIdAzienda());
        when(mockResultSet.getBoolean("stato")).thenReturn(utente.isStato());
        when(mockResultSet.getString("ruolo")).thenReturn(utente.getRuolo());

        // Eseguire il metodo doRetrieveByKey con specifica azienda
        Utente retrievedUtente = utenteDAO.doRetrieveByKey("test@email.com", "azienda123");

        // Verificare che il risultato sia non nullo e contenga i valori attesi
        assertNotNull(retrievedUtente);
        assertEquals(utente, retrievedUtente);

        // Verificare che le connessioni siano state chiuse
        verify(mockResultSet).close();
        verify(mockPreparedStatement).close();
        verify(mockConnection).close();
    }

    @Test
    void testDoRetrieveAll() throws SQLException {
        // Creare una lista di utenti di prova
        List<Utente> utenti = new ArrayList<>();
        utenti.add(new Utente("test1@email.com", "password", "Nome1", "Cognome1", "azienda123", true, "ruolo"));
        utenti.add(new Utente("test2@email.com", "password", "Nome2", "Cognome2", "azienda123", true, "ruolo"));

        // Mockare la connessione, il prepared statement, il result set e l'esecuzione della query
        Connection mockConnection = Mockito.mock(Connection.class);
        PreparedStatement mockPreparedStatement = Mockito.mock(PreparedStatement.class);
        ResultSet mockResultSet = Mockito.mock(ResultSet.class);

        when(mockDataSource.getConnection()).thenReturn(mockConnection);
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);

        // Configurare il result set per restituire la lista di utenti di prova
        when(mockResultSet.next()).thenReturn(true, true, false);
        when(mockResultSet.getString("email")).thenReturn(utenti.get(0).getEmail(), utenti.get(1).getEmail());
        when(mockResultSet.getString("pwd")).thenReturn(utenti.get(0).getPassword(), utenti.get(1).getPassword());
        when(mockResultSet.getString("nome")).thenReturn(utenti.get(0).getNome(), utenti.get(1).getNome());
        when(mockResultSet.getString("cognome")).thenReturn(utenti.get(0).getCognome(), utenti.get(1).getCognome());
        when(mockResultSet.getString("idAzienda")).thenReturn(utenti.get(0).getIdAzienda(), utenti.get(1).getIdAzienda());
        when(mockResultSet.getBoolean("stato")).thenReturn(utenti.get(0).isStato(), utenti.get(1).isStato());
        when(mockResultSet.getString("ruolo")).thenReturn(utenti.get(0).getRuolo(), utenti.get(1).getRuolo());

        // Eseguire il metodo doRetrieveAll
        List<Utente> retrievedUtenti = new ArrayList<>(utenteDAO.doRetrieveAll("azienda123"));

        // Verificare che il risultato sia non nullo e contenga gli utenti attesi
        assertNotNull(retrievedUtenti);
        assertEquals(utenti.size(), retrievedUtenti.size());
        assertTrue(retrievedUtenti.containsAll(utenti));

        // Verificare che le connessioni siano state chiuse
        verify(mockResultSet).close();
        verify(mockPreparedStatement).close();
        verify(mockConnection).close();
    }
    
    @Test
    void testDoRetrieveAllResponsabili() throws SQLException {
        // Creare una lista di utenti di prova
        List<Utente> responsabili = new ArrayList<>();
        responsabili.add(new Utente("resp1@email.com", "password", "Resp1", "Cognome1", "azienda123", true, "responsabile"));
        responsabili.add(new Utente("resp2@email.com", "password", "Resp2", "Cognome2", "azienda123", true, "responsabile"));

        // Mockare la connessione, il prepared statement, il result set e l'esecuzione della query
        Connection mockConnection = Mockito.mock(Connection.class);
        PreparedStatement mockPreparedStatement = Mockito.mock(PreparedStatement.class);
        ResultSet mockResultSet = Mockito.mock(ResultSet.class);

        when(mockDataSource.getConnection()).thenReturn(mockConnection);
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);

        // Configurare il result set per restituire la lista di responsabili di prova
        when(mockResultSet.next()).thenReturn(true, true, false);
        when(mockResultSet.getString("email")).thenReturn(responsabili.get(0).getEmail(), responsabili.get(1).getEmail());
        when(mockResultSet.getString("pwd")).thenReturn(responsabili.get(0).getPassword(), responsabili.get(1).getPassword());
        when(mockResultSet.getString("nome")).thenReturn(responsabili.get(0).getNome(), responsabili.get(1).getNome());
        when(mockResultSet.getString("cognome")).thenReturn(responsabili.get(0).getCognome(), responsabili.get(1).getCognome());
        when(mockResultSet.getString("idAzienda")).thenReturn(responsabili.get(0).getIdAzienda(), responsabili.get(1).getIdAzienda());
        when(mockResultSet.getBoolean("stato")).thenReturn(responsabili.get(0).isStato(), responsabili.get(1).isStato());
        when(mockResultSet.getString("ruolo")).thenReturn(responsabili.get(0).getRuolo(), responsabili.get(1).getRuolo());

        // Eseguire il metodo doRetrieveAllResponsabili
        List<Utente> retrievedResponsabili = new ArrayList<>(utenteDAO.doRetrieveAllResponsabili("azienda123"));

        // Verificare che il risultato sia non nullo e contenga i responsabili attesi
        assertNotNull(retrievedResponsabili);
        assertEquals(responsabili.size(), retrievedResponsabili.size());
        assertTrue(retrievedResponsabili.containsAll(responsabili));

        // Verificare che le connessioni siano state chiuse
        verify(mockResultSet).close();
        verify(mockPreparedStatement).close();
        verify(mockConnection).close();
    }

    @Test
    void testDoRetrieveAllSubordinati() throws SQLException {
        // Creare una lista di utenti di prova
        List<Utente> subordinati = new ArrayList<>();
        subordinati.add(new Utente("sub1@email.com", "password", "Sub1", "Cognome1", "azienda123", true, "subordinato"));
        subordinati.add(new Utente("sub2@email.com", "password", "Sub2", "Cognome2", "azienda123", true, "subordinato"));

        // Mockare la connessione, il prepared statement, il result set e l'esecuzione della query
        Connection mockConnection = Mockito.mock(Connection.class);
        PreparedStatement mockPreparedStatement = Mockito.mock(PreparedStatement.class);
        ResultSet mockResultSet = Mockito.mock(ResultSet.class);

        when(mockDataSource.getConnection()).thenReturn(mockConnection);
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);

        // Configurare il result set per restituire la lista di subordinati di prova
        when(mockResultSet.next()).thenReturn(true, true, false);
        when(mockResultSet.getString("email")).thenReturn(subordinati.get(0).getEmail(), subordinati.get(1).getEmail());
        when(mockResultSet.getString("pwd")).thenReturn(subordinati.get(0).getPassword(), subordinati.get(1).getPassword());
        when(mockResultSet.getString("nome")).thenReturn(subordinati.get(0).getNome(), subordinati.get(1).getNome());
        when(mockResultSet.getString("cognome")).thenReturn(subordinati.get(0).getCognome(), subordinati.get(1).getCognome());
        when(mockResultSet.getString("idAzienda")).thenReturn(subordinati.get(0).getIdAzienda(), subordinati.get(1).getIdAzienda());
        when(mockResultSet.getBoolean("stato")).thenReturn(subordinati.get(0).isStato(), subordinati.get(1).isStato());
        when(mockResultSet.getString("ruolo")).thenReturn(subordinati.get(0).getRuolo(), subordinati.get(1).getRuolo());

        // Eseguire il metodo doRetrieveAllSubordinati
        List<Utente> retrievedSubordinati = new ArrayList<>(utenteDAO.doRetrieveAllSubordinati("azienda123"));

        // Verificare che il risultato sia non nullo e contenga i subordinati attesi
        assertNotNull(retrievedSubordinati);
        assertEquals(subordinati.size(), retrievedSubordinati.size());
        assertTrue(retrievedSubordinati.containsAll(subordinati));

        // Verificare che le connessioni siano state chiuse
        verify(mockResultSet).close();
        verify(mockPreparedStatement).close();
        verify(mockConnection).close();
    }
    
    @Test
    public void testDoRetrieveByNotProject() throws SQLException {
        Connection connection = mock(Connection.class);
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        ResultSet resultSet = mock(ResultSet.class);

        when(mockDataSource.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);

        // Simulare i dati di ritorno dal ResultSet
        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getString("email")).thenReturn("test@email.com");
        when(resultSet.getString("pwd")).thenReturn("testpwd");
        when(resultSet.getString("nome")).thenReturn("Test");
        when(resultSet.getString("cognome")).thenReturn("User");
        when(resultSet.getString("idAzienda")).thenReturn("testAzienda");
        when(resultSet.getBoolean("stato")).thenReturn(true);
        when(resultSet.getString("ruolo")).thenReturn("subordinato");

        // Eseguire il metodo da testare
        List<Utente> result = utenteDAO.doRetriveByNotProject(1, "testAzienda");

        // Verificare il risultato
        assertEquals(1, result.size());
    }
    
    @Test
    public void testDoRetrieveAllSubMngdByResp() throws SQLException {
        // Creare una lista di utenti di prova
        List<Utente> userList = new ArrayList<>();
        userList.add(new Utente("user1@email.com", "password", "User1", "Surname1", "azienda123", true, "ruolo1"));
        userList.add(new Utente("user2@email.com", "password", "User2", "Surname2", "azienda123", true, "ruolo2"));

        // Mockare la connessione, il prepared statement, il result set e l'esecuzione della query
        Connection mockConnection = Mockito.mock(Connection.class);
        PreparedStatement mockPreparedStatement = Mockito.mock(PreparedStatement.class);
        ResultSet mockResultSet = Mockito.mock(ResultSet.class);

        when(mockDataSource.getConnection()).thenReturn(mockConnection);
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);

        // Configurare il result set per restituire la lista di utenti di prova
        when(mockResultSet.next()).thenReturn(true, true, false);
        when(mockResultSet.getString("email")).thenReturn(userList.get(0).getEmail(), userList.get(1).getEmail());
        when(mockResultSet.getString("pwd")).thenReturn(userList.get(0).getPassword(), userList.get(1).getPassword());
        when(mockResultSet.getString("nome")).thenReturn(userList.get(0).getNome(), userList.get(1).getNome());
        when(mockResultSet.getString("cognome")).thenReturn(userList.get(0).getCognome(), userList.get(1).getCognome());
        when(mockResultSet.getString("idAzienda")).thenReturn(userList.get(0).getIdAzienda(), userList.get(1).getIdAzienda());
        when(mockResultSet.getBoolean("stato")).thenReturn(userList.get(0).isStato(), userList.get(1).isStato());
        when(mockResultSet.getString("ruolo")).thenReturn(userList.get(0).getRuolo(), userList.get(1).getRuolo());

        // Eseguire il metodo doRetrieveAllSubMngdByResp
        List<Utente> retrievedUsers = utenteDAO.doRetrieveAllSubMngdByResp("azienda123", "responsabile@email.com");

        // Verificare che il risultato sia non nullo e contenga gli utenti attesi
        assertNotNull(retrievedUsers);
        assertEquals(userList.size(), retrievedUsers.size());
        assertTrue(retrievedUsers.containsAll(userList));

        // Verificare che le connessioni siano state chiuse
        verify(mockResultSet).close();
        verify(mockPreparedStatement).close();
        verify(mockConnection).close();
    }

    @Test
    public void testDoRetriveByNotProjectResp() throws SQLException {
        // Creare una lista di utenti di prova
        List<Utente> responsabili = new ArrayList<>();
        responsabili.add(new Utente("resp1@email.com", "password", "Resp1", "Surname1", "azienda123", true, "responsabile"));
        responsabili.add(new Utente("resp2@email.com", "password", "Resp2", "Surname2", "azienda123", true, "responsabile"));

        // Mockare la connessione, il prepared statement, il result set e l'esecuzione della query
        Connection mockConnection = Mockito.mock(Connection.class);
        PreparedStatement mockPreparedStatement = Mockito.mock(PreparedStatement.class);
        ResultSet mockResultSet = Mockito.mock(ResultSet.class);

        when(mockDataSource.getConnection()).thenReturn(mockConnection);
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);

        // Configurare il result set per restituire la lista di responsabili di prova
        when(mockResultSet.next()).thenReturn(true, true, false);
        when(mockResultSet.getString("email")).thenReturn(responsabili.get(0).getEmail(), responsabili.get(1).getEmail());
        when(mockResultSet.getString("pwd")).thenReturn(responsabili.get(0).getPassword(), responsabili.get(1).getPassword());
        when(mockResultSet.getString("nome")).thenReturn(responsabili.get(0).getNome(), responsabili.get(1).getNome());
        when(mockResultSet.getString("cognome")).thenReturn(responsabili.get(0).getCognome(), responsabili.get(1).getCognome());
        when(mockResultSet.getString("idAzienda")).thenReturn(responsabili.get(0).getIdAzienda(), responsabili.get(1).getIdAzienda());
        when(mockResultSet.getBoolean("stato")).thenReturn(responsabili.get(0).isStato(), responsabili.get(1).isStato());
        when(mockResultSet.getString("ruolo")).thenReturn(responsabili.get(0).getRuolo(), responsabili.get(1).getRuolo());

        // Eseguire il metodo doRetriveByNotProjectResp
        List<Utente> retrievedResponsabili = utenteDAO.doRetriveByNotProjectResp("responsabile@email.com", "azienda123");

        // Verificare che il risultato sia non nullo e contenga i responsabili attesi
        assertNotNull(retrievedResponsabili);
        assertEquals(responsabili.size(), retrievedResponsabili.size());
        assertTrue(retrievedResponsabili.containsAll(responsabili));

        // Verificare che le connessioni siano state chiuse
        verify(mockResultSet).close();
        verify(mockPreparedStatement).close();
        verify(mockConnection).close();
    }
}
