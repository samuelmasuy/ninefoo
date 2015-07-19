package ninefoo.model.object;

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
    private Date createDate;
    private Date startDate;
    private Date updateDate;
    private Date finishDate;
    private Project project;
    private Member member;
    private List<Activity> prerequisites;
	private Double cost;
    private int projectId;
    private int memberId;

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
        this.createDate = createDate;
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
    public Activity(String activityLabel, int duration, Date startDate, Date finishDate, Project project, Member member, Double cost){
         this(0,activityLabel, null, duration, 0, 0, 0, null, project, member, null);
         this.startDate = startDate;
         this.finishDate = finishDate;
         this.cost = cost;
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
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
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
     * @return String
     */
    public String getPrerequisitesAsString(){
    	List<Integer> preId = new ArrayList<>();
    	if(this.prerequisites != null)
    		for(int i=0; i<prerequisites.size(); i++)
        		preId.add(prerequisites.get(i).getActivityId());
    	return Arrays.toString(preId.toArray());
    }

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}
	
	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	
	public int getMemberId() {
		return memberId;
	}
	
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
}
