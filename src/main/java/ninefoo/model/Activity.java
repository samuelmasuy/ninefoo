package ninefoo.model;

import java.util.Date;
import java.util.List;

/**
 * Created by Farzad on 30-May-2015.
 */
public class Activity {
    private int activityId;
    private String activityLabel;
    private String description;
    private int duration;
    private int optimisticDuration;
    private int likelyDuration;
    private int pessimisticDuration;
    private Date createDate;
    private Project project;
    private int memberId;
    private List<Activity> prerequisites;

    public Activity(int activityId, String activityLabel, String description,
                    int duration, int optimisticDuration, int likelyDuration,
                    int pessimisticDuration, Date createDate, Project project,
                    int memberId, List<Activity> prerequisites) {
        this.activityId = activityId;
        this.activityLabel = activityLabel;
        this.description = description;
        this.duration = duration;
        this.optimisticDuration = optimisticDuration;
        this.likelyDuration = likelyDuration;
        this.pessimisticDuration = pessimisticDuration;
        this.createDate = createDate;
        this.project = project;
        this.memberId = memberId;
        this.prerequisites = prerequisites;
    }

    public Activity(String activityLabel, String description, int duration,
                    int optimisticDuration, int likelyDuration, int pessimisticDuration,
                    Date createDate, Project project, int memberId, List<Activity> prerequisites) {

        this.activityLabel = activityLabel;
        this.description = description;
        this.duration = duration;
        this.optimisticDuration = optimisticDuration;
        this.likelyDuration = likelyDuration;
        this.pessimisticDuration = pessimisticDuration;
        this.createDate = createDate;
        this.project = project;
        this.memberId = memberId;
        this.prerequisites = prerequisites;
    }

    public int getActivityId() {
        return activityId;
    }

    public String getActivityLabel() {
        return activityLabel;
    }

    public void setActivityLabel(String activityLabel) {
        this.activityLabel = activityLabel;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getOptimisticDuration() {
        return optimisticDuration;
    }

    public void setOptimisticDuration(int optimisticDuration) {
        this.optimisticDuration = optimisticDuration;
    }

    public int getLikelyDuration() {
        return likelyDuration;
    }

    public void setLikelyDuration(int likelyDuration) {
        this.likelyDuration = likelyDuration;
    }

    public int getPessimisticDuration() {
        return pessimisticDuration;
    }

    public void setPessimisticDuration(int pessimisticDuration) {
        this.pessimisticDuration = pessimisticDuration;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Project getProject() { return project; }

    public void setProject(Project project) {
        this.project = project;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public List<Activity> getPrerequisites() { return prerequisites; }

    public void setPrerequisites(List<Activity> prerequisites) { this.prerequisites = prerequisites; }
}
