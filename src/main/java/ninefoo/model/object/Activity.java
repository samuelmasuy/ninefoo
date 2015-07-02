package ninefoo.model.object;

import ninefoo.config.Config;
import ninefoo.helper.DateHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * This class represents an activity entity in the database.
 * Created on 30-May-2015.
 * @author Farzad MajidFayyaz
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
    private String startDate;
    private String updateDate;
    private String finishDate;
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
        this.createDate = DateHelper.format(createDate, Config.DATE_FORMAT);
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
    
    /**
     * A reduced constructor
     * @param activityLabel
     * @param description
     * @param duration
     * @param startDate
     * @param finishDate
     * @param project
     * @param member
     */
    public Activity(String activityLabel, int duration, String startDate, String finishDate, Project project, Member member){
         this(0,activityLabel, null, duration, 0, 0, 0, null, project, member, null);
         this.startDate = startDate;
         this.finishDate = finishDate;
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
        return DateHelper.parse(createDate, Config.DATE_FORMAT);
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

    public String toString() {
        return String.format("Activity [ID: %d, Label: '%s', Desc: '%s', Dur: %d, " +
                "OptDur: %d, LikeDur: %d, PessDur: %d, ProjID: %d, MemID: %d, PrereqCount: %d]",
                activityId, activityLabel, description, duration, optimisticDuration,
                likelyDuration, pessimisticDuration, project.getProjectId(),
                member.getMemberId(), prerequisites == null ? 0 : prerequisites.size());
    }

    public Date getStartDate() {
        return DateHelper.parse(startDate, Config.DATE_FORMAT);
    }

    public void setStartDate(Date startDate) {
        this.startDate = DateHelper.format(startDate, Config.DATE_FORMAT);
    }

    public Date getUpdateDate() {
        return DateHelper.parse(updateDate, Config.DATE_FORMAT);
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = DateHelper.format(updateDate, Config.DATE_FORMAT);
    }

    public Date getFinishDate() {
        return DateHelper.parse(finishDate, Config.DATE_FORMAT);
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = DateHelper.format(finishDate, Config.DATE_FORMAT);
    }
    
    /**
     * Added by amir
     * @param id
     */
    public void setActivityId(int activityId){
    	this.activityId = activityId;
    }
    
    /**
     * Get a formatted string list
     * Added by Amir
     * @return String
     */
    public String getPrerequisitesAsString(){
    	List<Integer> preId = new ArrayList<>();
    	for(int i=0; i<prerequisites.size(); i++)
    		preId.add(prerequisites.get(i).getActivityId());
    	return Arrays.toString(preId.toArray());
    }
}
