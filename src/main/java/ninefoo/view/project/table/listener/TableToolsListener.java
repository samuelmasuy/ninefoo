package ninefoo.view.project.table.listener;

import ninefoo.model.object.Activity;
import ninefoo.view.project.table.dialog.CreateActivityDialog;
import ninefoo.view.project.table.dialog.EditActivityDialog;

import javax.swing.*;

/**
 * Listener used to communicate with the Table Container View
 *
 * @author Sebouh Bardakjian
 */
public interface TableToolsListener {

    // Create
    public void createActivity(CreateActivityDialog dialog, String name, String description, String duration, String optimistic, String likely, String pessimistic, String cost, String startDate, String finishDate, int memberId, int[] prerequisitesId);

    // Load
    public void loadAllMembersForCreateActivityDialog(CreateActivityDialog dialog);

    public void loadActivitiesForCreateActivityDialog(CreateActivityDialog dialog);

    public void loadAllMembersForEditActivityDialog(EditActivityDialog dialog);

    public void loadActivitiesForEditActivityDialog(EditActivityDialog dialog);

    public void loadActivity(EditActivityDialog dialog, int activityId);

    // Edit
    public void updateActivity(EditActivityDialog dialog, int activityId, String name, String description, String duration, String optimistic, String likely, String pessimistic, String cost, String startDate, String finishDate, int memberId, int[] prerequisitesId);

    // Delete
    public void deleteActivity(int activityId);

    // View details
    public void viewActivityDetails();

}
