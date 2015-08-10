package ninefoo.lib.graph;

import junit.framework.TestCase;
import ninefoo.model.object.Activity;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by samuel on 2015-08-09.
 */
public class GraphTest extends TestCase {


    public void testBfs() throws Exception {
        List<Activity> activityList = new LinkedList<>();
        Activity a1 = new Activity(100, null, null, 10, 0, 0, 0, null, null, null, null);
        Activity a2 = new Activity(200, null, null, 10, 0, 0, 0, null, null, null, null);
        Activity a3 = new Activity(300, null, null, 20, 0, 0, 0, null, null, null, null);
        Activity a4 = new Activity(400, null, null, 30, 0, 0, 0, null, null, null, null);
        Activity a5 = new Activity(500, null, null, 5, 0, 0, 0, null, null, null, null);
        Activity a6 = new Activity(600, null, null, 10, 0, 0, 0, null, null, null, null);
        Activity a7 = new Activity(700, null, null, 20, 0, 0, 0, null, null, null, null);
        Activity a8 = new Activity(800, null, null, 10, 0, 0, 0, null, null, null, null);
        List<Activity> a1Prereqs = new LinkedList<>();
        a1.setPrerequisites(a1Prereqs);
        List<Activity> a2Prereqs = new LinkedList<>();
        a2Prereqs.add(a1);
        a2.setPrerequisites(a2Prereqs);
        List<Activity> a3Prereqs = new LinkedList<>();
        a3Prereqs.add(a1);
        a3.setPrerequisites(a3Prereqs);
        List<Activity> a4Prereqs = new LinkedList<>();
        a4Prereqs.add(a1);
        a4.setPrerequisites(a4Prereqs);
        List<Activity> a5Prereqs = new LinkedList<>();
        a5Prereqs.add(a2);
        a5Prereqs.add(a3);
        a5.setPrerequisites(a5Prereqs);
        List<Activity> a6Prereqs = new LinkedList<>();
        a6Prereqs.add(a4);
        a6.setPrerequisites(a6Prereqs);
        List<Activity> a7Prereqs = new LinkedList<>();
        a7Prereqs.add(a5);
        a7Prereqs.add(a6);
        a7.setPrerequisites(a7Prereqs);
        List<Activity> a8Prereqs = new LinkedList<>();
        a8Prereqs.add(a7);
        a8.setPrerequisites(a8Prereqs);
        activityList.add(a1);
        activityList.add(a2);
        activityList.add(a3);
        activityList.add(a4);
        activityList.add(a5);
        activityList.add(a6);
        activityList.add(a7);
        activityList.add(a8);

        Graph graph = new Graph(8, activityList);
        graph.addEdge(100, 200);
        graph.addEdge(100, 300);
        graph.addEdge(100, 400);
        graph.addEdge(200, 500);
        graph.addEdge(300, 500);
        graph.addEdge(500, 700);
        graph.addEdge(400, 600);
        graph.addEdge(600, 700);
        graph.addEdge(700, 800);
        System.out.println(graph.printGraphReversed());
//        System.out.println(graph);
//        List<Activity> activityCriticalBefore = graph.getActivityCriticalList();
//        for (Activity activity: activityCriticalBefore) {
//            System.out.println(activity);
//        }
//        graph.bfs(true);
//        List<Activity> activityCriticalAfter = graph.getActivityCriticalList();
//        for (Activity activityCritical: activityCriticalAfter) {
//            System.out.println(activityCritical);
//        }

    }
}