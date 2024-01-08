package account.bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import javax.sql.DataSource;

public class DirigenteDAO {
private DataSource ds = null;
	
	public DirigenteDAO(DataSource ds) {
		this.ds = ds;
	}
	
    // Metodo per salvare un Dirigente nel database
    public synchronized void doSave(Dirigente dirigente) throws SQLException {
    	
		Connection connection = null;
		PreparedStatement preparedStatement = null;
    	
        String query = "INSERT INTO Dirigente (email, nomeAzienda, piva) VALUES (?, ?)";
        
        try {
        	connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, dirigente.getEmail());
            preparedStatement.setString(2, dirigente.getNomeAzienda());
            preparedStatement.executeUpdate();
        } finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} finally {
				if (connection != null) {
					connection.close();
				}
			}
		}
    }
    
    // Metodo per eliminare un Dirigente nel database
    public synchronized void doDelete(String email) throws SQLException {
    	
		Connection connection = null;
		PreparedStatement preparedStatement = null;
    	
        String query = "DELETE FROM Dirigente WHERE email = ?";
        
        try {
        	connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);
            preparedStatement.executeUpdate();
        } finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} finally {
				if (connection != null) {
					connection.close();
				}
			}
		}
    }
    
    // Metodo per aggiornare un Dirigente nel database
    public synchronized void doUpdate(Dirigente dirigente) throws SQLException {
    	
		Connection connection = null;
		PreparedStatement preparedStatement = null;
    	
        String query = "UPDATE Dirigente SET nomeAzienda = ?, email = ? WHERE email = ?";
        
        try {
        	connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, dirigente.getEmail());
            preparedStatement.setString(2, dirigente.getNomeAzienda());
            preparedStatement.executeUpdate();
        } finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} finally {
				if (connection != null) {
					connection.close();
				}
			}
		}
    }
    
 // Metodo per recuperare un Dirigente dal database tramite la chiave primaria
 	public synchronized Dirigente doRetrieveByKey(String email, String piva) throws SQLException {
 		Connection connection = null;
 		PreparedStatement preparedStatement = null;

 		String query = "SELECT D.* " + 
				   "FROM Dirigente D " + 
				   "JOIN Utente U ON D.email = U.email " + 
				   "WHERE D.email = ? AND U.piva = ?";

	try {
		connection = ds.getConnection();
		preparedStatement = connection.prepareStatement(query);
		preparedStatement.setString(1, email);
		preparedStatement.setString(2, piva);

 			try (ResultSet rs = preparedStatement.executeQuery()) {
 				if (rs.next()) {
 					return new Dirigente(rs.getString("email"), rs.getString("nomeAzienda"));
 				}
 			}
 		} finally {
 			try {
 				if (preparedStatement != null) {
 					preparedStatement.close();
 				}
 			} finally {
 				if (connection != null) {
 					connection.close();
 				}
 			}
 		}

 		return null;
 	}
 	
}
