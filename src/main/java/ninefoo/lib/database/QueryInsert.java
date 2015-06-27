package ninefoo.lib.database;

public interface QueryInsert {
	public boolean run();
	public QueryInsert insert(String table);
	public QueryInsert where(String condition);
	public QueryInsert where(String condition, String option);
	public int getLastInsertId();
	public QueryInsert set(String attribute, String value);
	public int getAffectedRows();
	public String getQuery();
}
