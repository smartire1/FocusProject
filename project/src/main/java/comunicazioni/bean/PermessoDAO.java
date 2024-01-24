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
            preparedStatement.setObject(4, permesso.isStato(), Types.BOOLEAN);
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
    
    // Metodo per aggioranre un Permesso nel database
    public synchronized void doUpdate(Permesso permesso) throws SQLException {
        String query = "UPDATE Permesso SET dal_giorno = ?, al_giorno = ?, motivo = ?, stato = ?, richiedente_email = ? WHERE id = ?";

        try (
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            preparedStatement.setString(1, permesso.getDalGiorno());
            preparedStatement.setString(2, permesso.getAlGiorno());
            preparedStatement.setString(3, permesso.getMotivo());
            preparedStatement.setObject(4, permesso.isStato(), Types.BOOLEAN);
            preparedStatement.setString(5, permesso.getRichiedenteEmail());
            preparedStatement.setInt(6, permesso.getId());

            preparedStatement.executeUpdate();
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
    public Permesso doRetrieveByKey(int id, String idAzienda) throws SQLException {
        Permesso permesso = null;
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
                if (resultSet.next()) {
                    permesso = new Permesso(
                            resultSet.getInt("id"),
                            resultSet.getString("dal_giorno"),
                            resultSet.getString("al_giorno"),
                            resultSet.getString("motivo"),
                            resultSet.getBoolean("stato"),
                            resultSet.getString("richiedente_email")
                    );
                }
            }
        }

        return permesso;
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

    // Metodo per recuperare tutti i permessi richiesti da utenti con un certo ruolo (relativi sempre ad una data azienda)
    public List<Permesso> doRetrieveAllNotManagedAndByRuolo(String idAzienda, String ruolo) throws SQLException {
        List<Permesso> permessi = new ArrayList<>();
        String query = "SELECT * FROM Permesso p " +
                       "JOIN Utente u ON p.richiedente_email = u.email " +
                       "WHERE p.stato IS NULL AND u.idAzienda = ? AND u.ruolo = ?";

        try (
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            preparedStatement.setString(1, idAzienda);
            preparedStatement.setString(2, ruolo);

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
    
    // Metodo per recuperare tutti i permessi gestiti e richiesti da utenti con un certo ruolo (relativi sempre ad una data azienda)
    public List<Permesso> doRetrieveAllManagedAndByRuolo(String idAzienda, String ruolo) throws SQLException {
        List<Permesso> permessi = new ArrayList<>();
        String query = "SELECT * FROM Permesso p " +
                       "JOIN Utente u ON p.richiedente_email = u.email " +
                       "WHERE p.stato IS NOT NULL AND u.idAzienda = ? AND u.ruolo = ?";

        try (
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            preparedStatement.setString(1, idAzienda);
            preparedStatement.setString(2, ruolo);

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
    
    // Metodo per recuperare tutti i permessi richiesti da un certo utente (relativi sempre ad una data azienda)
    public List<Permesso> doRetrieveAllByUser(String idAzienda, String email) throws SQLException {
        List<Permesso> permessi = new ArrayList<>();
        String query = "SELECT * FROM Permesso p " +
                       "JOIN Utente u ON p.richiedente_email = u.email " +
                       "WHERE u.idAzienda = ? AND u.email = ?";

        try (
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            preparedStatement.setString(1, idAzienda);
            preparedStatement.setString(2, email);

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
