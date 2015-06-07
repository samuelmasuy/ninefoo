package ninefoo.view.listeners;

import java.util.List;

public interface ActivityListener {
	public void createUpdateActivity(int row, String activityId, String name, String description, String duration,
									 String optimisticDuration, String likelyDuration, String pessimisticDuration,
									 int projectID, int memberID, final List<Integer> prerequisites);
    }
