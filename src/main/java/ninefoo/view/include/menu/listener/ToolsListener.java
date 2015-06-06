package ninefoo.view.include.menu.listener;

import ninefoo.view.include.menu.dialog.CreateProjectDialog;

public interface ToolsListener {
	public void newProject(CreateProjectDialog formDialog, String name, String budget, String startDate, String deadline, String description);
	public void newActivity();
	public void logout();
}
