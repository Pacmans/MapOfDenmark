package visualization;

import java.math.BigDecimal;

import dataStructure.Connection;
import dataStructure.Point;

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

}