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
    	
        String query = "INSERT INTO Dirigente (email, nomeAzienda, piva) VALUES (?, ?, ?)";
        
        try {
        	connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, dirigente.getEmail());
            preparedStatement.setString(2, dirigente.getNomeAzienda());
            preparedStatement.setString(3, dirigente.getPiva());
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
    	
        String query = "UPDATE Dirigente SET nomeAzienda = ?, piva = ?, email = ? WHERE email = ?";
        
        try {
        	connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, dirigente.getEmail());
            preparedStatement.setString(2, dirigente.getNomeAzienda());
            preparedStatement.setString(3, dirigente.getPiva());
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
 	public synchronized Dirigente doRetrieveByKey(String email) throws SQLException {
 		Connection connection = null;
 		PreparedStatement preparedStatement = null;

 		String query = "SELECT * FROM Dirigente WHERE email = ?";

 		try {
 			connection = ds.getConnection();
 			preparedStatement = connection.prepareStatement(query);
 			preparedStatement.setString(1, email);

 			try (ResultSet rs = preparedStatement.executeQuery()) {
 				if (rs.next()) {
 					return new Dirigente(rs.getString("email"), rs.getString("nomeAzienda"),
 							rs.getString("piva"));
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
