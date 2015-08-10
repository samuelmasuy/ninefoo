package ninefoo.model.object;

import ninefoo.config.Config;
import ninefoo.helper.DateHelper;

import java.util.Date;
import java.util.List;

/**
 * This class represents a project entity in the database.
 * Created on 29-May-2015.
 *
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
    
    // Analysis value
    private double totalCost;
    private double totalPV;
    private double totalAC;
    private double totalEV;
    private double costVariance;
    private double scheduleVariance;
    private double cpi;
    private double spi;
    private double EAC;
    private double ETC;

    /**
     * This constructor is only used when we want to convert db entities to Java classes
     *
     * @param projectName  Name of the project
     * @param createDate   Date of creation
     * @param updateDate   Date of last update
     * @param budget       Budget of the project
     * @param deadlineDate Deadline date defined for the project
     * @param description  Optional description for the project
     */
    public Project(int projectId, String projectName, Date createDate, Date startDate,
                   Date updateDate, Double budget, Date deadlineDate, String description) {

        this.projectId = projectId;
        this.projectName = projectName;
        this.startDate = startDate;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.budget = budget;
        this.deadlineDate = deadlineDate;
        this.description = description;
    }

    /**
     * This constructor is used when creating a new project object in the Java code because
     * the ID of the project is determined when the project is added to the DB table.
     *
     * @param projectName  Name of the project
     * @param budget       Budget of the project
     * @param deadlineDate Deadline date defined for the project
     * @param description  Optional description for the project
     */
    public Project(String projectName, Double budget, Date startDate, Date deadlineDate, String description) {

        this.projectName = projectName;
        this.budget = budget;
        this.startDate = startDate;
        this.deadlineDate = deadlineDate;
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
        this.updateDate = updateDate;
    }

    public Double getBudget() {
        return budget;
    }

    public void setBudget(Double budget) {
        this.budget = budget;
    }

    public Date getDeadlineDate() {
        return deadlineDate;
    }

    public void setDeadlineDate(Date deadlineDate) {
        this.deadlineDate = deadlineDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
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
        this.finishDate = finishDate;
    }

    /************************************\
     * GETTER AND SETTERS FOR ANALYSIS
     ************************************/
    
    public double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}

	public double getTotalPV() {
		return totalPV;
	}

	public void setTotalPV(double totalPV) {
		this.totalPV = totalPV;
	}

	public double getTotalAC() {
		return totalAC;
	}

	public void setTotalAC(double totalAC) {
		this.totalAC = totalAC;
	}

	public double getTotalEV() {
		return totalEV;
	}

	public void setTotalEV(double totalEV) {
		this.totalEV = totalEV;
	}

	public double getCostVariance() {
		return costVariance;
	}

	public void setCostVariance(double costVariance) {
		this.costVariance = costVariance;
	}

	public double getScheduleVariance() {
		return scheduleVariance;
	}

	public void setScheduleVariance(double scheduleVariance) {
		this.scheduleVariance = scheduleVariance;
	}

	public double getCpi() {
		return cpi;
	}

	public void setCpi(double cpi) {
		this.cpi = cpi;
	}

	public double getSpi() {
		return spi;
	}

	public void setSpi(double spi) {
		this.spi = spi;
	}

	public double getEAC() {
		return EAC;
	}

	public void setEAC(double eAC) {
		EAC = eAC;
	}

	public double getETC() {
		return ETC;
	}

	public void setETC(double eTC) {
		ETC = eTC;
	}
}
