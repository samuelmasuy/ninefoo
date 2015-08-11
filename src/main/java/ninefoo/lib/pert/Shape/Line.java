package ninefoo.lib.pert.Shape;

/**
* Graph Theory GUI
* Coded by Amir El Bawab
* Date: 9 May 2015
* License: MIT License ~ Please read License.txt for more information about the usage of this software
* */
import java.awt.Point;
import java.awt.geom.Line2D;

public class Line extends Line2D.Double{
	private Point p1, p2;
	private PertShape c1, c2;
	public int expectedDuration, standardDeviation;
	
	/**
	 * Set points objects
	 * @param p1
	 * @param p2
	 */
	public void setPoints(Point p1, Point p2){
		this.p1 = p1;
		this.p2 = p2;
		this.refreshLine();
	}
	
	/**
	 * Set x1 and y1
	 * @param x
	 * @param y
	 */
	public void setXY1(int x, int y){
		x1 = x;
		y1 = y;
	}
	
	/**
	 * Set x2 and y2
	 * @param x
	 * @param y
	 */
	public void setXY2(int x, int y){
		x2 = x;
		y2 = y;
	}
	
	/**
	 *  Establish link between Edge and Line
	 * @param edge
	 */
	public void setEdge(PertShape c1, PertShape c2){
		this.c1 = c1;
		this.c2 = c2;
		this.setPoints(c1.getCenter(), c2.getCenter());
	}
	
	/**
	 * Refresh line
	 */
	public void refreshLine(){
		if(p1 != null && p2 != null){
			x1 = p1.x;
			y1 = p1.y;
			x2 = p2.x;
			y2 = p2.y;
		}
	}
}
