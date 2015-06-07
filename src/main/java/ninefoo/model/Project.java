package ninefoo.model;

import ninefoo.config.*;
import ninefoo.config.Config;
import ninefoo.helper.DateHelper;

import java.util.Date;
import java.util.List;

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
    private String finishDate;
    private double budget;
    private String deadlineDate;
    private String description;
    private List<Activity> acitivies;

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
        this.startDate = DateHelper.format(startDate, Config.DATE_FORMAT);
        this.createDate = DateHelper.format(createDate, Config.DATE_FORMAT);
        this.updateDate = DateHelper.format(updateDate, Config.DATE_FORMAT);
        this.budget = budget;
        this.deadlineDate = DateHelper.format(deadlineDate, Config.DATE_FORMAT);
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
        this.startDate = DateHelper.format(startDate, Config.DATE_FORMAT);
        this.deadlineDate = DateHelper.format(deadlineDate, Config.DATE_FORMAT);
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
        return DateHelper.parse(createDate, Config.DATE_FORMAT);
    }

    public Date getUpdateDate() {
        return DateHelper.parse(updateDate, Config.DATE_FORMAT);
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = DateHelper.format(updateDate, Config.DATE_FORMAT);
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public Date getDeadlineDate() {
        return DateHelper.parse(deadlineDate, Config.DATE_FORMAT);
    }

    public void setDeadlineDate(Date deadlineDate) {
        this.deadlineDate = DateHelper.format(deadlineDate, Config.DATE_FORMAT);
    }

    public String getDescription() {
        return description;
    }
    
    public Date getStartDate() {
		return DateHelper.parse(startDate, Config.DATE_FORMAT);
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String toString() {
        return String.format("Project [ID: %d, Name: '%s', Create: '%s', " +
                        "Update: '%s', Budget: %.2f, Deadline: '%s']", projectId, projectName,
                createDate, updateDate, budget, deadlineDate);
    }

	public List<Activity> getAcitivies() {
		return acitivies;
	}

	public void setAcitivies(List<Activity> acitivies) {
		this.acitivies = acitivies;
	}

    public Date getFinishDate() {
        return DateHelper.parse(finishDate, Config.DATE_FORMAT);
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = DateHelper.format(finishDate, Config.DATE_FORMAT);
    }
}
