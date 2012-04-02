package visualization;

import java.awt.Graphics;

/**
 * 
 * @author Claus L. Henriksen clih@itu.dk
 * @author Pacmans
 * @version 29. Marts 2012
 *
 */
public interface OurMap {

  public abstract void paint(Graphics g);

  public abstract void updateFilter(int n, boolean b);

}