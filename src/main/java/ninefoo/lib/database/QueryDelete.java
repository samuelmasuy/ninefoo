package ninefoo.lib.database;

public interface QueryDelete {
	public QueryDelete delete(String table);
	public QueryDelete where(String condition);
	public QueryDelete where(String condition, String option);
	public int getAffectedRows();
	public String getQuery();
	public boolean run();
}
