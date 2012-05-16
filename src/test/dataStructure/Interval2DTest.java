package test.dataStructure;

import static org.junit.Assert.*;

import org.junit.Test;

import dataStructure.Interval;
import dataStructure.Interval2D;

public class Interval2DTest {
  Interval x = new Interval(1, 10);
  Interval y = new Interval(1, 10);
  Interval2D instance = new Interval2D(x, y);

  @Test
  public void testIntersects() {
    Interval2D test = new Interval2D(new Interval(1,4), new Interval(2,3));
    assertTrue(instance.intersects(test));
  }
  
  @Test
  public void testIntersects2() {
    Interval2D test = new Interval2D(new Interval(12,42), new Interval(22,32));
    assertFalse(instance.intersects(test));
  }

  @Test
  public void testContains() {
    assertTrue(instance.contains(3, 3));
  }

  @Test
  public void testGetIntervalX() {
    assertEquals(instance.getIntervalX(), x);
  }

  @Test
  public void testGetIntervalY() {
    assertEquals(instance.getIntervalY(), y);
  }
}
