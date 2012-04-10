package files;

import java.math.BigDecimal;

import dataStructure.Connection;
import dataStructure.ConnectionQuadTree;
import dataStructure.Point;

public class FileLoaderSmart implements FileLoader {
  private BigDecimal xMin = new BigDecimal(700000);
  private BigDecimal xMax = new BigDecimal(0);
  private BigDecimal yMin = new BigDecimal(700000);
  private BigDecimal yMax = new BigDecimal(0);
  private BigDecimal Scale = new BigDecimal(750);
  private int index;
  private ConnectionQuadTree cqt;
  private Connection[] connections = new Connection[812301];
  
  public FileLoaderSmart(){
    
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
