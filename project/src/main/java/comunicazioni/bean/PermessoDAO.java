package comunicazioni.bean;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PermessoDAO {

    private final DataSource dataSource;

    public PermessoDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    // Metodo per salvare un Permesso nel database
    public synchronized void doSave(Permesso permesso) throws SQLException {
        String query = "INSERT INTO Permesso (giorno, motivo, stato, richiedente_email) VALUES (?, ?, ?, ?)";

        try (
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            preparedStatement.setString(1, permesso.getGiorno());
            preparedStatement.setString(2, permesso.getMotivo());
            preparedStatement.setBoolean(3, permesso.isStato());
            preparedStatement.setString(4, permesso.getRichiedenteEmail());
            preparedStatement.executeUpdate();

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    permesso.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Failed to retrieve generated ID.");
                }
            }
        }
    }

    // Metodo per eliminare un Permesso nel database
    public synchronized void doDelete(int id) throws SQLException {
        String query = "DELETE FROM Permesso WHERE id = ?";

        try (
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
    }
    
    public List<Permesso> doRetrieveByKey(int id) throws SQLException {
        List<Permesso> permessi = new ArrayList<>();
        String query = "SELECT * FROM Permesso WHERE id = ?";

        try (
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery()
        ) {
            while (resultSet.next()) {
                Permesso permesso = new Permesso(
                        resultSet.getInt("id"),
                        resultSet.getString("giorno"),
                        resultSet.getString("motivo"),
                        resultSet.getBoolean("stato"),
                        resultSet.getString("richiedente_email")
                );
                permessi.add(permesso);
            }
        }

        return permessi;
    }    

    // Metodo per recuperare tutti i Permessi dal database
    public List<Permesso> doRetrieveAll() throws SQLException {
        List<Permesso> permessi = new ArrayList<>();
        String query = "SELECT * FROM Permesso";

        try (
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery()
        ) {
            while (resultSet.next()) {
                Permesso permesso = new Permesso(
                        resultSet.getInt("id"),
                        resultSet.getString("giorno"),
                        resultSet.getString("motivo"),
                        resultSet.getBoolean("stato"),
                        resultSet.getString("richiedente_email")
                );
                permessi.add(permesso);
            }
        }

        return permessi;
    }
}
