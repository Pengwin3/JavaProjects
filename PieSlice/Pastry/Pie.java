package Pastry;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

/**
 * A model of a pie with a single slice taken out of it.
 * 
 * @author zeil
 *
 */
public class Pie {
    @Override
    public String toString() {
		return "Pastry.Pie{" +
				"radius=" + getRadius() +
				", baseColor=" + getBaseColor() +
				", sliceColor=" + getSliceColor() +
				", sliceAngle=" + getSliceAngle() +
				", center=" + getCenter() +
				'}';
	}

	private int radius;
	
	private Color baseColor;
	
	private Color sliceColor;
	
	private int sliceAngle;
	
	private Point center;
	
	/**
	 * Create a new pie
	 */
	public Pie ()
	{

		setRadius(20);
		setCenter(new Point(20,20));
		setSliceAngle(0);
		setBaseColor(Color.cyan);
		setSliceColor(Color.green);
	}
	
	
	/**
	 * Draw the pie on a graphics device
	 * 
	 * @param g the graphics device on which to draw
	 */
	public void draw (Graphics2D g)
	{
		g.setColor(getBaseColor());
		g.fillOval(getCenter().x- getRadius(), getCenter().y- getRadius(), 2* getRadius(), 2* getRadius());
		g.setColor(getSliceColor());
		g.fillArc(getCenter().x- getRadius(), getCenter().y- getRadius(), 2* getRadius(), 2* getRadius(), 0, getSliceAngle());
	}


	/**
	 * Angle of the slice, in degrees
	 */
	public int getSliceAngle() {
		return sliceAngle;
	}

	public void setSliceAngle(int sliceAngle) {
		this.sliceAngle = sliceAngle;
	}

	/**
	 * The size of the pie
	 */
	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	/**
	 * Color used to draw the pie
	 */
	public Color getBaseColor() {
		return baseColor;
	}

	public void setBaseColor(Color baseColor) {
		this.baseColor = baseColor;
	}

	/**
	 * Color use to draw the empty space left behind by the slice
	 */
	public Color getSliceColor() {
		return sliceColor;
	}

	public void setSliceColor(Color sliceColor) {
		this.sliceColor = sliceColor;
	}

	/**
	 * Location of the center of the pie.
	 */
	public Point getCenter() {
		return center;
	}

	public void setCenter(Point center) {
		this.center = center;
	}
}
