package files;

import gui.GUI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;

import controller.Controller;
import dataStructure.Connection;
import dataStructure.ConnectionQuadTree;
import dataStructure.Point;
import dataStructure.RoadType;
import dataStructure.TernarySearchTries;

/**
 * 
 * @author Claus L. Henriksen clih@itu.dk
 * @author Phillip
 * @author Pacmans
 * @version 10. April 2012
 */	
public class FileLoaderConnectionOnly{

	private GUI gui = Controller.getGUI();
	private BigDecimal xMin;
	private BigDecimal xMax;
	private BigDecimal yMin;
	private BigDecimal yMax;
	private BigDecimal Scale = new BigDecimal(750); //hvad er dette?
	private ConnectionQuadTree highwaysQT;
	private ConnectionQuadTree expresswaysQT;
	private ConnectionQuadTree primaryQT;
	private ConnectionQuadTree secondaryQT;
	private ConnectionQuadTree normalQT;
	private ConnectionQuadTree trailsStreetsQT;
	private ConnectionQuadTree pathsQT;
	volatile private Connection[] connections = new Connection[812301];
	volatile private TernarySearchTries<Integer> tst;
	private Point[] points = new Point[675902];
	
	public FileLoaderConnectionOnly() throws IOException {
		loadPoints();
		loadConnections();
	}
	
	private void loadConnections() throws IOException
	{
		Thread highways = new Thread(new FileLoaderThread("highways",points, connections, highwaysQT, tst));
		Thread expressways = new Thread(new FileLoaderThread("expressways", points, connections, expresswaysQT, tst));
		Thread primary = new Thread(new FileLoaderThread("primary", points, connections, primaryQT, tst));
		
		highways.start();
		expressways.start();
		primary.start();
		try {
			highways.join();
			expressways.join();
			primary.join();
		} catch (InterruptedException e) {
		}
		
		gui.setupMap();
		
		Thread secondary = new Thread(new FileLoaderThread("secondary", points, connections, secondaryQT, tst));
		Thread normal = new Thread(new FileLoaderThread("normal", points, connections, normalQT, tst));
		Thread trailsStreets = new Thread(new FileLoaderThread("trailsStreets", points, connections, trailsStreetsQT, tst));
		Thread paths = new Thread(new FileLoaderThread("paths", points, connections, pathsQT, tst));
		
		secondary.start();
		normal.start();
		trailsStreets.start();
		paths.start();
		try {
			secondary.join();
			normal.join();
			trailsStreets.start();
			paths.start();
				}
		catch (Exception e) {
			
		}
		
	}
	
	/**
	 * creates Points from file "kdv_node_unload" and calculates min and max coordinates
	 * @throws IOException
	 */
	private void loadPoints() throws IOException 
	{
		//loads the file "kdv_node_unload"
		// File pointsFile = new File("./src/files/kdv_node_unload.txt");
		InputStream pointFile = getClass().getResourceAsStream("kdv_node_unload.txt");
		BufferedReader pointInput = new BufferedReader(new InputStreamReader(pointFile));
		
		String line = null;
		BigDecimal x, y;
		int index = -1;
		if (pointInput.ready()) { //if file is loaded
			while ((line = pointInput.readLine()) != null) {
				if (index >= 0){ //does nothing at the first line
					//creates the point
					String[] info = line.split(",");
					x = createBigDecimal(info[3]);
					y = createBigDecimal(info[4]);
					points[index] = new Point(index + 1, x, y);
					
					// sets max and min coordinates
					if (xMin != null){
						if (x.compareTo(xMin) == -1) xMin = x;
						if (x.compareTo(xMax) == 1)  xMax = x;
						if (y.compareTo(yMin) == -1) yMin = y;
						if (y.compareTo(yMax) == 1)  yMax = y;
					}
					else { //first time
						xMin = x;
						xMax = x;
						yMin = y;
						yMax = y;
					}
				}
				index++;
			}
		}
	}

	/**
	 * creates a correct BigDecimal from a String number
	 */
	private BigDecimal createBigDecimal(String number)
	{
		BigDecimal decimal = new BigDecimal(number);
		return decimal.divide(Scale, 2, RoundingMode.HALF_UP);
	}


	public BigDecimal getxMax() {
		return xMax;
	}


	public BigDecimal getyMax() {
		return yMax;
	}


	public Connection[] getConnections() {
		return connections;
	}


	public BigDecimal getxMin() {
		return xMin;
	}


	public BigDecimal getyMin() {
		return yMin;
	}

	public ConnectionQuadTree getHighwaysQT() {
		return highwaysQT;
	}
	
	public ConnectionQuadTree getExpresswaysQT() {
		return expresswaysQT;
	}
	
	public ConnectionQuadTree getPrimaryQT() {
		return primaryQT;
	}
	
	public ConnectionQuadTree getSecondaryQT() {
		return secondaryQT;
	}
	
	public ConnectionQuadTree getNormalQT() {
		return normalQT;
	}
	
	public ConnectionQuadTree getTrailsStreetsQT() {
		return trailsStreetsQT;
	}
	
	public ConnectionQuadTree getPaths() {
		return pathsQT;
	}

	public TernarySearchTries<Integer> getTst() {
		return tst;
	}
}