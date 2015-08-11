package ninefoo.lib.pert.Panel;

/**
* Graph Theory GUI
* Coded by Amir El Bawab
* Date: 9 May 2015
* License: MIT License ~ Please read License.txt for more information about the usage of this software
* */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import ninefoo.lib.graph.Graph;
import ninefoo.lib.pert.Shape.Line;
import ninefoo.lib.pert.Shape.PertShape;

public class PertPanel extends JPanel {
	
	private static final long serialVersionUID = 1297243587767588319L;
	private Line edgeTmp;
	private Dimension dim = new Dimension(500,500);
	private ArrayList<Line> lines;
	private ArrayList<PertShape> circles;
	private Graph graph;
	
	// Constants
	int startX = 100;
	int startY = 100;
	int xGap = 200;
	int yGap = 200;
	
	// Arrow position relative to the edge
	private final double position = 1d/3;
	
	public PertPanel(int size) {
		
		// Initialize lists
		lines = new ArrayList<>();
		circles = new ArrayList<>();
		graph = new Graph(size);
		
		// Drag node listener
		MouseAdapter dragListener = new MouseAdapter() {
			PertShape activeCircle;
			
			public void mousePressed(MouseEvent e) {
				
				// Search for the circle at x y position and keep track of it
				for(int i = 0; i< circles.size(); i++){
					if(circles.get(i).contains(e.getX(), e.getY())){
						activeCircle = circles.get(i);
						break;
					}
				}
			}
			
			public void mouseDragged(MouseEvent e) {
				
				// Move the circle if selected
				if(activeCircle != null){
					
					// Not less than Diam/2
					int x = e.getX();
					int y = e.getY();
					if(e.getX() < PertShape.DIAMETER/2)
						x = PertShape.DIAMETER/2;
					
					if(e.getY() < PertShape.HEIGHT/2)
						y = PertShape.HEIGHT/2;
					
					// Update circle position
					activeCircle.setXY( x, y);
					repaint();
				} 
				
			}
			
			public void mouseReleased(MouseEvent e) {
				
				// If circle selected, deselect it
				if(activeCircle != null){
					checkBounds(e);
					activeCircle = null;
				}
			}
		};
		
		// Add listener
		addMouseListener(dragListener);
		addMouseMotionListener(dragListener);
		
		// Panel size
		setPreferredSize(dim);
	}
	
