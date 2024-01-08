package comunicazioni.bean;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ComunicazioneDAO {

    private final DataSource dataSource;

    public ComunicazioneDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    // Metodo per salvare una Comunicazione nel database
    public synchronized void doSave(Comunicazione comunicazione) throws SQLException {
        String query = "INSERT INTO Comunicazione (titolo, corpo, mittente_email) VALUES (?, ?, ?)";

        try (
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setString(1, comunicazione.getTitolo());
            preparedStatement.setString(2, comunicazione.getCorpo());
            preparedStatement.setString(3, comunicazione.getMittenteEmail());
            preparedStatement.executeUpdate();

        }
    }

    // Metodo per eliminare una Comunicazione nel database
    public synchronized void doDelete(int id) throws SQLException {
        String query = "DELETE FROM Comunicazione WHERE id = ?";

        try (
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
    }

    public List<Comunicazione> doRetrieveByKey(int id) throws SQLException {
        List<Comunicazione> comunicazioni = new ArrayList<>();
        String query = "SELECT * FROM Comunicazione WHERE id = ?";

        try (
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery()
        ) {
            while (resultSet.next()) {
                Comunicazione comunicazione = new Comunicazione(
                        resultSet.getInt("id"),
                        resultSet.getString("titolo"),
                        resultSet.getString("corpo"),
                        resultSet.getString("mittente_email")
                );
                comunicazioni.add(comunicazione);
            }
        }

        return comunicazioni;
    }    
    
    // Metodo per recuperare tutte le Comunicazioni dal database
    public List<Comunicazione> doRetrieveAll() throws SQLException {
        List<Comunicazione> comunicazioni = new ArrayList<>();
        String query = "SELECT * FROM Comunicazione";

        try (
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery()
        ) {
            while (resultSet.next()) {
                Comunicazione comunicazione = new Comunicazione(
                        resultSet.getInt("id"),
                        resultSet.getString("titolo"),
                        resultSet.getString("corpo"),
                        resultSet.getString("mittente_email")
                );
                comunicazioni.add(comunicazione);
            }
        }

        return comunicazioni;
    }
}
