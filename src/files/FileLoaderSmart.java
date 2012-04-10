package files;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;

import dataStructure.Connection;
import dataStructure.ConnectionQuadTree;
import dataStructure.RoadType;

public class FileLoaderSmart implements FileLoader {
  private BigDecimal xMin = new BigDecimal(700000);
  private BigDecimal xMax = new BigDecimal(0);
  private BigDecimal yMin = new BigDecimal(700000);
  private BigDecimal yMax = new BigDecimal(0);
  private BigDecimal Scale = new BigDecimal(750);
  private ConnectionQuadTree cqt = new ConnectionQuadTree();
  private Connection[] connections = new Connection[812301];
  
  public FileLoaderSmart() throws IOException{
    File a = new File(System.getProperty("user.dir") + "/src/files/smart_data.txt");
    BufferedReader input = new BufferedReader(new FileReader(a));
    
    String line = null; 
    int index = 0;
    while ((line = input.readLine()) != null) {
      String[] split = line.split(",");
      
      RoadType r;
      switch (Integer.parseInt(split[4])) {
      case 1:
        r = RoadType.HIGHWAY;
        break;
      case 2:
        r = RoadType.EXPRESSWAY;
        break;
      case 3:
        r = RoadType.PRIMARYWAY;
        break;
      case 4:
        r = RoadType.SECONDARYWAY;
        break;
      case 5:
        r = RoadType.ROAD3M;
        break;
      case 6:
        r = RoadType.OTHERROAD;
        break;
      case 8:
        r = RoadType.PATH;
        break;
      case 10:
        r = RoadType.LANE;
        break;
      case 11:
        r = RoadType.PEDESTRIAN;
        break;
      case 21:
        r = RoadType.PROJHIGHWAY;
        break;
      case 22:
        r = RoadType.PROJEXPRESSWAY;
        break;
      case 23:
        r = RoadType.PROJPRIMARYWAY;
        break;
      case 24:
        r = RoadType.PROJSECONDARYWAY;
        break;
      case 25:
        r = RoadType.PROJROAD6M;
        break;
      case 26:
        r = RoadType.PROJROAD3M;
        break;
      case 28:
        r = RoadType.PROJPATH;
        break;
      case 31:
        r = RoadType.HIGHWAYINTERSECTION;
        break;
      case 32:
        r = RoadType.EXPRESSWAYEXIT;
        break;
      case 33:
        r = RoadType.PRIMARYWAYINTERSECTION;
        break;
      case 34:
        r = RoadType.SECONDARYWAYINTERSECTION;
        break;
      case 35:
        r = RoadType.OTHERROADINTERSECTION;
        break;
      case 41:
        r = RoadType.HIGHWAYTUNNEL;
        break;
      case 42:
        r = RoadType.EXPRESSWAYTUNNEL;
        break;
      case 43:
        r = RoadType.PRIMARYWAYTUNNEL;
        break;
      case 44:
        r = RoadType.SECONDARYWAYTUNNEL;
        break;
      case 45:
        r = RoadType.OTHERROADTUNNEL;
        break;
      case 46:
        r = RoadType.SMALLROADTUNNEL;
        break;
      case 48:
        r = RoadType.PATHTUNNEL;
        break;
      case 80:
        r = RoadType.FERRY;
        break;
      case 99:
        r = RoadType.UNKNOWN;
        break;
      default:
        r = RoadType.HIGHWAY;
        break;
      }
      //add connection to data structures
      BigDecimal x1 = new BigDecimal(split[0]);
      BigDecimal y1 = new BigDecimal(split[1]);
      BigDecimal x2 = new BigDecimal(split[2]);
      BigDecimal y2 = new BigDecimal(split[3]);
      connections[index] = new Connection(index, x1, y1, x2, y2, r);
      cqt.insert(x1, y1, index);
      cqt.insert(x2, y2, index);
      index++;
      
      //set max and min values
      if(x1.compareTo(xMin) < 0) xMin = x1;
      else if(x1.compareTo(xMax) > 0) xMax = x1;
      if(x2.compareTo(xMin) < 0) xMin = x2;
      else if(x2.compareTo(xMax) > 0) xMax = x2;
      
      if(y1.compareTo(yMin) < 0) yMin = y1;
      else if(y1.compareTo(yMax) > 0) yMax = y1;
      if(y2.compareTo(yMin) < 0) yMin = y2;
      else if(y2.compareTo(yMax) > 0) yMax = y2;
    }
    System.out.println("Connections loaded");
  }

  @Override
  public BigDecimal getxMax() {
    return xMax;
  }

  @Override
  public BigDecimal getyMax() {
    return yMax;
  }

  @Override
  public Connection[] getConnections() {
    return connections;
  }

  @Override
  public BigDecimal getxMin() { 
    return xMin;
  }

  @Override
  public BigDecimal getyMin() {
    return yMin;
  }

  @Override
  public ConnectionQuadTree getConnectionQuadTree() {
    return cqt;
  }

}
