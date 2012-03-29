package visualization;

import java.awt.Graphics;

public interface OurMap {

  public abstract void paint(Graphics g);

  public abstract void updateFilter(int n, boolean b);

}