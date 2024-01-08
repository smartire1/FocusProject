package dipendenti.bean;  // Sostituisci "tuopackage" con il tuo package

import javax.sql.DataSource;
import java.sql.*;

public class StatistichePersonaleDAO {

    private final DataSource dataSource;

    public StatistichePersonaleDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    // Metodo per salvare le statistiche nel database
    public synchronized void doSave(StatistichePersonale statistiche) throws SQLException {
        String query = "INSERT INTO StatistichePersonale (email, num_progetti_completati, num_progetti_in_corso, num_permessi_richiesti) VALUES (?, ?, ?, ?)";

        try (
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setString(1, statistiche.getEmail());
            preparedStatement.setInt(2, statistiche.getNumProgettiCompletati());
            preparedStatement.setInt(3, statistiche.getNumProgettiInCorso());
            preparedStatement.setInt(4, statistiche.getNumPermessiRichiesti());
            preparedStatement.executeUpdate();
        }
    }

    // Metodo per aggiornare le statistiche nel database
    public synchronized void doUpdate(StatistichePersonale statistiche) throws SQLException {
        String query = "UPDATE StatistichePersonale SET num_progetti_completati = ?, num_progetti_in_corso = ?, num_permessi_richiesti = ? WHERE email = ?";

        try (
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setInt(1, statistiche.getNumProgettiCompletati());
            preparedStatement.setInt(2, statistiche.getNumProgettiInCorso());
            preparedStatement.setInt(3, statistiche.getNumPermessiRichiesti());
            preparedStatement.setString(4, statistiche.getEmail());
            preparedStatement.executeUpdate();
        }
    }

    // Metodo per recuperare le statistiche dal database tramite email
    public StatistichePersonale doRetrieveByEmail(String email) throws SQLException {
        String query = "SELECT * FROM StatistichePersonale WHERE email = ?";

        try (
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setString(1, email);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return new StatistichePersonale(
                            resultSet.getString("email"),
                            resultSet.getInt("num_progetti_completati"),
                            resultSet.getInt("num_progetti_in_corso"),
                            resultSet.getInt("num_permessi_richiesti")
                    );
                }
            }
        }

        return null;
    }
}
