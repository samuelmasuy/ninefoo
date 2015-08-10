package ninefoo.controller.logic;

import ninefoo.model.object.Activity;

/**
 * This class will make the needed calculations for earned value 
 * analysis for the application
 * @author Vince Abruzzese
 *
 */
public class EarnedValueAnalysis 
{
	/**
	 * calculates the expected duration. t = (a + 4m + b) / 6
	 * @param Activity
	 * @return Expected Duration
	 */
	public float calculateExpectedDuration(Activity activity)
	{
		return (
			   (activity.getOptimisticDuration() +
			   (activity.getLikelyDuration() * 4) +
			    activity.getPessimisticDuration()) 
				/ 6
				);
	}
}