/**
 * 
 */
package graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import sun.security.provider.certpath.AdjacencyList;

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
		HashSet<Integer> neighbor = new HashSet<Integer>();
		adjListMap.put(num, neighbor);
	}

	/* (non-Javadoc)
	 * @see graph.Graph#addEdge(int, int)
	 */
	@Override
	public void addEdge(int from, int to) {
		// TODO Auto-generated method stub
		if (adjListMap.containsKey(from) && adjListMap.containsKey(to)) {
			adjListMap.get(from).add(to);
		} else {
			System.out.println("invalid argument in addEdge() in CapGraph Class");
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
		for (Integer neighbor : this.adjListMap.get(center)) {
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
		return adjListMap;
	}

}
