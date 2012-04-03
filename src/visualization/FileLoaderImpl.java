package visualization;

import java.io.BufferedReader;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;

import dataStructure.Connection;
import dataStructure.Point;
import dataStructure.RoadType;

//TODO Java doc

public class FileLoaderImpl implements FileLoader{
	private BigDecimal xMin = new BigDecimal(700000);
	private BigDecimal xMax = new BigDecimal(0);
	private BigDecimal yMin = new BigDecimal(700000);
	private BigDecimal yMax = new BigDecimal(0);
	private BigDecimal Scale = new BigDecimal(500);
	private int index;

	Connection[] connections = new Connection[812301];
	Point[] cords = new Point[675902];
	
	public FileLoaderImpl() throws IOException { 	
		
	File a = new File("C:/Users/Phillip/MapOfDenmark/src/Union/kdv_node_unload.txt");
	File b = new File("C:/Users/Phillip/MapOfDenmark/src/Union/kdv_unload.txt");

		BufferedReader inputA =  new BufferedReader(new FileReader(a));
		BufferedReader inputB =  new BufferedReader(new FileReader(b));
		System.out.println("files loaded");
	String line = null;
	 index = -1;
	 while ((line = inputA.readLine()) != null){
	     if (index == -1)index=0;
	     else{
	     String[] split = line.split(",");
	     Point p = new Point(index+1, new BigDecimal(split[3]).divide(Scale), new BigDecimal(split[4]).divide(Scale));
	     cords[index] = p;
	     //set max and min
	     if(cords[index].getX().compareTo(xMin)==1) xMin = cords[index].getX();
	     if(cords[index].getX().compareTo(xMax)==1) xMax = cords[index].getX();
	     if(cords[index].getY().compareTo(yMin)==1) yMin = cords[index].getY();
	     if(cords[index].getY().compareTo(yMax)==1) yMax = cords[index].getY();

	     index++;
	     }

	     
	 }
     System.out.println("Points created");
	 xMax = xMax.divide(Scale);
	 xMin = xMin.divide(Scale);
	 yMax = yMax.divide(Scale);
	 yMin = yMin.divide(Scale);
     index = -1;
     while ((line = inputB.readLine()) != null){  	 
	     if (index == -1)index = 0;
     	else{
	     String[] split =line.split(",");
	     RoadType r = null;
	    switch(Integer.parseInt(split[5])){
	    case 1: r = RoadType.HIGHWAY;
	    case 2: r = RoadType.EXPRESSWAY;
	    case 3: r = RoadType.PRIMARYWAY;
	    case 4: r = RoadType.SECONDARYWAY;
	    case 5: r = RoadType.ROAD3M;
	    case 6: r = RoadType.OTHERROAD;
	    case 8: r = RoadType.PATH;
	    case 10: r = RoadType.LANE;
	    case 11: r = RoadType.PEDESTRIAN;
	    case 21: r = RoadType.PROJHIGHWAY;
	    case 22: r = RoadType.PROJEXPRESSWAY;
	    case 23: r = RoadType.PROJPRIMARYWAY;
	    case 24: r = RoadType.PROJSECONDARYWAY;
	    case 25: r = RoadType.PROJROAD6M;
	    case 26: r = RoadType.PROJROAD3M;
	    case 28: r = RoadType.PROJPATH;
	    case 31: r = RoadType.HIGHWAYINTERSECTION;
	    case 32: r = RoadType.EXPRESSWAYEXIT;
	    case 33: r = RoadType.PRIMARYWAYINTERSECTION;
	    case 34: r = RoadType.SECONDARYWAYINTERSECTION;
	    case 35: r = RoadType.OTHERROADINTERSECTION;
	    case 41: r = RoadType.HIGHWAYTUNNEL;
	    case 42: r = RoadType.EXPRESSWAYTUNNEL;
	    case 43: r = RoadType.PRIMARYWAYTUNNEL;
	    case 44: r = RoadType.SECONDARYWAYTUNNEL;
	    case 45: r = RoadType.OTHERROADTUNNEL;
	    case 46: r = RoadType.SMALLROADTUNNEL;
	    case 48: r = RoadType.PATHTUNNEL;
	    case 80: r = RoadType.FERRY;
	    case 99: r = RoadType.UNKNOWN;
	    }
	    connections[index] = new Connection(cords[Integer.parseInt(split[0])-1],cords[Integer.parseInt(split[1])-1], r);
	    index++;
     	}
	     
	}
     System.out.println("Connections created"); 
}
	/* 
   * @see visualization.FileLoader#getxMax()
   */
	@Override
  public BigDecimal getxMax() {
		return xMax;
	}

	/* 
   * @see visualization.FileLoader#getyMax()
   */
	@Override
  public BigDecimal getyMax() {
		return yMax;
	}

	/* 
   * @see visualization.FileLoader#getConnections()
   */
	@Override
  public Connection[] getConnections() {
		return connections;
	}

	/* 
   * @see visualization.FileLoader#getCords()
   */
	@Override
  public Point[] getCords() {
		return cords;
	}
	
}
