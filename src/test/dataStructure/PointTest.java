package test.dataStructure;

import static org.junit.Assert.*;

import org.junit.Test;

import dataStructure.Point;

public class PointTest {
  Point instance = new Point(1, 2, 3); //id, x, y

  @Test
  public void testGetX() {
    assertEquals(instance.getX(), 2, 0.1);
  }

  @Test
  public void testGetY() {
    assertEquals(instance.getY(), 3, 0.1);
  }

  @Test
  public void testGetID() {
    assertEquals(instance.getID(), 1, 0.1);
  }

  @Test
  public void testGetZip() {
    int test = 2300;
    instance.setZip(test);
    assertEquals(test, instance.getZip());
  }

  @Test
  public void testCompareTo() {
    Point test = new Point(2, 3, 4);
    assertTrue(instance.compareTo(test) == -1);
  }
  
  @Test
  public void testCompareTo2() {
    Point test = new Point(0, 3, 4);
    assertTrue(instance.compareTo(test) == 1);
  }
  
  @Test
  public void testCompareTo3() {
    Point test = new Point(1, 3, 4);
    assertTrue(instance.compareTo(test) == 0);
  }
}
