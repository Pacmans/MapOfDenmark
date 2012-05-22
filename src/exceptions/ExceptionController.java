package exceptions;

import java.io.IOException;

import javax.swing.JOptionPane;

/**
 * The ExceptionController handles all exceptions and displays an
 * error to the user.
 * @author Pacmans
 */
public class ExceptionController {

	/**
	 * Receives any exception from the Controller and 
	 * finds out what type the exception is.
	 * @param Exception e
	 */
	public static void recieveException(Exception e) {
		System.out.println("Error: " + e);
		
		if(e instanceof IOException)
			handleIOException((IOException) e);
		else if(e instanceof InterruptedException)
			handleInterruptedException((InterruptedException) e);
		else {
			printMessage(e.toString(), "Unknown error");
			System.exit(0);
		}
	}
	
	/**
	 * Informs the user of an IOException error 
	 * 
	 * @param ex
	 *            the IOException to show
	 */
	public static void handleIOException(IOException ex) {
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
	
	/**
	 * Informs the user of an InterruptedException error
	 * 
	 * @param ex
	 *            the InterruptedException to show
	 */
	public static void handleInterruptedException(InterruptedException ex) {
			// sets the title of the error window
			String title = "Thread error";
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
	
	/**
	 * Show a message dialog with the error.
	 */
	public static void printMessage(String message, String title) {
		JOptionPane.showMessageDialog(null, message, title,
		JOptionPane.ERROR_MESSAGE);
	}
}
