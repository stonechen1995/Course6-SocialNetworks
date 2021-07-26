/**
 * 
 */
package graph;

import java.awt.print.Printable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;
import java.util.concurrent.CountDownLatch;


/**
 * @author Your name here.
 * 
 * For the warm up assignment, you must implement your Graph in a class
 * named CapGraph.  Here is the stub file.
 *
 */
public class CapGraph implements Graph {

	//class members
	private HashMap<Integer, HashSet<Integer>> adjListMap; //directed graph
	
	//xtors
	public CapGraph() {
		adjListMap = new HashMap<Integer, HashSet<Integer>>();
	}
	
	public CapGraph(HashMap<Integer, HashSet<Integer>> map) {
		adjListMap = new HashMap<Integer, HashSet<Integer>>(map);
	}
	
	public int getVerticesSize() {
		return adjListMap.size();
	}
	
	private Set getAllVertices() {
		return adjListMap.keySet();
	}
	
	/* (non-Javadoc)
	 * @see graph.Graph#addVertex(int)
	 */
	@Override
	public void addVertex(int num) {
		// TODO Auto-generated method stub
		if (adjListMap.containsKey(num)) return;
		adjListMap.put(num, new HashSet<Integer>());
	}

	/* (non-Javadoc)
	 * @see graph.Graph#addEdge(int, int)
	 */
	@Override
	public void addEdge(int from, int to) {
		// TODO Auto-generated method stub
		if (!adjListMap.containsKey(from)) {
			System.out.println("invalid argument from: \"" + from + "\" not found in map");
		}
		if (!adjListMap.containsKey(to)) {
			System.out.println("invalid argument to: \"" + to + "\" not found in map");
		} 
		if (adjListMap.containsKey(from) && adjListMap.containsKey(to)){
			adjListMap.get(from).add(to);
		}
	}

	/* (non-Javadoc)
	 * @see graph.Graph#getEgonet(int)
	 * An egonet is a subgraph that includes the vertex center and all of the vertices, v_i, 
	 * that are directly connected by an edge from center to v_i and all of the edges between 
	 * these vertices that are present in the original graph.
	 */
	@Override
	public Graph getEgonet(int center) {
		// TODO Auto-generated method stub
		Graph result = new CapGraph();
		// add all vertices into the "result"; add edges that are linked to the "center"
		createStarGraph(result, center);
		// add edges between vertices in the "result"
		linkAllVertices(result);
		return result;
	}
	
	private void createStarGraph(Graph resultGraph, int center) {
		resultGraph.addVertex(center);
		for (int neighbor : this.adjListMap.get(center)) {
			resultGraph.addVertex(neighbor);
			resultGraph.addEdge(center, neighbor);
		}
	}
	
	private void linkAllVertices(Graph resultGraph) {
		@SuppressWarnings("unchecked")
		Set<Integer> verticesOfResult = ((CapGraph) resultGraph).getAllVertices();
		for (int i : verticesOfResult) {
			for (int j : verticesOfResult) {
				if (adjListMap.get(i).contains(j)) 
					resultGraph.addEdge(i, j);
				if (adjListMap.get(j).contains(i)) 
					resultGraph.addEdge(j, i);
			}
		}
	}
	
	private Graph linkAllVertices(Set<Integer> verticesSet) {
		Graph resultGraph = new CapGraph();
		for (int i : verticesSet) {
			for (int j : verticesSet) {
				resultGraph.addVertex(i);
				resultGraph.addVertex(j);
				if (adjListMap.get(i).contains(j)) 
					resultGraph.addEdge(i, j);
				if (adjListMap.get(j).contains(i)) 
					resultGraph.addEdge(j, i);
			}
		}
		return resultGraph;
	}
	
	public Graph getEgonets(Collection<Integer> center) {
		// TODO Auto-generated method stub
		Graph result = new CapGraph();
		// add all vertices into the "result"; add edges that are linked to the "center"
		for (int num : center) {
			result.addVertex(num);
			for (int neighbor : this.adjListMap.get(num)) {
				result.addVertex(neighbor);
				result.addEdge(num, neighbor);
			}
		}
		// add edges between vertices in the "result"
		linkAllVertices(result);
		
		return result;
	}
	