	/**
	 * Paint nodes and edges
	 */
	protected void paintComponent(Graphics g) {
		
		// Clear everything
		super.paintComponent(g);
		
		// Store as G2D
		Graphics2D g2 = (Graphics2D) g;
		
		// Higher resolution
		g2.setRenderingHint ( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON );
		
		// Set color to black
		g2.setColor(Color.BLACK);
		
		// Edge being built
		if(edgeTmp != null) {
			
			// Draw line
			g2.draw(edgeTmp);
			
			// Create independent graphics
			Graphics2D gtmp = (Graphics2D) g2.create();
			
			// Calculate position on the edge
			double x = edgeTmp.x2 + (edgeTmp.x1 - edgeTmp.x2) * position;
			double y = edgeTmp.y2 + (edgeTmp.y1 - edgeTmp.y2) * position;
			
			// Draw arrow
			Polygon arrowHead = new Polygon();  
			arrowHead.addPoint( 0, 6 );
			arrowHead.addPoint( -6, -6);
			arrowHead.addPoint( 6,-6);
			
		    double angle = Math.atan2(edgeTmp.y2 - edgeTmp.y1, edgeTmp.x2 - edgeTmp.x1);
		    gtmp.translate( (int)x , (int)y);
		    gtmp.rotate((angle-Math.PI/2d));
		    gtmp.fill(arrowHead);
		}
		
		// Draw all existing edges
		for (int i = 0; i < lines.size(); i++) {
			lines.get(i).refreshLine();
			
			// Arrow head
			Polygon arrowHead = new Polygon();  
			arrowHead.addPoint( 0, 6 );
			arrowHead.addPoint( -6, -6);
			arrowHead.addPoint( 6,-6);
			
			// Color edge
			g2.setColor(Color.BLACK);
			
			// Create independent graphics
			Graphics2D g3 = (Graphics2D) g2.create();
			
			// Draw line
			g2.draw(lines.get(i));
			
			// Calculate position on the edge
			double x = lines.get(i).x2 + (lines.get(i).x1 - lines.get(i).x2) * position;
			double y = lines.get(i).y2 + (lines.get(i).y1 - lines.get(i).y2) * position;
			
		    double angle = Math.atan2(lines.get(i).y2-lines.get(i).y1, lines.get(i).x2-lines.get(i).x1);
		    g3.translate( (int)x , (int)y);
		    g3.rotate((angle-Math.PI/2d));
		    g3.fill(arrowHead);
		    
		    g2.drawString("t=" + lines.get(i).expectedDuration, (float)x-20, (float)y - 20);
		    g2.drawString("s=" + lines.get(i).standardDeviation, (float)x-20, (float)y - 10);
		    
		}
		
		// Reset color to black
		g2.setColor(Color.BLACK);
		
		// Draw circles
		for (int i = 0; i < circles.size(); i++) {
			PertShape current = circles.get(i);
			current.refreshCircle();
			g2.setColor(Color.WHITE);
			g2.fill(current);
			
			// If vertex visited
			g2.setColor(Color.BLACK);
			g2.draw(current);
			
			// Variables
			Line2D.Double line;
			Point from, to;
			
			// Vertical
			line = new Line2D.Double();
			from = new Point(current.getCenter().x, current.getCenter().y + PertShape.HEIGHT/2);
			to = new Point(current.getCenter().x, current.getCenter().y - PertShape.HEIGHT/2);
			line.setLine(from, to);
			g2.draw(line);
			
			// Horizontal
			line = new Line2D.Double();
			from = new Point(current.getCenter().x + PertShape.DIAMETER/2, current.getCenter().y);
			to = new Point(current.getCenter().x - PertShape.DIAMETER/2, current.getCenter().y);
			line.setLine(from, to);
			g2.draw(line);
			
			// Values
			String value;
			value = current.eventNumber + "";
			g2.drawString(value, current.getCenter().x - (PertShape.DIAMETER/4) - g2.getFontMetrics().stringWidth(value)/2, current.getCenter().y - (PertShape.HEIGHT/4) + 6);

			value = current.targetDate + "";
			g2.drawString(value, current.getCenter().x + (PertShape.DIAMETER/4) - g2.getFontMetrics().stringWidth(value)/2, current.getCenter().y - (PertShape.HEIGHT/4) + 6);

			value = current.expectedDate + "";
			g2.drawString(value, current.getCenter().x - (PertShape.DIAMETER/4) - g2.getFontMetrics().stringWidth(value)/2, current.getCenter().y + (PertShape.HEIGHT/4) + 6);

			value = current.standardDerivation + "";
			g2.drawString(value, current.getCenter().x + (PertShape.DIAMETER/4) - g2.getFontMetrics().stringWidth(value)/2, current.getCenter().y + (PertShape.HEIGHT/4) + 6);
		}
	}
	
	/**
	 * Check bounds of a panel
	 * @param e
	 */
	private void checkBounds(MouseEvent e) {
		// Increase width when needed
		if(getWidth() - e.getX() < 200){
			dim.setSize(Math.max(e.getX(), Math.max(dim.getWidth(), getWidth()))+200, dim.getHeight());
			setPreferredSize(dim);
			repaint();
			revalidate();
			
		}
		
		// Increase height when needed
		if(getHeight() - e.getY() < 200){
			dim.setSize( dim.getWidth(), Math.max(e.getY(),Math.max(dim.getHeight(), getHeight()))+200);
			setPreferredSize(dim);
			repaint();
			revalidate();
		}
	}
	
	/**
	 * Add node
	 * @param point
	 * @return
	 */
	public PertShape addNode(){
		PertShape circle = new PertShape();
		circles.add(circle);
		return circle;
	}
	
	/**
	 * Add edge
	 * @param from
	 * @param to
	 */
	public void addEdge(PertShape from, PertShape to) {
		Line line = new Line();
		line.setEdge(from, to);
		lines.add(line);
		
		// Search for from and to
		int fromIndex = -1;
		int toIndex = -1;
		for(int i=0; i<circles.size(); i++){
			if(circles.get(i) == from)
				fromIndex = i;
			if(circles.get(i) == to)
				toIndex = i;
		}
		
		// Add to the graph
		graph.addEdge(fromIndex, toIndex);
	}
	
	public void prettify(){
		int depth = 0;
		int x = startX;
		int y = startY;
		while(graph.nDepth(depth).size() > 0){
			List<Integer> nodes = graph.nDepth(depth++);
			y = startY;
			for(int i=0; i<nodes.size(); i++){
				circles.get(nodes.get(i)).setXY(x, y);
				y += yGap;
			}
			x += xGap;
		}
		repaint();
	}
}
