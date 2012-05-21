package test.controller;

import dataStructure.Connection;
import dataStructure.ConnectionQuadTree;
import dataStructure.Point;
import dataStructure.TernarySearchTries;
import graph.Graph;
import gui.GUI;
import org.junit.*;

import controller.Controller;
import static org.junit.Assert.*;
import visualization.MapComponent;
import visualization.SliderComponent;

/**
 * 
 * @author Pacmans
 */
public class ControllerTest {
  static Controller instance = Controller.getInstance();
  
  //TODO Test getConnections, getRoads, getRoadPlan

  public ControllerTest() {
  }

  /**
   * Test of getInstance method, of class Controller.
   */
  @Test
  public void testGetInstance() {
    Controller expResult = instance;
    Controller result = Controller.getInstance();
    assertEquals(expResult, result);
  }

  /**
   * Test of getGUI method, of class Controller.
   */
  @Test
  public void testGetGUI() {
    GUI gui = Controller.getInstance().getGUI();
    GUI expResult = gui;
    GUI result = instance.getGUI();
    assertEquals(expResult, result);
  }

  /**
   * Test of getMap method, of class Controller.
   */
  @Test
  public void testGetMap() {
    MapComponent expResult = instance.getMap();
    MapComponent result = instance.getMap();
    assertEquals(expResult, result);
  }

  @Test
  public void testGetGraph() {
    Graph expResult = instance.getGraph();
    Graph result = instance.getGraph();
    assertEquals(expResult, result);
  }

  @Test
  public void testGetTST() {
    TernarySearchTries<Integer> expResult = instance.getTst();
    TernarySearchTries<Integer> result = instance.getTst();
    assertEquals(expResult, result);
  }

  @Test
  public void testGetSlider() {
    SliderComponent expResult = instance.getSlider();
    SliderComponent result = instance.getSlider();
    assertEquals(expResult, result);
  }
  
  @Test
  public void testGetConnections() {
    Connection[] expResult = instance.getConnections();
    Connection[] result = instance.getConnections();
    assertArrayEquals(expResult, result);
  }
  
  @Test
  public void testGetPoints() {
    Point[] expResult = instance.getPoints();
    Point[] result = instance.getPoints();
    assertArrayEquals(expResult, result);
  }

  /**
   * Test of getxMin method, of class Controller.
   */
  @Test
  public void testGetxMin() {
    double expResult = 589;
    double result = instance.getxMin();
    assertEquals(expResult, result, 1.0);
  }

  /**
   * Test of getyMin method, of class Controller.
   */
  @Test
  public void testGetyMin() {
    double expResult = 8066;
    double result = instance.getyMin();
    assertEquals(expResult, result, 1.0);
  }

  /**
   * Test of getxMax method, of class Controller.
   */
  @Test
  public void testGetxMax() {
    double expResult = 1190;
    double result = instance.getxMax();
    assertEquals(expResult, result, 1.0);
  }

  /**
   * Test of getyMax method, of class Controller.
   */
  @Test
  public void testGetyMax() {
    double expResult = 8536;
    double result = instance.getyMax();
    assertEquals(expResult, result, 1.0);
  }
  
  @Test
  public void testSetxMin() {
    instance.setxMin(2);
    double expResult = 2;
    double result = instance.getxMin();
    assertEquals(expResult, result, 0.1);
  }
  
  @Test
  public void testsetyMin() {
    instance.setyMin(3);
    double expResult = 3;
    double result = instance.getyMin();
    assertEquals(expResult, result, 0.1);
  }
  
  @Test
  public void testSetxMax() {
    instance.setxMax(200);
    double expResult = 200;
    double result = instance.getxMax();
    assertEquals(expResult, result, 0.1);
  }
  
  @Test
  public void testSetyMax() {
    instance.setyMax(150);
    double expResult = 150;
    double result = instance.getyMax();
    assertEquals(expResult, result, 0.1);
  }
  
  @Test
  public void testQT1() {
    ConnectionQuadTree expResult = instance.getHighwaysQT();
    ConnectionQuadTree result = instance.getHighwaysQT();
    assertEquals(expResult, result);
  }
  
  @Test
  public void testQT2() {
    ConnectionQuadTree expResult = instance.getExpresswaysQT();
    ConnectionQuadTree result = instance.getExpresswaysQT();
    assertEquals(expResult, result);
  }
  
  @Test
  public void testQT3() {
    ConnectionQuadTree expResult = instance.getPrimaryQT();
    ConnectionQuadTree result = instance.getPrimaryQT();
    assertEquals(expResult, result);
  }
  
  @Test
  public void testQT4() {
    ConnectionQuadTree expResult = instance.getSecondaryQT();
    ConnectionQuadTree result = instance.getSecondaryQT();
    assertEquals(expResult, result);
  }
  
  @Test
  public void testQT5() {
    ConnectionQuadTree expResult = instance.getNormalQT();
    ConnectionQuadTree result = instance.getNormalQT();
    assertEquals(expResult, result);
  }
  
  @Test
  public void testQT6() {
    ConnectionQuadTree expResult = instance.getSmallQT();
    ConnectionQuadTree result = instance.getSmallQT();
    assertEquals(expResult, result);
  }
  
  @Test
  public void testQT7() {
    ConnectionQuadTree expResult = instance.getPathsQT();
    ConnectionQuadTree result = instance.getPathsQT();
    assertEquals(expResult, result);
  }
  
  @Test
  public void testSetConnections() {
    Connection[] result = new Connection[2];
    instance.setConnections(result);
    Connection[] expResult = instance.getConnections();
    assertArrayEquals(expResult, result);
  }
  
  @Test
  public void testSetPoint() {
    Point[] result = new Point[2];
    instance.setPoints(result);
    Point[] expResult = instance.getPoints();
    assertArrayEquals(expResult, result);
  }
}