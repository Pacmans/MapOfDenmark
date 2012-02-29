package Union;

import java.math.*;
import java.util.HashMap;
import java.io.*;

public class TextReader {
	
	public static void main(String [ ] args)
	{
		try {
			TextReader.reader("C:\\Users\\Phillip\\Dropbox\\Pacman\\Krak-data\\kdv_node_unload.txt");
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
public static HashMap<Integer, Point> reader(String s) throws IOException{
	File a = new File(s);
	LineNumberReader  lnr = new LineNumberReader(new FileReader(a));
	lnr.skip(Long.MAX_VALUE);
	HashMap<Integer, Point> cords = new HashMap<Integer, Point>(lnr.getLineNumber()-1); 
	
	BufferedReader input =  new BufferedReader(new FileReader(a));
	
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