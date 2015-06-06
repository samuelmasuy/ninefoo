package ninefoo.view.project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class GanttChart_view extends JPanel{
	
	private static final long serialVersionUID = 6876353610483002088L;
	
	// Constants
	private final int rowSize = 20;
	private final int columnSize = 50;
	private final int startRow = 20;
	private final int startCol = 20;
	
	// Define components
	private JPanel panel;
	
	/**
	 * Constructor
	 */
	public GanttChart_view() {
		
		// Set layout
		this.setLayout(new BorderLayout());
		
		// Define components
		panel = new JPanel(){
			
			private static final long serialVersionUID = 8383128594912669663L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				
				// Cast to G2D
				Graphics2D g2 = (Graphics2D) g;
				
				// Better quality
				g2.setRenderingHint ( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON );
				
				drawCalendar(g2);
				drawActivity(g2, 0, 0, 10, "Activity 1");
				drawActivity(g2, 1, 2, 2, "Activity 2");
				
			}
		};
		JScrollPane scrollPane = new JScrollPane(panel);
		
		// Configure panel
		panel.setBackground(Color.WHITE);
		panel.setPreferredSize(new Dimension(2000,0));
		
		// Add components
		this.add(scrollPane, BorderLayout.CENTER);
	}
	
	/**
	 * Draw activity
	 * @param g2
	 * @param row
	 * @param start
	 * @param end
	 * @param activity
	 */
	public void drawActivity(Graphics2D g2, int row, int start, int end, String activity){
		Graphics2D g3 = (Graphics2D)g2.create();
		
		// Adjust the position
		int padding = (int)(rowSize/1.2);
		row = (int)(row * rowSize * 1.5 + startRow) + padding;
		start = start * columnSize + startCol;
		end = end * columnSize;
		
		// Adjust row and graphics
		g3.setColor(new Color(0, 127, 255, 180));
		
		// Draw rectangle
		g3.fillRect(start, row, end, rowSize);
		
		// Adjust graphics
		g3.setColor(new Color(0, 100, 180));
		g3.fillRect(start, row, columnSize/10, rowSize);
		
		// Draw shadow
		g3.setColor(new Color(0, 100, 180));
		g3.drawChars(activity.toCharArray(), 0, activity.length(), start + 11, 1 + row + 3 * rowSize/4);
		
		// Draw the text
		g3.setColor(Color.WHITE);
		g3.drawChars(activity.toCharArray(), 0, activity.length(), start + 10, row + 3 * rowSize/4);
	}
	
	/**
	 * Draw calendar
	 * @param g2
	 * @param weeks
	 */
	private void drawCalendar(Graphics2D g2){
		
		Graphics2D g3 = (Graphics2D)g2.create();
		
		char days[] = {'M', 'T', 'W', 'T', 'F', 'S', 'S'};
		int row = startRow;
		Color lightGray = Color.decode("#D8D8D8");
		Color darkGray = Color.decode("#989898");
		
		// Calendar size
		int weeks = panel.getWidth() / (columnSize * days.length) + 2;
		
		for(int week = 0; week < weeks; week++){
			for(int day=0; day < days.length; day++){
				
				g3.setColor(darkGray);
				
				// Draw characters
				int column = day * columnSize + startCol + days.length * week * columnSize;
				g3.drawChars(days, day, 1, (int) (column + columnSize/2.3), row);
				
				// Draw lines
				if(day == 0)
					g3.setColor(darkGray);
				else
					g3.setColor(lightGray);
				g3.drawLine(column, startRow, column, this.getHeight());
			}
		}
	}
}
