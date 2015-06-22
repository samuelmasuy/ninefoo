package ninefoo.lib.database;

public interface QueryUpdate {
	public QueryUpdate update(String table);
	public QueryUpdate where(String condition);
	public QueryUpdate where(String condition, String option);
	public QueryUpdate set(String attribute, String value);
	public int getAffectedRows();
	public String getQuery();
	public boolean run();
}
