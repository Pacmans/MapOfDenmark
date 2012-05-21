package test.dataStructure;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import dataStructure.Connection;
import dataStructure.Point;
import dataStructure.RoadType;

/**
 * 
 * @author Pacmans
 */
public class ConnectionTest {
  static Connection instance = null;
  static Point p1, p2;

  public ConnectionTest() {
  }

  @BeforeClass
  public static void setUpClass() throws Exception {
    p1 = new Point(1, 5, 7); // id, x, y
    p2 = new Point(2, 7, 5); // id, x, y
    instance = new Connection(1, // ID
        p1, p2, //points 
        RoadType.EXPRESSWAY, // type
        "Test road", // name
        20); // speed limit
  }

  /**
   * Test of getID method, of class Connection.
   */
  @Test
  public void testGetID() {
    int expResult = 1;
    int result = instance.getID();
    assertEquals(expResult, result);
  }

  /**
   * Test of getX1 method, of class Connection.
   */
  @Test
  public void testGetX1() {
    double expResult = 5;
    double result = instance.getX1();
    assertEquals(expResult, result, 0.0);
  }

  /**
   * Test of getX2 method, of class Connection.
   */
  @Test
  public void testGetX2() {
    double expResult = 7;
    double result = instance.getX2();
    assertEquals(expResult, result, 0.0);
  }

  /**
   * Test of getY1 method, of class Connection.
   */
  @Test
  public void testGetY1() {
    double expResult = 7;
    double result = instance.getY1();
    assertEquals(expResult, result, 0.0);
  }

  /**
   * Test of getY2 method, of class Connection.
   */
  @Test
  public void testGetY2() {
    double expResult = 5;
    double result = instance.getY2();
    assertEquals(expResult, result, 0.0);
  }

  /**
   * Test of getType method, of class Connection.
   */
  @Test
  public void testGetType() {
    RoadType expResult = RoadType.EXPRESSWAY;
    RoadType result = instance.getType();
    assertEquals(expResult, result);
  }

  @Test
  public void testGetName() {
    String expResult = "Test road";
    String result = instance.getName();
    assertEquals(expResult, result);
  }
  
  @Test
  public void testGetLeft() {
    Point expResult = p1;
    Point result = instance.getLeft();
    assertEquals(expResult, result);
  }
  
  @Test
  public void testGetRight() {
    Point expResult = p2;
    Point result = instance.getRight();
    assertEquals(expResult, result);
  }
  
  @Test
  public void testGetLength() {
    double expResult = 2.828;
    double result = instance.getLength();
    assertEquals(expResult, result, 0.1);
  }
  
  @Test
  public void testGetWeight1() {
    double expResult = 14.14;
    double result = instance.getWeight();
    assertEquals(expResult, result, 0.01);
  }
  
  @Test
  public void testGetWeight2() {
    Connection test = new Connection(5,
        new Point(8, 2, 3), //id, x, y
        new Point(7, 2, 4), //id, x, y
        RoadType.EXPRESSWAY, //type
        null, //name
        20); //speedlimit
      assertTrue(test.getLength()<instance.getLength()); 
  }
  
  @Test
  public void testLeftRight() {
    double x1 = instance.getLeft().getX();
    double x2 = instance.getRight().getX();
    assertTrue(x1<x2);
  }

  /**
   * Test of compareTo method, of class Connection.
   */
  @Test
  public void testCompareTo() {
    Connection con = new Connection(2, new Point(1, 0, 0), new Point(2, 0, 0),
        null, null, 0);
    int expResult = -1;
    int result = instance.compareTo(con);
    assertEquals(expResult, result);
  }
}
