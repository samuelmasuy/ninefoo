package ninefoo.model.object;

import ninefoo.config.Config;
import ninefoo.helper.DateHelper;

import java.util.Date;
import java.util.List;

/**
 * This class represents a project entity in the database.
 * Created on 29-May-2015.
 * @author Farzad MajidFayyaz
 */
public class Project {
    private int projectId;
    private String projectName;
    private Date createDate;
    private Date startDate;
    private Date updateDate;
    private Date finishDate;
    private Double budget;
    private Date deadlineDate;
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
    public Project(int projectId, String projectName, Date createDate, Date startDate,
            Date updateDate, Double budget, Date deadlineDate, String description) {

        this.projectId = projectId;
        this.projectName = projectName;
        this.startDate = (Date)startDate.clone();
        this.createDate = (Date)createDate.clone();
        this.updateDate = (Date)updateDate.clone();
        this.budget = budget;
        this.deadlineDate = (Date)deadlineDate.clone();
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
    public Project(String projectName, Double budget, Date startDate, Date deadlineDate, String description) {

        this.projectName = projectName;
        this.budget = budget;
        this.startDate = (Date)startDate.clone();
        this.deadlineDate = (Date)deadlineDate.clone();
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
        return createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = (Date)updateDate.clone();
    }

    public Double getBudget() {
        return budget;
    }

    public void setBudget(Double budget) {
    	this.budget = budget;
    }

    public Date getDeadlineDate() {
        return (Date)deadlineDate.clone();
    }

    public void setDeadlineDate(Date deadlineDate) {
        this.deadlineDate = (Date)deadlineDate.clone();
    }

    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description){
    	this.description = description;
    }
    
    public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = (Date)startDate.clone();
	}

	public String toString() {
        return String.format("Project [ID: %d, Name: '%s', Create: '%s', " +
                        "Update: '%s', Budget: %.2f, Deadline: '%s']", projectId, projectName,
                        DateHelper.format(createDate, Config.DATE_FORMAT),
                        DateHelper.format(updateDate, Config.DATE_FORMAT),
                        budget,
                        DateHelper.format(deadlineDate, Config.DATE_FORMAT));
    }

	public List<Activity> getAcitivies() {
		return acitivies;
	}

	public void setAcitivies(List<Activity> acitivies) {
		this.acitivies = acitivies;
	}

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = (Date)finishDate.clone();
    }
}
