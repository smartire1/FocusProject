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
        String query = "INSERT INTO StatisticheResponsabile (email, num_progetti_completati, num_progetti_in_corso, num_permessi_richiesti,"
        													+ "num_task_completati, num_task_in_corso) VALUES (?, ?, ?, ? ?, ?)";

        try (
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setString(1, statistiche.getEmail());
            preparedStatement.setInt(2, statistiche.getNum_progetti_completati());
            preparedStatement.setInt(3, statistiche.getNum_progetti_in_corso());
            preparedStatement.setInt(4, statistiche.getNum_permessi_richiesti());
			preparedStatement.setInt(5, statistiche.getNum_task_completati());
			preparedStatement.setInt(6, statistiche.getNum_task_in_corso());         
            preparedStatement.executeUpdate();
        }
    }

    // Metodo per aggiornare le statistiche nel database
    public synchronized void doUpdate(StatisticheSubordinato statistiche) throws SQLException {
        String query = "UPDATE StatisticheSubordinato SET num_progetti_completati = ?, num_progetti_in_corso = ?, num_permessi_richiesti = ?"
				+ "num_task_completati = ?, num_task_in_corso = ? WHERE email = ?";

		try (
		Connection connection = dataSource.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query)
		) {
			preparedStatement.setInt(1, statistiche.getNum_progetti_completati());
			preparedStatement.setInt(2, statistiche.getNum_progetti_in_corso());
			preparedStatement.setInt(3, statistiche.getNum_permessi_richiesti());
			preparedStatement.setInt(4, statistiche.getNum_task_completati());
			preparedStatement.setInt(5, statistiche.getNum_task_in_corso());    
			preparedStatement.setString(6, statistiche.getEmail());			
			preparedStatement.executeUpdate();
		}
	}

    // Metodo per recuperare le statistiche dal database tramite email
    public StatisticheSubordinato doRetrieveByEmail(String email) throws SQLException {
        String query = "SELECT * FROM StatisticheResponsabile WHERE email = ?";

        try (
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setString(1, email);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return new StatisticheSubordinato(
                            resultSet.getString("email"),
                            resultSet.getInt("num_progetti_completati"),
                            resultSet.getInt("num_progetti_in_corso"),
                            resultSet.getInt("num_permessi_richiesti"),
                            resultSet.getInt("num_subordinati_gestiti"),
                            resultSet.getInt("num_scadenze_rispettate")                            
                    );
                }
            }
        }
        return null;
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
