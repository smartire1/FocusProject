package dipendenti.bean;

import javax.sql.DataSource;
import java.sql.*;

public class StatisticheResponsabileDAO {

    private final DataSource dataSource;

    public StatisticheResponsabileDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    public StatisticheResponsabile getStatisticheResponsabile(String emailResponsabile) throws SQLException {
        String query = "SELECT " +
                "(SELECT COUNT(*) FROM Progetto WHERE responsabile_email = ? AND stato = true) AS num_progetti_completati, " +
                "(SELECT COUNT(*) FROM Progetto WHERE responsabile_email = ? AND stato = false) AS num_progetti_in_corso, " +
                "(SELECT COUNT(*) FROM Permesso WHERE richiedente_email = ?) AS num_permessi_richiesti, " +
                "(SELECT COUNT(*) FROM LavoraA " +
                " JOIN Progetto ON LavoraA.id_progetto = Progetto.id_progetto " +
                " WHERE Progetto.responsabile_email = ?) AS num_subordinati_gestiti";

        try (
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            preparedStatement.setString(1, emailResponsabile);
            preparedStatement.setString(2, emailResponsabile);
            preparedStatement.setString(3, emailResponsabile);
            preparedStatement.setString(4, emailResponsabile);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int numProgettiCompletati = resultSet.getInt("num_progetti_completati");
                    int numProgettiInCorso = resultSet.getInt("num_progetti_in_corso");
                    int numPermessiRichiesti = resultSet.getInt("num_permessi_richiesti");
                    int numSubordinatiGestiti = resultSet.getInt("num_subordinati_gestiti");

                    return new StatisticheResponsabile(emailResponsabile, numProgettiCompletati, numProgettiInCorso, numPermessiRichiesti, numSubordinatiGestiti);
                }
            }
        }

        return null;
    }
}
