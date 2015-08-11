package ninefoo.lib.graph;

import junit.framework.TestCase;
import ninefoo.model.object.Activity;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by samuel on 2015-08-09.
 */
public class GraphTest extends TestCase {

    @Test
    public void testBfs() throws Exception {
        List<Activity> activityList = new LinkedList<>();
        Activity a1 = new Activity(0, null, null, 14, 0, 0, 0, null, null, null, null);
        Activity a2 = new Activity(1, null, null, 5, 0, 0, 0, null, null, null, null);
        Activity a3 = new Activity(2, null, null, 10, 0, 0, 0, null, null, null, null);
        Activity a4 = new Activity(3, null, null, 10, 0, 0, 0, null, null, null, null);
        Activity a5 = new Activity(4, null, null, 14, 0, 0, 0, null, null, null, null);
        Activity a6 = new Activity(5, null, null, 8, 0, 0, 0, null, null, null, null);
        Activity a7 = new Activity(6, null, null, 10, 0, 0, 0, null, null, null, null);
        Activity a8 = new Activity(7, null, null, 21, 0, 0, 0, null, null, null, null);
        activityList.add(a1);
        activityList.add(a2);
        activityList.add(a3);
        activityList.add(a4);
        activityList.add(a5);
        activityList.add(a6);
        activityList.add(a7);
        activityList.add(a8);

        Graph graph = new Graph(8, activityList);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(0, 3);
        graph.addEdge(1, 4);
        graph.addEdge(2, 4);
        graph.addEdge(3, 5);
        graph.addEdge(5, 6);
        graph.addEdge(4, 6);
        graph.addEdge(6, 7);
//        System.out.println(graph.printGraphReversed());
//        System.out.println(graph);
//        List<Activity> activityCriticalBefore = graph.getActivityCriticalList();
//        for (Activity activity: activityCriticalBefore) {
//            System.out.println(activity);
//        }
        graph.bfs(true);
        graph.bfs(false);
        graph.setCriticalPath();
        List<Activity> activityCriticalAfter = graph.getActivityCriticalList();
        System.out.println("final result:");
        System.out.println("------------------------------------------------------------------------------------");
        for (Activity activityCritical : activityCriticalAfter) {
            System.out.println(activityCritical);
        }

    }
}