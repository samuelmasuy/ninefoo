package ninefoo.lib.graph;

import ninefoo.model.object.Activity;
import org.apache.logging.log4j.LogManager;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Adjacency list graph.
 *
 * @author Amir El Bawab
 */
public class Graph {

    // Logger
    private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger();

    // Variables
    protected int[][] graph;

	private int[][] graphReversed;
    protected int[] degree;

	private int[] degreeReversed;
    private HashMap<Integer, Integer> idMapper;
    private int index = 0;


    private List<Activity> activityCriticalList = new LinkedList<>();

    // Constants
    protected final int UNVISITED = 0;
    protected final int VISITING = 1;
    protected final int VISITED = 2;
    private int[] virtualToRealMapping;

    /**
     * Constructor
     *
     * @param size Number of nodes in the graph
     */
    public Graph(int size) {

        // Store graph connection
        this.graph = new int[size][size - 1];
        this.graphReversed = new int[size][size - 1];

        // Store out going edges
        this.degree = new int[size];
        this.degreeReversed = new int[size];

        // Store virtual id
        this.idMapper = new HashMap<>(size);

        this.virtualToRealMapping = new int[size];
    }

    public Graph(int size, List<Activity> activityList) {
        this(size);
        this.activityCriticalList = activityList;
    }

    /**
     * Add an edge in the graph
     *
     * @param from From index
     * @param to   To index
     */
    public void addEdge(int from, int to) {

        // If the 'from' id is not in the hash map, add it
        if (idMapper.get(from) == null) {
            this.idMapper.put(from, this.index);
            this.virtualToRealMapping[index++] = from;
        }

        // If the 'to' id is not in the hash map, add it
        if (idMapper.get(to) == null) {
            this.idMapper.put(to, this.index);
            this.virtualToRealMapping[index++] = to;
        }

        // Get virtual id
        int virtualFrom = idMapper.get(from);
        int virtualTo = idMapper.get(to);

        try {

            // Check if edge already exist
            boolean found = false;
            for (int i = 0; i < this.degree[virtualFrom] && !found; i++) {
                if (this.graph[virtualFrom][i] == virtualTo)
                    found = true;
            }

            // Create edge
            if(!found) {
                this.graph[virtualFrom][this.degree[virtualFrom]] = virtualTo;
                this.degree[virtualFrom]++;
            }

            found = false;
            for (int i = 0; i < this.degreeReversed[virtualTo] && !found; i++) {
                if (this.graphReversed[virtualTo][i] == virtualFrom)
                    found = true;
            }

            // Create edge
            if(!found) {
                this.graphReversed[virtualTo][this.degreeReversed[virtualTo]] = virtualFrom;
                this.degreeReversed[virtualTo]++;
            }
            System.out.println(printGraphReversed());
        } catch (IndexOutOfBoundsException e) {
            LOGGER.error(String.format("Cannot add edge: %d -> %d", from, to));
        }
    }

    /**
     * Run Depth FIrst Search recursively to find cycles
     *
     * @param index   Starting point
     * @param visited
     * @return true if cycle detected
     */
    private boolean isCyclic(int index, int[] visited) {

        // If node unvisited
        if (visited[index] == UNVISITED) {

            // Mark it as visiting
            visited[index] = VISITING;

            // Loop on all its neighbors
            for (int i = 0; i < this.degree[index]; i++) {

                // Store neighbor
                int neighbor = this.graph[index][i];

                // If cycle found
                if (visited[neighbor] == VISITING)
                    return true;

                // Recursive checking
                if (isCyclic(neighbor, visited))
                    return true;
            }

            // Mark it as visited
            visited[index] = VISITED;
        }

        // No cycle so far
        return false;
    }

    public void bfs(boolean forward) {
        initCriticalPath();
        Queue<Integer> queue = new LinkedList<>();
        int[] visited = new int[degree.length];

        visited[0] = VISITING;

        queue.offer(0);

        while (!queue.isEmpty()) {
            int currentPoll = queue.poll();
            visited[currentPoll] = VISITED;

            for (int i = 0; i < degree[currentPoll]; i++) {
                int neighbour = graph[currentPoll][i];
                setCriticalPath(neighbour, currentPoll, forward);
                if (visited[neighbour] == UNVISITED) {
                    queue.offer(neighbour);
                    visited[neighbour] = VISITING;
                }
            }
        }
    }

    private void initCriticalPath() {
        getActivityByVirtualID(0).setEarliestStart(0);
        getActivityByVirtualID(0).setEarliestFinish(getActivityByVirtualID(0).getDuration());
    }

    private void setCriticalPath(int neighbour, int current, boolean forward) {
        // find corresponding activity
        Activity activityCriticalNeighbor = getActivityByVirtualID(neighbour);
        Activity activityCriticalCurrent = getActivityByVirtualID(current);
        // evaluate all previous
        if (forward) {
            activityCriticalNeighbor.setEarliestStart(Math.max(activityCriticalNeighbor.getEarliestStart(), activityCriticalCurrent.getEarliestFinish()));
            activityCriticalNeighbor.setEarliestFinish(activityCriticalNeighbor.getEarliestStart() + activityCriticalNeighbor.getDuration());
        } else {
            activityCriticalNeighbor.setLatestFinish(Math.max(activityCriticalNeighbor.getLatestFinish(), activityCriticalCurrent.getEarliestFinish()));
            activityCriticalNeighbor.setLatestStart(activityCriticalNeighbor.getEarliestStart() + activityCriticalNeighbor.getDuration());
        }
    }

    protected Activity getActivityByVirtualID(int target) {
        int realTarget = virtualToRealMapping[target];
        for (Activity activity: this.activityCriticalList) {
           if (activity.getActivityId() == realTarget) {
              return activity;
           }
        }
        return null;
    }

    /**
     * Checks if there's a cycle in the graph. The approach used is Depth First Search.
     *
     * @return true if cycle detected
     */
    public boolean isCyclic() {

        // Loop on all nodes
        for (int i = 0; i < this.graph.length; i++) {

            // Create the visited list
            int visited[] = new int[this.graph.length];

            // If cycle detected
            if (this.isCyclic(i, visited))
                return true;
        }

        // No cycle detected
        return false;
    }

    /**
     * Get graph representation as a String
     */
    public String toString() {
        String output = "";
        for (int row = 0; row < this.graph.length; row++) {

            output += String.format("Node %d\t| Connected to: ", row);

            for (int col = 0; col < this.degree[row]; col++) {
                if (col > 0)
                    output += ", ";
                output += this.graph[row][col];
            }

            output += "\n";
        }
        return output;
    }
    public String printGraphReversed() {
        String output = "";
        for (int row = 0; row < this.graphReversed.length; row++) {

            output += String.format("Node %d\t| Connected to: ", row);

            for (int col = 0; col < this.degreeReversed[row]; col++) {
                if (col > 0)
                    output += ", ";
                output += getActivityByVirtualID(this.graphReversed[row][col]).getActivityId();
            }

            output += "\n";
        }
        return output;
    }
    public List<Activity> getActivityCriticalList() {
        return activityCriticalList;
    }
}
