package ninefoo.lib.graph;

import java.util.HashMap;

import org.apache.logging.log4j.LogManager;

/**
 * Adjacency list graph.
 * @author Amir El Bawab
 */
public class Graph {
	
	// Logger
	private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger();
		
	// Variables
	private int[][] graph;
	private int[] degree;
	private HashMap<Integer, Integer> idMapper;
	private int index = 0;
	
	// Constants
	private final int UNVISITED = 0;
	private final int VISITING = 1;
	private final int VISITED = 2;
	
	/**
	 * Constructor
	 * @param size Number of nodes in the graph
	 */
	public Graph(int size) {
		
		// Store graph connection
		this.graph = new int[size][size-1];
		
		// Store out going edges
		this.degree = new int[size];
		
		// Store virtual id
		this.idMapper = new HashMap<>(size);
	}
	
	/**
	 * Add an edge in the graph
	 * @param from From index
	 * @param to To index
	 */
	public void addEdge(int from, int to){
		
		// If the 'from' id is not in the hash map, add it
		if(idMapper.get(from) == null)
			this.idMapper.put(from, this.index++);
		
		// If the 'to' id is not in the hash map, add it
		if(idMapper.get(to) == null)
			this.idMapper.put(to, this.index++);
		
		// Get virtual id
		int virtualFrom = idMapper.get(from);
		int virtualTo = idMapper.get(to);
		
		try{
			
			// Check if edge already exist
			for(int i=0; i<this.degree[virtualFrom]; i++){
				if(this.graph[virtualFrom][i] == virtualTo)
					return;
			}
			
			// Create edge
			this.graph[virtualFrom][this.degree[virtualFrom]] = virtualTo;
			this.degree[virtualFrom]++;
			
		} catch (IndexOutOfBoundsException e) {
			LOGGER.error(String.format("Cannot add edge: %d -> %d", from, to));
		}
	}
	
	/**
	 * Run Depth FIrst Search recursively to find cycles
	 * @param index Starting point
	 * @param visited
	 * @return true if cycle detected
	 */
	private boolean isCyclic(int index, int[] visited){
		
		// If node unvisited
		if(visited[index] == UNVISITED){
			
			// Mark it as visiting
			visited[index] = VISITING;
			
			// Loop on all its neighbors
			for(int i = 0; i < this.degree[index]; i++){
				
				// Store neighbor
				int neighbor = this.graph[index][i];
				
				// If cycle found
				if(visited[neighbor] == VISITING)
					return true;
				
				// Recursive checking
				if(isCyclic(neighbor, visited))
					return true;
			}
			
			// Mark it as visited
			visited[index] = VISITED;
		}
		
		// No cycle so far
		return false;
	}
	
	/**
	 * Checks if there's a cycle in the graph. The approach used is Depth First Search.
	 * @return true if cycle detected
	 */
	public boolean isCyclic(){
		
		// Loop on all nodes
		for(int i=0; i < this.graph.length; i++){
			
			// Create the visited list
			int visited[] = new int[this.graph.length];
			
			// If cycle detected
			if(this.isCyclic(i, visited))
				return true;
		}
		
		// No cycle detected
		return false;
	}
	
	/**
	 * Get graph representation as a String
	 */
	public String toString(){
		String output = "";
		for(int row = 0; row < this.graph.length; row++){
			
			output += String.format("Node %d\t| Connected to: ", row);

			for(int col = 0; col < this.degree[row]; col++){
				if(col > 0)
					output += ", ";
				output += this.graph[row][col];
			}
			
			output += "\n";
		}
		return output;
	}
}
