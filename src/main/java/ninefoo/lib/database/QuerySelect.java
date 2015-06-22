package ninefoo.lib.database;

import java.sql.ResultSet;

public interface QuerySelect {
	public boolean run();
	public QuerySelect select(String attributes);
	public QuerySelect from(String table);
	public QuerySelect where(String condition);
	public QuerySelect where(String condition, String option);
	public String getQuery();
	public ResultSet getResult();
}
