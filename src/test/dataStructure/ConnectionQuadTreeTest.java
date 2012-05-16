package test.dataStructure;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;

import org.junit.Test;

import dataStructure.ConnectionQuadTree;
import dataStructure.DynArray;
import dataStructure.Interval;
import dataStructure.Interval2D;

public class ConnectionQuadTreeTest {
  ConnectionQuadTree instance = new ConnectionQuadTree();

  public ConnectionQuadTreeTest() {
    for(int i = 0; i < 100; i++){
      instance.insert(i, i, i);
    }
  }

  @Test
  public void testGetConnections() {
    Interval intX = new Interval(50, 70);
    Interval intY = new Interval(50, 70);
    DynArray<Integer> cs = instance.getConnections(new Interval2D(intX, intY));
    boolean passed = true;
    Iterator<Integer> it = cs.iterator();
    while(it.hasNext()){
      int i = it.next();
      if(i<50 || i>70) passed = false;
    }
    assertTrue(passed);
  }
  
  @Test
  public void testGetConnections2() {
    Interval intX = new Interval(50, 70);
    Interval intY = new Interval(50, 70);
    DynArray<Integer> cs = instance.getConnections(new Interval2D(intX, intY));
    boolean passed = true;
    Iterator<Integer> it = cs.iterator();
    while(it.hasNext()){
      int i = it.next();
      if(i>50 && i<70) passed = false;
    }
    assertFalse(passed);
  }
}