	/* (non-Javadoc)
	 * @see graph.Graph#getSCCs()
	 * 1. DFS the graph vertices
	 * 2. transpose the graph (meaning reverse all the direction in the graph)
	 * 3. DFS the transposed graph with the answer from the stack from step 1
	 * details::
	 *  // initialized Set "visited" and Stack "finished"
		// while (vertices) not empty
			// v = vertices.pop();
			// if (v not in visited) 
				//DFS_vistied 
		// return finished;
	 */
	@Override
	public List<Graph> getSCCs() {
		// TODO Auto-generated method stub
		// 1. DFS the graph vertices and move each vertex into the "visited" and "finished"
		Set<Integer> visited1 = new HashSet<Integer>();
		Stack<Integer> finished1 = new Stack<Integer>();
		Set<Integer> verticesSet = new HashSet<Integer> (adjListMap.keySet());
		Stack<Integer> verticesStack = new Stack<Integer>();		
		verticesStack.addAll(verticesSet);
//		System.out.println("verticesStack created");
		DFS(verticesStack, visited1, finished1, this);
//		System.out.println("1st DFS completed");

		// 2. transpose the graph (meaning reverse all the edge direction in the graph)
		Graph transposedGraph = new CapGraph();
		for (int i : adjListMap.keySet()) {
			transposedGraph.addVertex(i);
			for (int neighbor : adjListMap.get(i)) {
				transposedGraph.addVertex(neighbor);
				transposedGraph.addEdge(neighbor, i);
			}
		}
//		System.out.println("graph transpose completed");

		// 3.DFS the transposed graph with the answer from the stack from step 1
		Set<Integer> visited2 = new HashSet<Integer>();
		Stack<Integer> finished2 = new Stack<Integer>();
//		System.out.println("2nd DFS about to start");
		return DFS(finished1, visited2, finished2, transposedGraph);
	}

	private List<Graph> DFS(Stack<Integer> verticesStack, Set<Integer> visited, 
			Stack<Integer> finished, Graph graph) {
//		System.out.println("DFS starting");
		List<Graph> lst = new ArrayList<Graph>();
		while (!verticesStack.empty()) {
			int num = verticesStack.pop();
//			System.out.println("num in DFS is " + num);
			Set<Integer> newGraphVertices = new HashSet<Integer>();
			
			if (!visited.contains(num)) {
//				System.out.println(num + " not visited");
				visited.add(num);
				DFS_visited(num, visited, finished, graph, newGraphVertices);
//				System.out.println("DFS_visited completed");
				
				/*//////////////////////////////////////////////////
				System.out.println("printing out newGraphVertices");
				for (int i : newGraphVertices) {
					System.out.println(i);
				}
				System.out.println("newGraphVertices printed out");
				*///////////////////////////////////////////////////
				
				lst.add(linkAllVertices(newGraphVertices));
//				System.out.println("lst.add completed");
			}
		}
//		System.out.println("DFS completed");
		return lst;
	}

	private void DFS_visited(int start, Set<Integer> visited, 
			Stack<Integer> finished, Graph graph, Set<Integer> verticesSet) {
//		System.out.println("DFS_visited starting");
		HashMap<Integer, HashSet<Integer>> map = graph.exportGraph();
		for (int neighbor : map.get(start)) {
//			System.out.println("neighbor is " + neighbor);
			if (!visited.contains(neighbor)) {
				visited.add(neighbor);
				DFS_visited(neighbor, visited, finished, graph, verticesSet);
			}
		}
		finished.add(start);
//		System.out.println("finished.add(" + start + ")");
		verticesSet.add(start);
//		System.out.println("verticesSet.add(" + start + ")");
	}


	/* (non-Javadoc)
	 * @see graph.Graph#exportGraph()
	 */
	@Override
	public HashMap<Integer, HashSet<Integer>> exportGraph() {
		// TODO Auto-generated method stub
		return new HashMap<Integer, HashSet<Integer>>(adjListMap);
	}
	
	public void printMap() {
		String str = ""; 
		for (int from : adjListMap.keySet()) {
			System.out.println("From \"" + from + "\", there are " + adjListMap.get(from).size() + " destinations");
			str += "From " + from + "\n";
			for (int to : adjListMap.get(from)) {
				str += "--->To " + to + "\n";
			}
		}
		System.out.println(str);
	}
	
	public void printEgoNode(int center) {
		System.out.println("From " + center + ", there are " + adjListMap.get(center).size() + " destinations.");
		String str = "Destionation of " + center + ": ";
		for (int i : adjListMap.get(center)) {
			str += i + ", ";
		}
		System.out.println(str);
	}

}
