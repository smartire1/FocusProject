package dipendenti.bean;

import javax.sql.DataSource;
import java.sql.*;

public class StatisticheResponsabileDAO {

    private final DataSource dataSource;

    public StatisticheResponsabileDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    // Metodo per salvare le statistiche nel database
    public synchronized void doSave(StatisticheResponsabile statistiche) throws SQLException {
        String query = "INSERT INTO StatisticheResponsabile (email, num_progetti_completati, num_progetti_in_corso, num_permessi_richiesti,"
				+ "num_subordinati_gestiti) VALUES (?, ?, ?, ? ?, ?)";

		try (
		Connection connection = dataSource.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query)
		) {
			preparedStatement.setString(1, statistiche.getEmail());
			preparedStatement.setInt(2, statistiche.getNum_progetti_completati());
			preparedStatement.setInt(3, statistiche.getNum_progetti_in_corso());
			preparedStatement.setInt(4, statistiche.getNum_permessi_richiesti());
			preparedStatement.setInt(5, statistiche.getNum_subordinati_gestiti());
			preparedStatement.executeUpdate();
		}
	}

    // Metodo per aggiornare le statistiche nel database
    public synchronized void doUpdate(StatisticheResponsabile statistiche) throws SQLException {
        String query = "UPDATE StatisticheResponsabile SET num_progetti_completati = ?, num_progetti_in_corso = ?, num_permessi_richiesti = ?"
        													+ "num_subordinati_gestiti = ? WHERE email = ?";

        try (
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setInt(1, statistiche.getNum_progetti_completati());
            preparedStatement.setInt(2, statistiche.getNum_progetti_in_corso());
            preparedStatement.setInt(3, statistiche.getNum_permessi_richiesti());
            preparedStatement.setInt(4, statistiche.getNum_subordinati_gestiti());
            preparedStatement.setString(6, statistiche.getEmail());
            preparedStatement.executeUpdate();
        }
    }   

    // Metodo per recuperare le statistiche dal database tramite email
    public StatisticheResponsabile doRetrieveByEmail(String email) throws SQLException {
        String query = "SELECT * FROM StatisticheResponsabile WHERE email = ?";

        try (
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setString(1, email);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return new StatisticheResponsabile(
                            resultSet.getString("email"),
                            resultSet.getInt("num_progetti_completati"),
                            resultSet.getInt("num_progetti_in_corso"),
                            resultSet.getInt("num_permessi_richiesti"),
                            resultSet.getInt("num_subordinati_gestiti")
                    );
                }
            }
        }
        return null;
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
