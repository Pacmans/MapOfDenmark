package addressParser;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Admin
 */
public class AddressParserTest {

    public AddressParserTest() {
        AddressParser ap = new AddressParser();
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
     * Test of parseAddress method, of class AddressParser.
     */
    @Test
    public void testParseAddress1() throws Exception {
        System.out.println("parseAddress1");
        String s = "Rued Langgaards Vej 754, 5. sal, 2300 K�benhavn S 2300 ";
        AddressParser instance = new AddressParser();
        String[] expResult = {"Rued Langgaards Vej#", "754#", "#", "5#", "2300#", "K�benhavn S"};
        String[] result = instance.parseAddress(s);
        assertEquals(expResult, result);
    }

    /**
     * Test of parseAddress method, of class AddressParser.
     */
    @Test
    public void testParseAddress2() throws Exception {
        System.out.println("parseAddress2");
        String s = "Rued Langgaards Vej 7 2300 K�benhavn S";
        AddressParser instance = new AddressParser();
        String[] expResult = {"Rued Langgaards Vej#", "7#", "#", "#", "2300#", "K�benhavn S"};
        String[] result = instance.parseAddress(s);
        assertEquals(expResult, result);
    }

    /**
     * Test of parseAddress method, of class AddressParser.
     */
    @Test
    public void testParseAddress3() throws Exception {
        System.out.println("parseAddress3");
        String s = "Rued Langgaards Vej 7, 5.";
        AddressParser instance = new AddressParser();
        String[] expResult = {"Rued Langgaards Vej#", "7#", "#", "5#", "#", ""};
        String[] result = instance.parseAddress(s);
        assertEquals(expResult, result);
    }

    /**
     * Test of parseAddress method, of class AddressParser.
     */
    @Test
    public void testParseAddress4() throws Exception {
        System.out.println("parseAddress4");
        String s = "Rued Langgaards Vej 77 A K�benhavn S";
        AddressParser instance = new AddressParser();
        String[] expResult = {"Rued Langgaards Vej#", "77#", "A#", "#", "#", "K�benhavn S"};
        String[] result = instance.parseAddress(s);
        assertEquals(expResult, result);
    }

    /**
     * Test of parseAddress method, of class AddressParser.
     */
    @Test
    public void testParseAddress6() throws Exception {
        System.out.println("parseAddress6");
        String s = "Rued Langgaards Vej#####K�benhavn S";
        AddressParser instance = new AddressParser();
        String[] expResult = {""};
        String[] result = instance.parseAddress(s);
        assertEquals(expResult, result);
    }

    /**
     * Test of parseAddress method, of class AddressParser.
     */
    @Test
    public void testParseAddress7() throws Exception {
        System.out.println("parseAddress7");
        String s = "Britney Spears, 30";
        AddressParser instance = new AddressParser();
        String[] expResult = {""};
        String[] result = instance.parseAddress(s);
        assertEquals(expResult, result);
    }


    /**
     * Test of parseAddress method, of class AddressParser.
     */
    @Test
    public void testParseAddress11() throws Exception {
        System.out.println("parseAddress11");
        String s = "Rued Langgaards Vej 2300 K�benhavn S";
        AddressParser instance = new AddressParser();
        String[] expResult = {"Rued Langgaards Vej#", "#", "#", "#", "2300#", "K�benhavn S"};
        String[] result = instance.parseAddress(s);
        assertEquals(expResult, result);
    }
    
      /**
     * Test of parseAddress method, of class AddressParser.
     */
    @Test
    public void testParseAddress12() throws Exception {
        System.out.println("parseAddress12");
        String s = "";
        AddressParser instance = new AddressParser();
        String[] expResult = {""};
        String[] result = instance.parseAddress(s);
        assertEquals(expResult, result);
    }
    
   /**
     * Test of parseAddress method, of class AddressParser.
     */
    @Test
    public void testParseAddress13() throws Exception {
        System.out.println("parseAddress13");
        String s = "ny holms gade 2 kbh";
        AddressParser instance = new AddressParser();
        String[] expResult = {"ny holms gade#", "2#", "#", "#", "#", "kbh"};
        String[] result = instance.parseAddress(s);
        assertEquals(expResult, result);
    }
    
     /**
     * Test of parseAddress method, of class AddressParser.
     */
    @Test
    public void testParseAddress14() throws Exception {
        System.out.println("parseAddress14");
        String s = "Rådmandsgade 12 A 3. 2000 København";
        AddressParser instance = new AddressParser();
        String[] expResult = {"Rådmandsgade#", "12#", "A#", "3#", "2000#", "København"};
        String[] result = instance.parseAddress(s);
        assertEquals(expResult, result);
    }
    
         /**
     * Test of parseAddress method, of class AddressParser.
     */
    @Test
    public void testParseAddress15() throws Exception {
        System.out.println("parseAddress15");
        String s = "Rådmandsgade 12, 3A, København";
        AddressParser instance = new AddressParser();
        String[] expResult = {"Rådmandsgade#", "12#", "#", "3#", "#", "København"};
        String[] result = instance.parseAddress(s);
        assertEquals(expResult, result);
    }
}
