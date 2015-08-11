package ninefoo.controller.logic;

import java.util.ArrayList;
import java.util.List;

import ninefoo.helper.DateHelper;
import ninefoo.model.object.Activity;
import ninefoo.model.object.Project;


/**
 * This class will make the needed calculations for earned value 
 * analysis for the application
 * @author Vince Abruzzese
 *
 */
public class EarnedValueAnalysis 
{
	private Project project;

	public EarnedValueAnalysis(Project project)
	{
		this.project = project;
	}

	public float calculateBudgetAtCompletion()
	{
		List<Activity> activities = project.getAcitivies();

		float totalPlannedValue = 0;

		for(Activity activity : activities){

			//Check the earned value for an activity
			boolean isComplete = !activity.getFinishDate().after(DateHelper.getToday()) ? true : false;
			
			if (isComplete){
				totalPlannedValue += activity.getPlannedCost();
			}
		}
		return totalPlannedValue;
	}

	public float calculateTotalActualCost(){
		List<Activity> activities = project.getAcitivies();

		float totalActualCost = 0;

		for(Activity activity : activities){
			totalActualCost += activity.getActualCost();
		}

		return totalActualCost;
	}

	public float calculateTotalEarnedValue(){

		List<Activity> activities = project.getAcitivies();

		float totalEarnedValue = 0;

		for(Activity activity : activities){
			boolean isComplete = activity.getActualPercentage() == 100 ? true : false;

			if(isComplete){
				totalEarnedValue += activity.getPlannedCost();
			}

		}

		return totalEarnedValue;
	}

	public float calculateTotalPlannedCost()
	{
		List<Activity> activities = project.getAcitivies();

		float totalPlannedCost = 0;

		for(Activity activity : activities){
			totalPlannedCost += activity.getPlannedCost();
		}
		return totalPlannedCost;
	}
	
	public float calculateTotalCostVariance()
	{
		float earnedValue = this.calculateTotalEarnedValue();
		float actualCost = this.calculateTotalActualCost();
		
		return (earnedValue - actualCost);
	}
	
	public float calculateTotalScheduleVariance()
	{
		float earnedValue = this.calculateTotalEarnedValue();
		float plannedValue = this.calculateBudgetAtCompletion();
		
		return (earnedValue - plannedValue);
	}
	
	public float calculateCostPerformanceIndex()
	{
		float earnedValue = this.calculateTotalEarnedValue();
		float actualCost = this.calculateTotalActualCost();
		
		if (actualCost == 0)
			return 0;
		else
			return (earnedValue / actualCost);
	}

	
	public float calculateSchedulePerformanceIndex()
	{
		float earnedValue = this.calculateTotalEarnedValue();
		float plannedValue = this.calculateBudgetAtCompletion();
		
		if (plannedValue == 0)
			return 0;
		else
			return (earnedValue / plannedValue);
	}
	
	public float calculateEstimateAtCompletion()
	{
		float BAC = this.calculateBudgetAtCompletion();
		float CPI = this.calculateCostPerformanceIndex();
		
		if (CPI == 0)
			return 0;
		else
			return (BAC / CPI);
	}
	
	public float calculateEstimateToComplete()
	{
		float EAC = this.calculateEstimateAtCompletion();
		float AC = this.calculateTotalActualCost();
		
		return (EAC - AC);
	}

}