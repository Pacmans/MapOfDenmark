package dataStructure;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Test;

import controller.Controller;

public class TernarySearchTriesTest {

	TernarySearchTries<Integer> tst = Controller.getInstance().getTst();
	@Test
	public void putAndGetTest() {
		tst.put("Langbortistand", 11, 2000);
		System.out.println(tst.get("Dalgas Boulevard"));
		int i = (Integer) tst.get("Langbortistand").iterator().next();
		assertEquals(i,11);
	}
	@Test
	public void keysWithPrefixTest(){
		tst.put("Arsenal", 81, 5560);
		tst.put("arsenik", 11, 7000);
		tst.put("arsenull", 21, 3000);
		LinkedList<Integer> q = (LinkedList<Integer>) tst.keysWithPrefix("Arsen");
		assertTrue(q.contains(81));
		assertTrue(q.contains(11));
		assertTrue(q.contains(21));
	}
}
