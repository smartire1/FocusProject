package dipendenti.bean;  // Sostituisci "tuopackage" con il tuo package

import javax.sql.DataSource;
import java.sql.*;

public class StatisticheSubordinatoDAO {

    private final DataSource dataSource;

    public StatisticheSubordinatoDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    // Metodo per salvare le statistiche nel database
    public synchronized void doSave(StatisticheSubordinato statistiche) throws SQLException {
        String query = "INSERT INTO StatisticheSubordinato (email, num_task_completati, num_task_in_corso) VALUES (?, ?, ?)";

        try (
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setString(1, statistiche.getEmail());
            preparedStatement.setInt(2, statistiche.getNumTaskCompletati());
            preparedStatement.setInt(3, statistiche.getNumTaskInCorso());
            preparedStatement.executeUpdate();
        }
    }

    // Metodo per aggiornare le statistiche nel database
    public synchronized void doUpdate(StatisticheSubordinato statistiche) throws SQLException {
        String query = "UPDATE StatisticheSubordinato SET num_task_completati = ?, num_task_in_corso = ? WHERE email = ?";

        try (
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setInt(1, statistiche.getNumTaskCompletati());
            preparedStatement.setInt(2, statistiche.getNumTaskInCorso());
            preparedStatement.setString(3, statistiche.getEmail());
            preparedStatement.executeUpdate();
        }
    }

    // Metodo per recuperare le statistiche dal database tramite email
    public StatisticheSubordinato doRetrieveByEmail(String email) throws SQLException {
        String query = "SELECT * FROM StatisticheSubordinato WHERE email = ?";

        try (
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setString(1, email);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return new StatisticheSubordinato(
                            resultSet.getString("email"),
                            resultSet.getInt("num_task_completati"),
                            resultSet.getInt("num_task_in_corso")
                    );
                }
            }
        }

        return null;
    }
}
