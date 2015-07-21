package ninefoo.lib.component;

import java.util.ArrayList;

public interface Refreshable {
	public void refresh();
	public static ArrayList<Refreshable> componentList = new ArrayList<>();
}
