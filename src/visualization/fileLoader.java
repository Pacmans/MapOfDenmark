package visualization;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;

import dataStructure.Connection;
import dataStructure.Point;
import dataStructure.RoadType;

public class fileLoader{
	private BigDecimal xMin = new BigDecimal(700000);
	private BigDecimal xMax = new BigDecimal(0);
	private BigDecimal yMin = new BigDecimal(700000);
	private BigDecimal yMax = new BigDecimal(0);
	private BigDecimal Scale = new BigDecimal(500);
	
	

	Object[] connections = new Object[812302];
	

	BigDecimal[][] cords = new BigDecimal[675903][2];
	
	public fileLoader() throws IOException {
	
		
	File a = new File("C:/Users/Phillip/MapOfDenmark/src/Union/kdv_node_unload.txt");
	File b = new File("C:/Users/Phillip/MapOfDenmark/src/Union/kdv_unload.txt");

		BufferedReader inputA =  new BufferedReader(new FileReader(a));
		BufferedReader inputB =  new BufferedReader(new FileReader(b));

	String line = null;
	 int index = -1;
	 while ((line = inputA.readLine()) != null){
	     if (index == -1)index=1;
	     else{
	     String[] split = line.split(",");
	     cords[index][0]= new BigDecimal(split[3]);
	     cords[index][1]= new BigDecimal(split[4]);

	     //set max and min
	     if(cords[index][0].compareTo(xMin)==1) xMin = cords[index][0];
	     if(cords[index][0].compareTo(xMax)==1) xMax = cords[index][0];
	     if(cords[index][1].compareTo(yMin)==1) yMin = cords[index][1];
	     if(cords[index][1].compareTo(yMax)==1) yMax = cords[index][1];

	     index++;
	     }
	 }
     index = -1;
     while ((line = inputB.readLine()) != null){
    	 
	     if (index == -1)index = 0;
     	else{
	     String[] split =line.split(",");
	     Point p = new Point(Integer.parseInt(split[0]),cords[Integer.parseInt(split[0])][0].divide(Scale),cords[Integer.parseInt(split[0])][1].divide(Scale));
	     Point q = new Point(Integer.parseInt(split[1]),cords[Integer.parseInt(split[1])][0].divide(Scale),cords[Integer.parseInt(split[1])][1].divide(Scale));
	     RoadType r = null;
	    switch(Integer.parseInt(split[5])){
	    case 1: r = RoadType.MOTORVEJ;
	    case 2: r = RoadType.MOTORTRAFIKVEJ;
	    case 3: r = RoadType.PRIMÆRRUTE;
	    case 4: r = RoadType.SEKUNDÆRRUTE;
	    case 5: r = RoadType.VEJ3;
	    case 6: r = RoadType.ANDENVEJ;
	    case 8: r = RoadType.STI;
	    case 10: r = RoadType.MARKVEJ;
	    case 11: r = RoadType.GÅGADER;
	    case 21: r = RoadType.PROJMOTVEJ;
	    case 22: r = RoadType.PROJMOTORTRAFIKVEJ;
	    case 23: r = RoadType.PROJPRIMÆRVEJ;
	    case 24: r = RoadType.PROJSEKUNDÆRRUTE;
	    case 25: r = RoadType.PROJVEJ6M;
	    case 26: r = RoadType.PROJVEJ3M;
	    case 28: r = RoadType.PROJSTI;
	    case 31: r = RoadType.MOTORVEJSAFKØRSEL;
	    case 32: r = RoadType.MOTORTRAFIKVEJSAFKØRSEL;
	    case 33: r = RoadType.PRIMÆRVEJSAFKØRSEL;
	    case 34: r = RoadType.SEKUNDERVEJSAFKØRSEL;
	    case 35: r = RoadType.ANDENVEJAFKØRESL;
	    case 41: r = RoadType.MOTORVEJSTUNNEL;
	    case 42: r = RoadType.MOTORTRAFIKVEJSTUNNEL;
	    case 43: r = RoadType.PRIMÆRVEJSTUNNEL;
	    case 44: r = RoadType.SEKUNDÆRVEJSTUNNEL;
	    case 45: r = RoadType.ANDENVEJTUNNEL;
	    case 46: r = RoadType.MINDREVEJTUNNEL;
	    case 48: r = RoadType.STITUNNEL;
	    case 80: r = RoadType.FÆRGE;
	    case 99: r = RoadType.UKENDT;
	    }
	    connections[index] = new Connection(p,q, r);
	    index++;
     	}    
	}
     
}
	public BigDecimal getxMax() {
		return xMax;
	}

	public BigDecimal getyMax() {
		return yMax;
	}

	public Object[] getConnections() {
		return connections;
	}

	public BigDecimal[][] getCords() {
		return cords;
	}
}
