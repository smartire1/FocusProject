package dipendenti.bean;  // Sostituisci "tuopackage" con il tuo package

import javax.sql.DataSource;
import java.sql.*;

public class StatisticheSubordinatoDAO {

    private final DataSource dataSource;

    public StatisticheSubordinatoDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    public StatisticheSubordinato getStatisticheSubordinato(String subordinatoEmail) throws SQLException {
        StatisticheSubordinato statistiche = new StatisticheSubordinato(subordinatoEmail, 0, 0, 0, 0, 0);

        String query = "SELECT " +
                "(SELECT COUNT(*) FROM LavoraA " +
                " JOIN Progetto ON LavoraA.id_progetto = Progetto.id_progetto " +
                " WHERE LavoraA.email = ? AND Progetto.stato = true) AS num_progetti_completati, " +
                "(SELECT COUNT(*) FROM LavoraA " +
                " JOIN Progetto ON LavoraA.id_progetto = Progetto.id_progetto " +
                " WHERE LavoraA.email = ? AND Progetto.stato = false) AS num_progetti_in_corso, " +
                "(SELECT COUNT(*) FROM Permesso WHERE richiedente_email = ?) AS num_permessi_richiesti, " +
                "(SELECT COUNT(*) FROM Task WHERE subordinato_email = ? AND stato = true) AS num_task_completati, " +
                "(SELECT COUNT(*) FROM Task WHERE subordinato_email = ? AND stato = false) AS num_task_in_corso";

        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setString(1, subordinatoEmail);
            preparedStatement.setString(2, subordinatoEmail);
            preparedStatement.setString(3, subordinatoEmail);
            preparedStatement.setString(4, subordinatoEmail);
            preparedStatement.setString(5, subordinatoEmail);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    statistiche = new StatisticheSubordinato(subordinatoEmail,
                            resultSet.getInt("num_progetti_completati"),
                            resultSet.getInt("num_progetti_in_corso"),
                            resultSet.getInt("num_permessi_richiesti"),
                            resultSet.getInt("num_task_completati"),
                            resultSet.getInt("num_task_in_corso"));
                }
            }
        }

        return statistiche;
    }
}
