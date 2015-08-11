package ninefoo.model.object;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * This class represents an activity entity in the database.
 * Created on 30-May-2015.
 *
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
    private double plannedCost;
    private double actualCost;
    private int actualPercentage;

	private int projectId;
    private int memberId;

    private int earliestStart;
    private int earliestFinish;
    private int latestStart;
    private int latestFinish;
    private boolean isCritical; // (LS-ES == 0)

    /**
     * This constructor is used when converting DB entities to Java classes.
     *
     * @param activityId          integer representing the ID of the activity.
     * @param activityLabel       label associated with the activity.
     * @param description         description for the activity.
     * @param duration            duration (days) of the activity.
     * @param optimisticDuration  optimistic duration (days) of the activity.
     * @param likelyDuration      likely duration (days) of the activity.
     * @param pessimisticDuration pessimistic duration (days) of the activity.
     * @param createDate          Date when the activity was first created.
     * @param project             Project object associated with this activity.
     * @param member              Member object associated with this activity.
     * @param prerequisites       List of Activity objects that are prerequisites to this activity.
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
     *
     * @param activityLabel       label associated with the activity.
     * @param description         description for the activity.
     * @param duration            duration (days) of the activity.
     * @param optimisticDuration  optimistic duration (days) of the activity.
     * @param likelyDuration      likely duration (days) of the activity.
     * @param pessimisticDuration pessimistic duration (days) of the activity.
     * @param project             Project object associated with this activity.
     * @param member              Member object associated with this activity.
     * @param prerequisites       List of Activity objects that are prerequisites to this activity.
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
     *
     * @param activityLabel
     * @param description
     * @param duration
     * @param startDate
     * @param finishDate
     * @param project
     * @param member
     */
    public Activity(String activityLabel, String description, int duration, int optimistic, int likely, int pessimistic, double cost, Date startDate, Date finishDate, int projectId, int memberId, List<Activity> prerequisites) {
        this.activityLabel = activityLabel;
        this.description = description;
        this.duration = duration;
        this.optimisticDuration = optimistic;
        this.likelyDuration = likely;
        this.pessimisticDuration = pessimistic;
        this.plannedCost = cost;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.memberId = memberId;
        this.prerequisites = prerequisites;
        this.projectId = projectId;
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

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }


    public int getEarliestStart() {
        return earliestStart;
    }

    public int getEarliestFinish() {
        return earliestFinish;
    }

    public int getLatestStart() {
        return latestStart;
    }

    public int getLatestFinish() {
        return latestFinish;
    }
    public boolean isCritical() {
        return isCritical;
    }

    public void setEarliestStart(int earliestStart) {
        this.earliestStart = earliestStart;
    }

    public void setEarliestFinish(int earliestFinish) {
        this.earliestFinish = earliestFinish;
    }

    public void setLatestStart(int latestStart) {
        this.latestStart = latestStart;
    }

    public void setLatestFinish(int latestFinish) {
        this.latestFinish = latestFinish;
    }

    public void setIsCritical(boolean isCritical) {
        this.isCritical = isCritical;
    }

    public List<Activity> getPrerequisites() {
        return this.prerequisites;
    }

    public void setPrerequisites(List<Activity> prerequisites) {
        this.prerequisites = prerequisites;
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
     *
     * @param id
     */
    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }

    /**
     * Get a formatted string list
     *
     * @return String
     */
    public String getPrerequisitesAsString() {

        // If none found
        if (prerequisites.size() == 0)
            return "None";

        // If at least one
        List<Integer> preId = new ArrayList<>();
        if (this.prerequisites != null)
            for (int i = 0; i < prerequisites.size(); i++)
                preId.add(prerequisites.get(i).getActivityId());
        return Arrays.toString(preId.toArray());
    }

    public Double getPlannedCost() {
        return plannedCost;
    }

    public void setCost(Double plannedCost) {
        this.plannedCost = plannedCost;
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
    @Override
    public String toString() {
        return "Activity{" +
                "activityId=" + activityId +
//                ", activityLabel='" + activityLabel + '\'' +
//                ", description='" + description + '\'' +
                ", duration=" + duration +
//                ", optimisticDuration=" + optimisticDuration +
//                ", likelyDuration=" + likelyDuration +
//                ", pessimisticDuration=" + pessimisticDuration +
//                ", createDate=" + createDate +
//                ", startDate=" + startDate +
//                ", updateDate=" + updateDate +
//                ", finishDate=" + finishDate +
//                ", project=" + project +
//                ", member=" + member +
//                ", projectId=" + projectId +
//                ", memberId=" + memberId +
                ", earliestStart=" + earliestStart +
                ", earliestFinish=" + earliestFinish +
                ", latestStart=" + latestStart +
                ", latestFinish=" + latestFinish +
                ", isCritical=" + isCritical +
                '}';
    }
    
    public double getActualCost() {
		return actualCost;
	}

	public void setActualCost(double actualCost) {
		this.actualCost = actualCost;
	}
	
	public int getActualPercentage() {
		return actualPercentage;
	}

	public void setActualPercentage(int actualPercentage) {
		this.actualPercentage = actualPercentage;
	}
}
