package ninefoo.lib.database;

import java.sql.ResultSet;

public interface QueryCustom {
	public ResultSet getResult();
	public String getQuery();
	public boolean run(String query);
}
