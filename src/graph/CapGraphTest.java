package graph;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class CapGraphTest {

	CapGraph myGraph = new CapGraph();

	@Before
	public void setUp() throws Exception {
		myGraph = new CapGraph();
		for (int i = 0; i < 5; i++) {
			myGraph.addVertex(i);
		}
		for(int i = 0; i < 5; i ++) {
			for (int j = 0; j < 5; j++) {
				myGraph.addEdge(i, j);
			}
		}
	}

	@Test
	public void testAddVertex() {
		assertEquals(5, myGraph.getVerticesSize());
	}

//	@Test
//	public void testAddEdge() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetEgonet() {
//		Graph egonetGraph = myGraph.getEgonet(0);
//		((CapGraph)egonetGraph).printMap();;
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
//	
//	@Test
//	public void testPrintMap() {
//		myGraph.printMap();
//	}

	@Test
	public void testGetSCCs() {
		for (Graph gra: myGraph.getSCCs()) {
			System.out.println("going to print out gra");
			gra.printMap();
			System.out.println("gra printed out");
		}
	}
}
