package ninefoo.lib.pert.Shape;

import java.awt.Point;
import java.awt.geom.RoundRectangle2D;

public class PertShape extends RoundRectangle2D.Double{
	public static int DIAMETER = 100;
	private static double heightPercentage = 0.5;
	public static int HEIGHT = (int) (DIAMETER * heightPercentage);
	private Point centerPoint;
	
	// Data
	public int eventNumber;
	public double targetDate, expectedDate, standardDerivation;
		
	/**
	 * Constructor
	 */
	public PertShape(){
		this(0,0);
	}
	
	/**
	 * Constructor
	 * @param x
	 * @param y
	 */
	public PertShape(double x, double y) {
		centerPoint = new Point();
		setXY(x,y);
		width = DIAMETER;
		height = DIAMETER * heightPercentage;
		archeight = DIAMETER/3;
		arcwidth = archeight;
		
	}
	
	/**
	 * Set position of the circle by clicking in the center
	 * @param x
	 * @param y
	 */
	public void setXY(double x, double y) {
		int diam2 = DIAMETER/2;
		int height2 = HEIGHT/2;
		this.x = x-diam2;
		this.y = y-height2;
		refreshCircle();
	}
	
	/**
	 * Refresh circle
	 */
	public void refreshCircle(){
		width = DIAMETER;
		height = DIAMETER * heightPercentage;
		centerPoint.x = (int) (x + width/2);
		centerPoint.y = (int) (y + height/2);
	}
	
	/**
	 * Get center point of the circle
	 * @return Point
	 */
	public Point getCenter(){
		return centerPoint;
	}
}