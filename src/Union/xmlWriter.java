package Union;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;

public class xmlWriter{
	private double xMin = 9000000, yMin = 9000000, yMax = 0; //setting very big and very small numbers

	public static void main(String[] args){
		xmlWriter lol = new xmlWriter();
		try {
			lol.writer();
		} catch (Exception e) {
			System.out.println(e);
		}

	}


	public void writer() throws Exception {
	File a = new File("./kdv_node_unload.txt");
	File b = new File("./kdv_unload.txt");

		BufferedReader inputA =  new BufferedReader(new FileReader(a));

		BufferedReader inputB =  new BufferedReader(new FileReader(b));

		Double[][] cords = new Double[675903][2];
		Integer[][] lines = new Integer[812302][3];
	String line = null;
	 int index = -1;
	 while ((line = inputA.readLine()) != null){
	     if (index == -1)index++;
	     else{
	     String[] split = line.split(",");
	     cords[index][0]=Double.parseDouble(split[3]); //x-cord
	     cords[index][1]=Double.parseDouble(split[4]); //y-cord

	     //set max and min
	     if(cords[index][0] < xMin) xMin = cords[index][0];
	     if(cords[index][1] < yMin) yMin = cords[index][1];
	     if(cords[index][1] > yMax) yMax = cords[index][1];

	     index++;
	     }
	 }
	 System.out.println("done part 1");
	 System.out.println("xMin " + xMin);
	 System.out.println("yMin " + yMin);
	 System.out.println("yMax " + yMax);
     index = -1;
     while ((line = inputB.readLine()) != null){
	     if (index == -1)index++;
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

	  output.write("<roaddata>Danmark");
	  ((BufferedWriter) output).newLine();
	  for(int i = 0; i < lines.length-1; i++){
	  output.write("<connection xmlns:xsi='file:./KRAX.xsd'>connecten "+i);
	  ((BufferedWriter) output).newLine();
	  output.write("<xcoord1>"+(cords[lines[i][0]][0] - xMin + 10)+"</xcoord1>");
	  ((BufferedWriter) output).newLine();
	  output.write("<ycoord1>"+((cords[lines[i][0]][1]*-1)+ yMax)+"</ycoord1>");
	  ((BufferedWriter) output).newLine();
	  output.write("<xcoord2>"+(cords[lines[i][1]][0] - xMin + 10)+"</xcoord2>");
	  ((BufferedWriter) output).newLine();
	  output.write("<ycoord2>"+((cords[lines[i][1]][1]*-1) + yMax) +"</ycoord2>");
	  ((BufferedWriter) output).newLine();
	  output.write("<rtype>"+lines[i][2]+"</rtype>");
	  ((BufferedWriter) output).newLine();
	  output.write("<annotation>");
	  ((BufferedWriter) output).newLine();
	  output.write("<documentation>");
	  ((BufferedWriter) output).newLine();
	  output.write("<svg xmlns='http://www.w3.org/2000/svg' version='1.1'>");
	  ((BufferedWriter) output).newLine();
	  //output.write("<line id='line' x1='"+cords[lines[i][0]][0]+"' y1='"+cords[lines[i][0]][1]+"' x2='"+cords[lines[i][1]][0]+"' y2='"+cords[lines[i][1]][1]+"' style='stroke:rgb(255,0,0);stroke-width:"+lines[i][2]+"'/>");
	  output.write("<line id='line' x1='"+(cords[lines[i][0]][0] - xMin + 10)+"' y1='"+((cords[lines[i][0]][1]*-1) + yMax) +"' x2='"+(cords[lines[i][1]][0] - xMin + 10) +"' y2='"+((cords[lines[i][1]][1] * -1) + yMax) +"' style='stroke:rgb(255,0,0);stroke-width:"+lines[i][2]+"'/>");
	  ((BufferedWriter) output).newLine();
	  output.write("</svg>");
	  ((BufferedWriter) output).newLine();
	  output.write("</documentation>");
	  ((BufferedWriter) output).newLine();
	  output.write("</annotation>");
	  ((BufferedWriter) output).newLine();
	  output.write("</connection>");
	  ((BufferedWriter) output).newLine();
	  }
	  output.write("</roaddata>");
	  output.close();
	  System.out.println("all done!");
}
}





