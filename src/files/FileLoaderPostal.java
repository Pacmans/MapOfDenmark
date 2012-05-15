package files;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

import controller.Controller;

/**
 * Creates a new thread which creates a HashMap - postal 
 * of the postal codes in the postal.text file.
 * @author Pacmans
 */
public class FileLoaderPostal implements Runnable {
  private HashMap<String, String> postal = Controller.getInstance().getPostal();
  
  /**
   *	The method to execute when this thread is started.
   *	It splits each line and puts into the HashMap
   */
  @Override
  public void run() {
    InputStream postalFile = getClass().getResourceAsStream("postal.txt");
    BufferedReader input = new BufferedReader(new InputStreamReader(postalFile));
    
    String line = null;
    try {
      while((line = input.readLine()) != null){
        String[] split = line.split(" ");
        postal.put(split[0], split[1]);
      }
    } catch (IOException e) {
      Controller.catchException(e);
    }
  }
}
