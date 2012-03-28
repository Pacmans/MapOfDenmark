package Union;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;

public class SVGParser{
	private double xMin = 7000000, xMax = 0, yMin = 7000000, yMax = 0, scale = 500;
	
	
 public static void main(String[] args){
		SVGParser lol = new SVGParser();
		try {
			lol.writer();
		} catch (Exception e) {
			System.out.println(e);
		}

	}


	public void writer() throws Exception {
	File a = new File("C:/Users/Morten/workspace/MapOfDenmark/bin/xmlWriter/kdv_node_unload.txt");
	File b = new File("C:/Users/Morten/workspace/MapOfDenmark/bin/xmlWriter/kdv_unload.txt");

		BufferedReader inputA =  new BufferedReader(new FileReader(a));

		BufferedReader inputB =  new BufferedReader(new FileReader(b));

		Double[][] cords = new Double[675903][2];
		Integer[][] lines = new Integer[812302][3];
	String line = null;
	 int index = -1;
	 while ((line = inputA.readLine()) != null){
	     if (index == -1)index=1;
	     else{
	     String[] split = line.split(",");
	     cords[index][0]=Double.parseDouble(split[3]); //x-cord
	     cords[index][1]=Double.parseDouble(split[4]); //y-cord

	     //set max and min
	     if(cords[index][0] < xMin) xMin = cords[index][0];
	     if(cords[index][0] > xMax) xMax = cords[index][0];
	     if(cords[index][1] < yMin) yMin = cords[index][1];
	     if(cords[index][1] > yMax) yMax = cords[index][1];

	     index++;
	     }
	     	 }
	 System.out.println("done part 1");
	 
     index = -1;
     while ((line = inputB.readLine()) != null){
	     if (index == -1)index=1;
	     else{
	     String[] split = line.split(",");
	     lines[index][0]=Integer.parseInt(split[0]);
	     lines[index][1]=Integer.parseInt(split[1]);
	     lines[index][2]=Integer.parseInt(split[5]);
	     index++;
	     }
	}
	System.out.println("done part 2");
	Writer output = null;
	  File file = new File("write.txt");
	  output = new BufferedWriter(new FileWriter(file));

	  output.write("<svg xmlns='http://www.w3.org/2000/svg' version='1.1'>");
	  ((BufferedWriter) output).newLine();
	  for(int i = 1; i < lines.length-1; i++){
	  //output.write("<line id='line' x1='"+cords[lines[i][0]][0]+"' y1='"+cords[lines[i][0]][1]+"' x2='"+cords[lines[i][1]][0]+"' y2='"+cords[lines[i][1]][1]+"' style='stroke:rgb(255,0,0);stroke-width:"+lines[i][2]+"'/>");
	  output.write("<line id='line' x1='"+ ((cords[lines[i][0]][0]/scale)-(xMin/scale)) +"' y1='"+(((cords[lines[i][0]][1]/scale)* -1)+(yMax/scale)) +"' x2='"+((cords[lines[i][1]][0]/scale)-(xMin/scale)) +"' y2='"+(((cords[lines[i][1]][1]/scale)* -1)+(yMax/scale)) +"' style='stroke:rgb(255,0,0);stroke-width:"+1+"'/>");
	  ((BufferedWriter) output).newLine();
	  }
	  output.write("</svg>");
	  output.close();
	  System.out.println("all done!");
}
}