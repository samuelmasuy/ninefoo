package ninefoo.model;

import ninefoo.lib.DateUtils;

import java.util.Date;

/**
 * This class represents a project entity in the database.
 * Created by Farzad on 29-May-2015.
 */
public class Project {
    private int projectId;
    private String projectName;
    private String createDate;
    private String startDate;
    private String updateDate;
    private double budget;
    private String deadlineDate;
    private String description;

    /**
     * This constructor is only used when we want to convert db entities to Java classes
     * @param projectName Name of the project
     * @param createDate Date of creation
     * @param updateDate Date of last update
     * @param budget Budget of the project
     * @param deadlineDate Deadline date defined for the project
     * @param description Optional description for the project
     */
    Project(int projectId, String projectName, Date createDate, Date startDate,
            Date updateDate, double budget, Date deadlineDate, String description) {

        this.projectId = projectId;
        this.projectName = projectName;
        this.startDate = DateUtils.format(startDate);
        this.createDate = DateUtils.format(createDate);
        this.updateDate = DateUtils.format(updateDate);
        this.budget = budget;
        this.deadlineDate = DateUtils.format(deadlineDate);
        this.description = description;
    }

    /**
     * This constructor is used when creating a new project object in the Java code because
     *      the ID of the project is determined when the project is added to the DB table.
     * @param projectName Name of the project
     * @param budget Budget of the project
     * @param deadlineDate Deadline date defined for the project
     * @param description Optional description for the project
     */
    public Project(String projectName, double budget, Date startDate, Date deadlineDate, String description) {

        this.projectName = projectName;
        this.budget = budget;
        this.startDate = DateUtils.format(startDate);
        this.deadlineDate = DateUtils.format(deadlineDate);
        this.description = description;
    }
    
    public int getProjectId() {
        return projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Date getCreateDate() {
        return DateUtils.parse(createDate);
    }

    public Date getUpdateDate() {
        return DateUtils.parse(updateDate);
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = DateUtils.format(updateDate);
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public Date getDeadlineDate() {
        return DateUtils.parse(deadlineDate);
    }

    public void setDeadlineDate(Date deadlineDate) {
        this.deadlineDate = DateUtils.format(deadlineDate);
    }

    public String getDescription() {
        return description;
    }
    
    public Date getStartDate() {
		return DateUtils.parse(startDate);
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String toString() {
        return String.format("Project [ID: %d, Name: '%s', Create: '%s', " +
                "Update: '%s', Budget: %.2f, Deadline: '%s']", projectId, projectName,
                createDate, updateDate, budget, deadlineDate);
    }
}
