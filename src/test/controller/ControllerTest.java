/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test.controller;

import dataStructure.Connection;
import gui.GUI;
import org.junit.*;

import controller.Controller;
import static org.junit.Assert.*;
import visualization.MapComponent;

/**
 *
 * @author Admin
 */
public class ControllerTest {
    static Controller instance = Controller.getInstance();

    public ControllerTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getInstance method, of class Controller.
     */
    @Test
    public void testGetInstance() {
        System.out.println("getInstance");
        Controller expResult = instance;
        Controller result = Controller.getInstance();
        assertEquals(expResult, result);
    }

    /**
     * Test of getGUI method, of class Controller.
     */
    @Test
    public void testGetGUI() {
        System.out.println("getGUI");
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
        System.out.println("getMap");
        MapComponent expResult = instance.getMap();
        MapComponent result = instance.getMap();
        assertEquals(expResult, result);
    }

//    /**
//     * Test of getConnections method, of class Controller.
//     */
//    @Test
//    public void testGetConnections_4args() {
//        System.out.println("getConnections");
//        double x1 = 0.0;
//        double y1 = 0.0;
//        double x2 = 0.0;
//        double y2 = 0.0;
//        Connection[] expResult = null;
//        Connection[] result = instance.getConnections(x1, y1, x2, y2);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getConnections method, of class Controller.
//     */
//    @Test
//    public void testGetConnections_0args() {
//        System.out.println("getConnections");
//        Controller instance = new Controller();
//        Connection[] expResult = null;
//        Connection[] result = instance.getConnections();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of updateMap method, of class Controller.
//     */
//    @Test
//    public void testUpdateMap() {
//        System.out.println("updateMap");
//        int n = 0;
//        boolean m = false;
//        Controller instance = new Controller();
//        instance.updateMap(n, m);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of showAll method, of class Controller.
//     */
//    @Test
//    public void testShowAll() {
//        System.out.println("showAll");
//        Controller instance = new Controller();
//        instance.showAll();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of getxMin method, of class Controller.
     */
    @Test
    public void testGetxMin() {
        System.out.println("getxMin");
        double expResult = 589;
        double result = instance.getxMin();
        assertEquals(expResult, result, 1.0);
    }

    /**
     * Test of getyMin method, of class Controller.
     */
    @Test
    public void testGetyMin() {
        System.out.println("getyMin");
        double expResult = 8066;
        double result = instance.getyMin();
        assertEquals(expResult, result, 1.0);
    }

    /**
     * Test of getxMax method, of class Controller.
     */
    @Test
    public void testGetxMax() {
        System.out.println("getxMax");
        double expResult = 1190;
        double result = instance.getxMax();
        assertEquals(expResult, result, 1.0);
    }

    /**
     * Test of getyMax method, of class Controller.
     */
    @Test
    public void testGetyMax() {
        System.out.println("getyMax");
        double expResult = 8536;
        double result = instance.getyMax();
        assertEquals(expResult, result, 1.0);
    }
}