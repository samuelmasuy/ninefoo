package ninefoo.view.project;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

public class GanttChart_view extends JPanel{
	
	// Constants
	private final int rowSize = 20;
	private final int columnSize = 50;
	private final int startRow = 20;
	private final int startCol = 20;
	
	/**
	 * Constructor
	 */
	public GanttChart_view() {
		
		// Configure panel
		this.setBackground(Color.WHITE);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		// Cast to G2D
		Graphics2D g2 = (Graphics2D) g;
		
		// Better quality
		g2.setRenderingHint ( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON );
		
		drawCalendar(g2, 5);
		
	}
	
	/**
	 * Draw calendar
	 * @param g2
	 * @param weeks
	 */
	private void drawCalendar(Graphics2D g2, int weeks){
		
		Graphics2D g3 = (Graphics2D)g2.create();
		
		char days[] = {'M', 'T', 'W', 'T', 'F', 'S', 'S'};
		int row = startRow;
		Color lightGray = Color.decode("#D8D8D8");
		Color darkGray = Color.decode("#989898");
		
		for(int week = 0; week < weeks; week++){
			for(int day=0; day < days.length; day++){
				
				g3.setColor(darkGray);
				
				// Draw characters
				int column = day * columnSize + startCol + days.length * week * columnSize;
				g3.drawChars(days, day, 1, column , row);
				
				// Draw lines
				int lineXPosition = column + (int)(columnSize/1.7);
				if(day == days.length - 1)
					g3.setColor(darkGray);
				else
					g3.setColor(lightGray);
				g3.drawLine(lineXPosition, startRow, lineXPosition, this.getHeight());
			}
		}
	}
}
