package files;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

import controller.Controller;

public class FileLoaderPostal implements Runnable {
  private HashMap<String, String> postal = Controller.getInstance().getPostal();
  
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
