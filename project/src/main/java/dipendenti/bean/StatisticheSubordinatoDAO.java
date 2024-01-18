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
}
