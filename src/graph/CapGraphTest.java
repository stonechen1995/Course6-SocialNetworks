package graph;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import sun.tools.jar.resources.jar;

public class CapGraphTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		CapGraph gra = new CapGraph();
		for (int i = 0; i < 5; i++) {
			gra.addVertex(i);
		}
		for(int i = 0; i < 5; i ++) {
			for (int j = 0; j < 5; j++) {
				gra.addEdge(i, j);
			}
		}
	}

	@Before
	public void setUp() throws Exception {
		Graph gra = new CapGraph();
		for (int i = 0; i < 5; i++) {
			gra.addVertex(i);
		}
		for(int i = 0; i < 5; i ++) {
			for (int j = 0; j < 5; j++) {
				gra.addEdge(i, j);
			}
		}
	}

	@Test
	public void testAddVertex() {
		CapGraph gra = new CapGraph();
		for (int i = 0; i < 5; i++) {
			gra.addVertex(i);
		}
		for(int i = 0; i < 5; i ++) {
			for (int j = 0; j < 5; j++) {
				gra.addEdge(i, j);
			}
		}
		assertEquals(5, gra.getVerticesSize());
		fail("Not yet implemented");
	}

//	@Test
//	public void testAddEdge() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetEgonet() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetSCCs() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testExportGraph() {
//		fail("Not yet implemented");
//	}

}