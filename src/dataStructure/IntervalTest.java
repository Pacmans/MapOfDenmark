package dataStructure;

import static org.junit.Assert.*;

import org.junit.Test;

public class IntervalTest {
  static Interval instance = new Interval(1, 4);

  @Test
  public void testContains() {
    assert(instance.contains(3));
  }

  @Test
  public void testIntersects() {
    Interval test = new Interval(1, 2);
    assert(instance.intersects(test));
  }
  
  @Test
  public void testIntersects2() {
    Interval test = new Interval(7, 9);
    assert(!instance.intersects(test));
  }

  @Test
  public void testGetLow() {
    assert(instance.getLow() == 1);
  }

  @Test
  public void testGetHigh() {
    assert(instance.getHigh() == 4);
  }
}
