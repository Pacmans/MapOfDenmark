package dataStructure;

import static org.junit.Assert.*;

import org.junit.Test;

import controller.Controller;

public class AddressParserTest {
	
	AddressParser add = new AddressParser();

	@Test
	public void adressParserTest1() {
		
		String[] r;
		try {
			r = add.parseAddress("Dalgas Boulevard 161, 2000 Frederiksberg");
		} catch (Exception e) {
			fail();
			return;
		}
		assertEquals("Dalgas Boulevard", r[0]);
		assertEquals("161", r[1]);
		assertEquals("2000", r[3]);
		assertEquals("Frederiksberg", r[4]);
	}
	
	@Test
	public void adressParserTest2() {
		
		String[] r;
		try {
			r = add.parseAddress("Rued Langgardsvej 7A, 0 Sweden ");
		} catch (Exception e) {
			fail();
			return;
		}

		assertEquals("Rued Langgardsvej", r[0]);
		assertEquals("7", r[1]);
		assertEquals("A", r[2]);
		assertEquals("0", r[3]);
		assertEquals("Sweden", r[4]);
	}
	
	@Test
	public void adressParserTest3() {
		
		String[] r;
		try {
			r = add.parseAddress("Peter Bangs Vej 11, 2000");
		} catch (Exception e) {
			fail();
			return;
		}
		assertEquals("Peter Bangs Vej", r[0]);
		assertEquals("11", r[1]);
		assertEquals("2000", r[3]);
		assertEquals("Frederiksberg", r[4]);
	}
	

}
