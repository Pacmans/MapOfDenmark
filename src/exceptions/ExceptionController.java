package exceptions;

import java.io.IOException;

import javax.swing.JOptionPane;


public class ExceptionController {

	public static void recieveException(Exception e) {
		if(e instanceof IOException)
			ExceptionController.handleIOException((IOException) e);
	}
	
	/**
	 * Informs the user of an error while connecting to the database
	 * 
	 * @param ex
	 *            the IOException to show
	 */
	public static void handleIOException(IOException ex) {
		if (ex instanceof IOException) {
			// sets the title of the error window
			String title = "Input/Output error";
			// sets the message of the error window
			String message = "";
			message += "\nMessage: " + ex.getMessage();
			Throwable t = ex.getCause();
			// adds the cause of the error
			// to the message if it exists
			while (t != null) {
				message += " " + t.getCause();
			}
			message += "\n" + "Program will now execute!";
			printMessage(message,title);
			System.exit(0);
		}
	}
	
	public static void printMessage(String message, String title) {
		JOptionPane.showMessageDialog(null, message, title,
		JOptionPane.ERROR_MESSAGE);
	}



	
}
