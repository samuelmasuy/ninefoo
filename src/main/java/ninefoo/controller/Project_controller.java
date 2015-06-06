package ninefoo.controller;

import ninefoo.model.Project;
import ninefoo.model.Project_model;
import ninefoo.view.frame.UpdatableView;
import ninefoo.view.listeners.ProjectListener;
import org.apache.logging.log4j.LogManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Project_controller extends AbstractController implements ProjectListener{
	private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger();

	private Project_model pm = new Project_model();

	/**
	 * Constructor
	 * @param view
	 */
	public Project_controller(UpdatableView view) {
		super(view);
	}
	
	@Override
	public void createProject(String name, String budget, String deadline, String description) {
		
		if (name.isEmpty()) {
			LOGGER.info("name of the project is empty");
			return;

		}
		double budgetNumber;
		try {
			budgetNumber = Double.parseDouble(budget);
		}
		catch (IllegalArgumentException e) {
			LOGGER.info("not a valid budget");
			return;
		}

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		Date deadlineDate;
		try {
			deadlineDate = sdf.parse(deadline);
		} catch (ParseException e) {
			LOGGER.info("Invalid Date format, please try again.");
			return;
		}

		if (description.length() > 150) {
			LOGGER.info("Description can only take up to 150 characters");
			return;
		}

		Project project = new Project(name, budgetNumber, deadlineDate, description);

		int success = pm.insertNewProject(project);
		if (success != 0) {
			LOGGER.info("Succesfully inserted new project with ID: " + success);
		}

	}
}
