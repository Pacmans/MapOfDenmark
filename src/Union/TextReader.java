package Union;

import java.math.*;
import java.util.HashMap;
import java.io.*;

public class TextReader {
	
	public static void main(String [ ] args)
	{
		try {
			TextReader.reader(System.in);
		} catch (IOException e) {
			System.out.println("file does not exist or wrong direction");
		} 
	}
		

	//Reads the given .txt file and prints out X-Cord and Y-Cord in a BigDecimal 2D Array.
	//it skips the first line.
	//iD 1 from file equals ID 0 in cords array.
	//Throws IOException ("File does not exist or wrong direction")
	//@param	s 		file location
	//@return	cords	coordinates of X and Y for knobs
public static HashMap<Integer, Point> reader(InputStream s) throws IOException{
	InputStreamReader irs = new InputStreamReader(s);
	LineNumberReader  lnr = new LineNumberReader(irs);
	lnr.skip(Long.MAX_VALUE);
	HashMap<Integer, Point> cords = new HashMap<Integer, Point>(lnr.getLineNumber()-1); 
	
	BufferedReader input =  new BufferedReader(irs);
	
	String line = null;
	 int index = -1;
	 while ((line = input.readLine()) != null){
	     if (index == -1)index++;
	     else{
	     String[] split = line.split(",");
	     cords.put(index, new Point(Integer.parseInt(split[2]), new BigDecimal(split[3]), new BigDecimal(split[4])));
	     index++;

	     }
	     
	 }
	 return cords;}
	}