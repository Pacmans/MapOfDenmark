package files;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import dataStructure.Connection;
import dataStructure.ConnectionQuadTree;
import dataStructure.Point;
import dataStructure.RoadType;
import dataStructure.TernarySearchTries;

public class FileLoaderThread implements Runnable {

	private String txtname;
	private Point[] points;
	private Connection[] connections;
	private ConnectionQuadTree qtr;
	private TernarySearchTries<Integer> tst;
	
	public FileLoaderThread (String txtname, Point[] points, Connection[] connections, ConnectionQuadTree qtr, TernarySearchTries<Integer> tst) {
		this.txtname = txtname;
		this.points = points;
		this.connections = connections;
		this.qtr = qtr;
		this.tst = tst;
	}
	
	@Override
	public void run() {
		try {
		makeQT();
			}
		catch (Exception e) {
			
		}
	}
	
	private void makeQT () throws IOException {
		InputStream b = getClass().getResourceAsStream(txtname+".txt");
	    BufferedReader connectionInput = new BufferedReader(new InputStreamReader(b));
	    String line;
		int index = -1;
		Point p1, p2;
		qtr = new ConnectionQuadTree();
		
		if (connectionInput.ready()) { //if loaded
			while ((line = connectionInput.readLine()) != null) {
				if (index >= 0){ //ignores the first line
					String[] split = line.split(",");
					//gets the correct RoadType
					RoadType r = getRoadType(Integer.parseInt(split[5]));
					
					//gets the points associated with the connection
					p1 = points[Integer.parseInt(split[0]) - 1];
					p2 = points[Integer.parseInt(split[1]) - 1];

					//creates and saves the Connection
					connections[index] = new Connection(index, p1.getX(), 
							p1.getY(), p2.getX(), p2.getY(), r, split[6]);

					//adds p1 and p2 to the quadtree
					qtr.insert(p1.getX(), p1.getY(), index);
					qtr.insert(p2.getX(), p2.getY(), index);
					tst.put(split[6], index);
				}
				index++;
			}
		}


	}
	
	private RoadType getRoadType(int type)
	{
		switch (type) {
		case 1: return RoadType.HIGHWAY;
		case 2: return RoadType.EXPRESSWAY;
		case 3: return RoadType.PRIMARYWAY;
		case 4: return RoadType.SECONDARYWAY;
		case 5: return RoadType.ROAD3M;
		case 6: return RoadType.OTHERROAD;
		case 8: return RoadType.PATH;
		case 10: return RoadType.LANE;
		case 11: return RoadType.PEDESTRIAN;
		case 21: return RoadType.PROJHIGHWAY;
		case 22: return RoadType.PROJEXPRESSWAY;
		case 23: return RoadType.PROJPRIMARYWAY;
		case 24: return RoadType.PROJSECONDARYWAY;
		case 25: return RoadType.PROJROAD6M;
		case 26: return RoadType.PROJROAD3M;
		case 28: return RoadType.PROJPATH;
		case 31: return RoadType.HIGHWAYINTERSECTION;
		case 32: return RoadType.EXPRESSWAYEXIT;
		case 33: return RoadType.PRIMARYWAYINTERSECTION;
		case 34: return RoadType.SECONDARYWAYINTERSECTION;
		case 35: return RoadType.OTHERROADINTERSECTION;
		case 41: return RoadType.HIGHWAYTUNNEL;
		case 42: return RoadType.EXPRESSWAYTUNNEL;
		case 43: return RoadType.PRIMARYWAYTUNNEL;
		case 44: return RoadType.SECONDARYWAYTUNNEL;
		case 45: return RoadType.OTHERROADTUNNEL;
		case 46: return RoadType.SMALLROADTUNNEL;
		case 48: return RoadType.PATHTUNNEL;
		case 80: return RoadType.FERRY;
		case 99: return RoadType.UNKNOWN;
		default: return RoadType.UNKNOWN;
		}
	}
}