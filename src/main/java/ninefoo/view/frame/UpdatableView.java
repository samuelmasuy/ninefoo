package ninefoo.view.frame;

import ninefoo.view.listeners.MemberListener;
import ninefoo.view.listeners.ProjectListener;

public interface UpdatableView {
	public void updateLogin(boolean success, String message);
	public void updateRegister(boolean success, String message);
	public void updateCreateProject(boolean success, String message);
	
	public void setMemberListener(MemberListener memberListener);
	public void setProjectListener(ProjectListener projectListener);
}
