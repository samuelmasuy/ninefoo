package ninefoo.helper;

import static org.junit.Assert.*;

import java.awt.GridBagConstraints;

import org.junit.Test;

public class LayoutHelperTest {
		
		@Test
		
		public void TestLayoutHelper(){
			GridBagConstraints expected = new GridBagConstraints();
			int col=3, row=3, mergedCells=3;
		GridBagConstraints testGrid = new GridBagConstraints();
		
		testGrid.gridx = col;
		testGrid.gridy = row;
		testGrid.gridwidth = mergedCells;
		
		LayoutHelper.gcGrid(expected,row,col,mergedCells);
		
		assertEquals("hi",testGrid.gridx,expected.gridx);
		assertEquals("hi",testGrid.gridy,expected.gridy);
		assertEquals("hi",testGrid.gridwidth,expected.gridwidth);
		}
}
