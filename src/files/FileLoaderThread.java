package files;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileLoaderThread implements Runnable {

	private String txtname;
	
	public FileLoaderThread (String txtname) {
		this.txtname = txtname;
	}
	
	@Override
	public void run() {
		InputStream b = getClass().getResourceAsStream("txtname");
	    BufferedReader input = new BufferedReader(new InputStreamReader(b));
	    
		
	}

}
