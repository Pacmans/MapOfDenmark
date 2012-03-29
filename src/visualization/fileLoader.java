package visualization;

import java.math.BigDecimal;

import dataStructure.Connection;
import dataStructure.Point;

public interface FileLoader {

  public abstract BigDecimal getxMax();

  public abstract BigDecimal getyMax();

  public abstract Connection[] getConnections();

  public abstract Point[] getCords();

}