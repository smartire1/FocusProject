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
        String query = "INSERT INTO Permesso (dal_giorno, al_giorno, motivo, stato, richiedente_email) VALUES (?, ?, ?, ?, ?)";

        try (
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        ) {
            preparedStatement.setString(1, permesso.getDalGiorno());
            preparedStatement.setString(2, permesso.getAlGiorno());
            preparedStatement.setString(3, permesso.getMotivo());
            preparedStatement.setBoolean(4, permesso.isStato());
            preparedStatement.setString(5, permesso.getRichiedenteEmail());
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
    
    // Metodo per recuperare un permesso specifico
    public List<Permesso> doRetrieveByKey(int id, String idAzienda) throws SQLException {
        List<Permesso> permessi = new ArrayList<>();
        String query = "SELECT * FROM Permesso p " +
                       "JOIN Utente u ON p.richiedente_email = u.email " +
                       "WHERE p.id = ? AND u.idAzienda = ?";

        try (
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, idAzienda);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Permesso permesso = new Permesso(
                            resultSet.getInt("id"),
                            resultSet.getString("dal_giorno"),
                            resultSet.getString("al_giorno"),
                            resultSet.getString("motivo"),
                            resultSet.getBoolean("stato"),
                            resultSet.getString("richiedente_email")
                    );
                    permessi.add(permesso);
                }
            }
        }

        return permessi;
    }

 // Metodo per recuperare tutti i permessi (relativi sempre ad una data azienda)
    public List<Permesso> doRetrieveAll(String idAzienda) throws SQLException {
        List<Permesso> permessi = new ArrayList<>();
        String query = "SELECT * FROM Permesso p " +
                       "JOIN Utente u ON p.richiedente_email = u.email " +
                       "WHERE u.idAzienda = ?";

        try (
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            preparedStatement.setString(1, idAzienda);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Permesso permesso = new Permesso(
                            resultSet.getInt("id"),
                            resultSet.getString("dal_giorno"),
                            resultSet.getString("al_giorno"),
                            resultSet.getString("motivo"),
                            resultSet.getBoolean("stato"),
                            resultSet.getString("richiedente_email")
                    );
                    permessi.add(permesso);
                }
            }
        }

        return permessi;
    }

}
