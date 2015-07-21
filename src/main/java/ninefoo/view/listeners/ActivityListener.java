package ninefoo.view.listeners;

/**
 * Activity Listener used to communicate with the Controller
 *
 * @author Amir El Bawab
 */
public interface ActivityListener {

    // Create
    public void createActivity(String name, String description, String duration, String optimistic, String likely, String pessimistic, String cost, String startDate, String finishDate, int memberId, int[] prerequisitesId);

    public void editActivity(int activityId, String name, String description, String duration, String optimistic, String likely, String pessimistic, String cost, String startDate, String finishDate, int memberId, int[] prerequisitesId);

    // Load
    public void loadActivitiesByProject(int projectId);

    public void loadActivitiesForAllProjectByMember(int memberId);

    public void loadActivity(int activityId);
    
    // Delete
    public void deleteActivity(int activityId);
}
