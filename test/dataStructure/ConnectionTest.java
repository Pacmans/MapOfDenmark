/** To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dataStructure;

import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Admin
 */
public class ConnectionTest {
    static Connection instance = null;
    
    public ConnectionTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        instance = new Connection(1, //ID
                3, //x1
                4, //y1
                5, //x2
                6, //y2
                RoadType.EXPRESSWAY, //type
                "Test road"); //name
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
     * Test of getID method, of class Connection.
     */
    @Test
    public void testGetID() {
        System.out.println("getID");
        int expResult = 1;
        int result = instance.getID();
        assertEquals(expResult, result);
    }

    /**
     * Test of getX1 method, of class Connection.
     */
    @Test
    public void testGetX1() {
        System.out.println("getX1");
        double expResult = 3;
        double result = instance.getX1();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getX2 method, of class Connection.
     */
    @Test
    public void testGetX2() {
        System.out.println("getX2");
        double expResult = 5;
        double result = instance.getX2();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getY1 method, of class Connection.
     */
    @Test
    public void testGetY1() {
        System.out.println("getY1");
        double expResult = 4;
        double result = instance.getY1();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getY2 method, of class Connection.
     */
    @Test
    public void testGetY2() {
        System.out.println("getY2");
        double expResult = 6;
        double result = instance.getY2();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getType method, of class Connection.
     */
    @Test
    public void testGetType() {
        System.out.println("getType");
        RoadType expResult = RoadType.EXPRESSWAY;
        RoadType result = instance.getType();
        assertEquals(expResult, result);
    }

    /**
     * Test of compareTo method, of class Connection.
     */
    @Test
    public void testCompareTo() {
        System.out.println("compareTo");
        Connection con = new Connection(2, 0, 0, 0, 0, null, null);
        int expResult = -1;
        int result = instance.compareTo(con);
        assertEquals(expResult, result);
    }
}
