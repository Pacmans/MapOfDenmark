package exceptions;

import java.sql.SQLException;

import javax.swing.JOptionPane;


public class ExceptionController {

	/**
	 * Informs the user of an error while connecting to the database
	 * 
	 * @param ex
	 *            the SQLException to show
	 */
	public static void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				// sets the title of the error window
				String title = "Database Error";
				// sets the message of the error window
				String message = "SQLState: "
						+ ((SQLException) e).getSQLState();
				message += "\nError Code: " + ((SQLException) e).getErrorCode();
				message += "\nMessage: " + e.getMessage();
				Throwable t = ex.getCause();
				// adds the cause of the error
				// to the message if it exists
				while (t != null) {
					message += "\n" + t.getCause();
				}
				JOptionPane.showMessageDialog(null, message, title,
				JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	
}
