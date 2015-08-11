package ninefoo.controller.logic;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import ninefoo.lib.graph.*;
import ninefoo.model.object.Activity;
import ninefoo.model.object.Project;

/**
 * This class will make the needed calculations for earned value 
 * analysis for the application
 * @author Martin Grezak
 *
 */
public class PERTAnalysis extends Graph {
	
	
		
	public PERTAnalysis(int size, List<Activity> activityList)
	{
		super(size, activityList);
		
	}

	/**
	 * calculates the expected duration. t = (a + 4m + b) / 6
	 * @param Activity
	 * @return Expected Duration
	 */
	public double calculateExpectedDuration(Activity activity)
	{
		return (
			   (activity.getOptimisticDuration() +  (activity.getLikelyDuration() * 4) + activity.getPessimisticDuration()) 
				/ 6	);
	}
	/**
	 * calculates the expected duration. w = (b-a) / 6
	 * @param Activity
	 * @return Standard Deviation
	 */
	public double standardDeviation(Activity activity)
	{
		return (
			   (activity.getPessimisticDuration()- activity.getOptimisticDuration()) / 6	);
	}
	/**
	 * adds the expected durations of two activities
	 * @param Activity
	 * @return Expected Duration
	 */
	public double addExpectedDurations(Activity activity1, Activity activity2)
	{
		return calculateExpectedDuration(activity1)+calculateExpectedDuration(activity2);
		//TODO Make sure to Math.max and get 2 more parameters
	}
	/**
	 * adds the standard deviation of two activities
	 * @param Activity
	 * @return Standard Deviation
	 */
	public double addStandardDeviations(Activity activity1, Activity activity2)
	{
		return  Math.sqrt(Math.pow(standardDeviation(activity1), 2)+Math.pow(standardDeviation(activity2), 2));
		//TODO Make sure to Math.max and get 2 more parameters
	}

	@Override
	public void bfs(boolean forward){
	        Queue<Integer> queue = new LinkedList<>();
	        int[] visited = new int[degree.length];

	        visited[0] = VISITING;

	        queue.offer(0);

	        while (!queue.isEmpty()) {
	            int currentPoll = queue.poll();
	            visited[currentPoll] = VISITED;

	            for (int i = 0; i < degree[currentPoll]; i++) {
	                int neighbour = graph[currentPoll][i];
	               setPERTpath(neighbour,currentPoll,forward);
	                if (visited[neighbour] == UNVISITED) {
	                    queue.offer(neighbour);
	                    visited[neighbour] = VISITING;
	                }
	            }
	        }
		
	}
	
	
	 public void setPERTpath(int neighbour, int current, boolean forward){
	        // find corresponding activity
	        Activity activityCriticalNeighbor = getActivityByVirtualID(neighbour);
	        Activity activityCriticalCurrent = getActivityByVirtualID(current);
            System.out.println("neighbor before" + activityCriticalNeighbor);
            System.out.println("current" + activityCriticalCurrent);
            
           // addExpectedDurations(activityCriticalCurrent,activityCriticalNeighbor);
           // addStandardDeviations(activityCriticalCurrent,activityCriticalNeighbor);
            
            //////////////
            System.out.println("neighbor after" + activityCriticalNeighbor);

	        System.out.println();
	    }

}
