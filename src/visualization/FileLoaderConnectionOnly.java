package visualization;

import java.math.BigDecimal;

import dataStructure.Connection;
import dataStructure.ConnectionQuadTree;
import dataStructure.Point;
import dataStructure.PointQuadTree;

public class FileLoaderConnectionOnly implements FileLoader {
  private BigDecimal xMin = new BigDecimal(700000);
  private BigDecimal xMax = new BigDecimal(0);
  private BigDecimal yMin = new BigDecimal(700000);
  private BigDecimal yMax = new BigDecimal(0);
  private BigDecimal Scale = new BigDecimal(750);
  private int index;
  private ConnectionQuadTree qt;

  Connection[] connections = new Connection[812301];
  Point[] cords = new Point[675902];
  
  public FileLoaderConnectionOnly(){
    
  }
  
  @Override
  public BigDecimal getxMax() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public BigDecimal getyMax() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Connection[] getConnections() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Point[] getCords() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public BigDecimal getxMin() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public BigDecimal getyMin() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public PointQuadTree getPointQuadTree() {
    // TODO Auto-generated method stub
    return null;
  }

}
