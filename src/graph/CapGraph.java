/**
 * 
 */
package graph;

import java.awt.print.Printable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CountDownLatch;


/**
 * @author Your name here.
 * 
 * For the warm up assignment, you must implement your Graph in a class
 * named CapGraph.  Here is the stub file.
 *
 */
public class CapGraph implements Graph {

	/* (non-Javadoc)
	 * @see graph.Graph#addVertex(int)
	 */
	
	//class members
	private HashMap<Integer, HashSet<Integer>> adjListMap; //directed graph
	private int count_invalidToArgument = 0;
	private int count_invalidFromArgument = 0;
	
	//xtors
	public CapGraph() {
		adjListMap = new HashMap<Integer, HashSet<Integer>>();
	}
	
	public int getVerticesSize() {
		return adjListMap.size();
	}
	
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
			count_invalidFromArgument++;
			System.out.println("invalid argument from: \"" + from + "\" not found in map");
		}
		if (!adjListMap.containsKey(to)) {
			count_invalidToArgument++;
			System.out.println("invalid argument to: \"" + to + "\" not found in map");
		} 
		if (adjListMap.containsKey(from) && adjListMap.containsKey(to)){
			adjListMap.get(from).add(to);
		}
	}

	/* (non-Javadoc)
	 * @see graph.Graph#getEgonet(int)
	 */
	@Override
	public Graph getEgonet(int center) {
		// TODO Auto-generated method stub
		Graph result = new CapGraph();
		result.addVertex(center);
		for (int neighbor : this.adjListMap.get(center)) {
			result.addVertex(neighbor);
			result.addEdge(center, neighbor);
		}
		
		return result;
	}

	/* (non-Javadoc)
	 * @see graph.Graph#getSCCs()
	 */
	@Override
	public List<Graph> getSCCs() {
		// TODO Auto-generated method stub
		return null;
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
			System.out.println("From \"" + from + "\" to " + adjListMap.get(from).size() + " destinations");
			str += "From " + from + "\n";
			for (int to : adjListMap.get(from)) {
				str += "--->To " + to + "\n";
			}
		}
		System.out.println(str);
		System.out.println("count_invalidFromArgument: " + count_invalidFromArgument);
		System.out.println("count_invalidToArgument: " + count_invalidToArgument);
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
