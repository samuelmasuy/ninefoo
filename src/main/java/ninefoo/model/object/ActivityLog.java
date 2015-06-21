package ninefoo.model.object;

import java.util.Date;

/**
 * Created on 30-May-2015.
 * @author Farzad MajidFayyaz
 */
public class ActivityLog {
    private int activityLogId;
    private Project project;
    private Member member;
    private Status status;
    private Activity activity;

    public ActivityLog(Project project, Member member, Status status,
                       Activity activity, Date create_date) {
        this.project = project;
        this.member = member;
        this.status = status;
        this.activity = activity;
        this.create_date = create_date;
    }

    private Date create_date;

    public int getActivityLogId() {
        return activityLogId;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

	public Date getCreateDate() {
		return create_date;
	}

	public void setCreateDate(Date create_date) {
		this.create_date = create_date;
	}
}
