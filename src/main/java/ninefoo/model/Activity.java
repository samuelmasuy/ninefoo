package ninefoo.model;

import ninefoo.lib.DateUtils;

import java.util.Date;
import java.util.List;

/**
 * This class represents an activity entity in the database.
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
    private String createDate;
    private Project project;
    private Member member;
    private List<Activity> prerequisites;

    /**
     * This constructor is used when converting DB entities to Java classes.
     * @param activityId integer representing the ID of the activity.
     * @param activityLabel label associated with the activity.
     * @param description description for the activity.
     * @param duration duration (days) of the activity.
     * @param optimisticDuration optimistic duration (days) of the activity.
     * @param likelyDuration likely duration (days) of the activity.
     * @param pessimisticDuration pessimistic duration (days) of the activity.
     * @param createDate Date when the activity was first created.
     * @param project Project object associated with this activity.
     * @param member Member object associated with this activity.
     * @param prerequisites List of Activity objects that are prerequisites to this activity.
     */
    public Activity(int activityId, String activityLabel, String description,
                    int duration, int optimisticDuration, int likelyDuration,
                    int pessimisticDuration, Date createDate, Project project,
                    Member member, List<Activity> prerequisites) {

        this.activityId = activityId;
        this.activityLabel = activityLabel;
        this.description = description;
        this.duration = duration;
        this.optimisticDuration = optimisticDuration;
        this.likelyDuration = likelyDuration;
        this.pessimisticDuration = pessimisticDuration;
        this.project = project;
        this.member = member;
        this.prerequisites = prerequisites;
    }

    /**
     * This constructor should be used when creating new instances of this class in Java.
     * @param activityLabel label associated with the activity.
     * @param description description for the activity.
     * @param duration duration (days) of the activity.
     * @param optimisticDuration optimistic duration (days) of the activity.
     * @param likelyDuration likely duration (days) of the activity.
     * @param pessimisticDuration pessimistic duration (days) of the activity.
     * @param project Project object associated with this activity.
     * @param member Member object associated with this activity.
     * @param prerequisites List of Activity objects that are prerequisites to this activity.
     */
    public Activity(String activityLabel, String description, int duration,
                    int optimisticDuration, int likelyDuration, int pessimisticDuration,
                    Project project, Member member, List<Activity> prerequisites) {

        this.activityLabel = activityLabel;
        this.description = description;
        this.duration = duration;
        this.optimisticDuration = optimisticDuration;
        this.likelyDuration = likelyDuration;
        this.pessimisticDuration = pessimisticDuration;
        this.project = project;
        this.member = member;
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
        return DateUtils.parse(createDate);
    }

    public Project getProject() { return project; }

    public void setProject(Project project) {
        this.project = project;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public List<Activity> getPrerequisites() { return prerequisites; }

    public void setPrerequisites(List<Activity> prerequisites) { this.prerequisites = prerequisites; }
}
