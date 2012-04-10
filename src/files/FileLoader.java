package files;

import java.math.BigDecimal;

import dataStructure.Connection;
import dataStructure.ConnectionQuadTree;
import dataStructure.Point;
import dataStructure.PointQuadTree;

/**
 * 
 * @author Claus L. Henriksen clih@itu.dk
 * @author Pacmans
 * @version 29. Marts 2012
 *
 */
public interface FileLoader {

  public abstract BigDecimal getxMax();

  public abstract BigDecimal getyMax();

  public abstract Connection[] getConnections();

  public abstract Point[] getCords();
	
  public abstract BigDecimal getxMin();
	
  public abstract BigDecimal getyMin();

  public abstract ConnectionQuadTree getConnectionQuadTree();
}