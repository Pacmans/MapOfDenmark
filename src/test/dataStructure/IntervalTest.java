package test.dataStructure;

import static org.junit.Assert.*;

import org.junit.Test;

import dataStructure.Interval;

public class IntervalTest {
  static Interval instance = new Interval(1, 4);

  @Test
  public void testContains() {
    assertTrue(instance.contains(3));
  }

  @Test
  public void testIntersects() {
    Interval test = new Interval(1, 2);
    assertTrue(instance.intersects(test));
  }
  
  @Test
  public void testIntersects2() {
    Interval test = new Interval(7, 9);
    assertFalse(instance.intersects(test));
  }

  @Test
  public void testGetLow() {
    assertTrue(instance.getLow() == 1);
  }

  @Test
  public void testGetHigh() {
    assertTrue(instance.getHigh() == 4);
  }
}
