package ninefoo.model.object;

/**
 * This class represents a status entity in the database.
 * Created on 30-May-2015.
 *
 * @author Farzad MajidFayyaz
 */
public class Status {
    private int statusId;
    private String statusName;

    public Status(String statusName) {
        this.statusName = statusName;
    }

    public int getStatusId() {
        return statusId;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }
}
