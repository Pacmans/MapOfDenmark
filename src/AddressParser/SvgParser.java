package AddressParser;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;

public class SvgParser{
	private double xMin = 7000000, xMax = 0, yMin = 7000000, yMax = 0, scale = 500;

	public static void main(String[] args){
		SvgParser lol = new SvgParser();
		try {
			lol.writer();
		} catch (Exception e) {
			System.out.println(e);
		}

	}


	public void writer() throws Exception {
	File a = new File("C://Users//Phillip//Dropbox//Pacman//krak-data/kdv_node_unload.txt");
	File b = new File("C://Users//Phillip//Dropbox//Pacman//krak-data/kdv_unload.txt");

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
	for(int i = 1; i < cords.length; i++)
		if(lines[i][0]==null || lines[i][1]== null || lines[2][0]==null)
			System.out.println("lines[" + i + "][0] = " + lines[i][0] + "lines["+ i + "][1] = " + lines[i][1] + "lines[" + i + "][2] = " + lines[i][2]);
	for(int i=1; i< lines.length; i++)
		if(cords[lines[i][0]][0]==null || cords[lines[i][0]][1]==null || cords[lines[i][1]][0]==null || cords[lines[i][1]][1]==null)
			System.out.println(i+" "+cords[lines[i][0]][0]+" "+cords[lines[i][0]][1]+" "+cords[lines[i][1]][0]+" "+cords[lines[i][1]][1]);
	System.out.println("done part 3");
	Writer output = null;
	  File file = new File("færge.xml");
	  output = new BufferedWriter(new FileWriter(file));

	  output.write("<svg viewBox='0 0 1680 850' preserveAspectRatio='xMinYMin meet' xmlns='http://www.w3.org/2000/svg' version='1.1'>");
	  ((BufferedWriter) output).newLine();
	  int red = 0;
	  int green = 0;
	  int blue = 0;
	  int dash = 0;
	  double width = 0.01;
	  
	  for(int i = 1; i < lines.length-1; i++){
		  if (lines[i][2]==1 || lines[i][2]==21 || lines[i][2]==41 ) {red=255; green=127; blue=0; width = 3;}		  
		  else if (lines[i][2]==2 || lines[i][2]==22 || lines[i][2]==42 || lines[i][2]==31) {red=255; green=185; blue=0; width = 2;}		  
		  else if (lines[i][2]==3 || lines[i][2]==32 || lines[i][2]==43) {red=255; green=255; blue=0; width = 1;}		  
		  else if (lines[i][2]==4 || lines[i][2]==33 || lines[i][2]==44) {red=255; green=255; blue=0; width = 1;}		  
		  else if (lines[i][2]==5 || lines[i][2]==6 || lines[i][2]==25 || lines[i][2]==34 || lines[i][2]==45) {red=150; green=150; blue=150; width = 1;}
		  else if (lines[i][2]==10 || lines[i][2]==11 || lines[i][2]==26 || lines[i][2]==35 || lines[i][2]==46) {red=128; green=128; blue=128; width = 1;}
		  else if (lines[i][2]==8 || lines[i][2]==28 || lines[i][2]==48 || lines[i][2]==99) {red=0; green=200; blue=0; width = 1;}
		  else if (lines[i][2]==80) {red=128; green=128; blue=128; width = 1; dash=5; output.write("<line id='line' x1='"+ ((cords[lines[i][0]][0]/scale)-(xMin/scale)) +"' y1='-"+(((cords[lines[i][0]][1]/scale)* -1)+(yMax/scale)) +"' x2='"+((cords[lines[i][1]][0]/scale)-(xMin/scale)) +"' y2='-"+(((cords[lines[i][1]][1]/scale)* -1)+(yMax/scale)) +"' style='stroke:rgb("+red+","+green+","+blue+");stroke-width:"+width+"' stroke-dasharray='"+dash+","+dash+"'/>");
		  ((BufferedWriter) output).newLine();}
		  
	  }
	  output.write("</svg>");
	  output.close();
	  System.out.println("all done!");
	  
}
}