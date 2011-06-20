/**
 * Creates Objects of type disk, square or jewel
 * These objects have the following attributes:
 * 	private String type;	//to store object type e.g square, jewel, disk. This is more convenient than three classes in the given situation.
 *	private double width;	//width of square/jewel, diameter of circle, etc
 *	private Point position;	//coordinates of object center
 *	private double v;	//speed in direction of given angle (velocity) | units are meters per second
 *	private double angle;  //angle entered in degrees, stored in degrees
 *	private double xV;	//velocity in positive x direction
 *	private double yV;	//velocity in positive y direction
 *	private double g = -9.8;	//default set in meters per second squared, not final for interesting possibilities
 * @author tahaalibak
 *
 */


public class Object {

	private String type;	//to store object type e.g square, jewel, disk. This is more convenient than three classes in the given situation.
	private double width;	//width of square/jewel, diameter of circle, etc
	private Point position;	//coordinates of object center
	private double v;	//speed in direction of given angle (velocity) | units are meters per second
	private double angle;  //angle entered in degrees, stored in degrees
	private double xV;	//velocity in positive x direction
	private double yV;	//velocity in positive y direction
	private double g = -9.8;	//default set in meters per second squared, not final for interesting possibilities
	
	//constructor method for any object
	public Object(String type, double width, Point position, double v, double angle){
		/**
		 * This is the main constructor method.
		 * Input required: type, width, position, velocity, angle
		 */
		this.type = type;
		this.setPosition(position);
		this.angle = angle;
		this.v = v;
		this.setWidth(width);
		resolver();
		if (type.equals("square") || type.equals("jewel")){
			this.g = 0;
		}
		
	}
	
	//to freeze a moving object
	public void freezer(){
		/**
		 * This method freezes moving objects (same as setting to static)
		 */	
		this.v=0;
		this.setG(0);
		this.resolver();
	}
	
	//to resolve velocity in x and y directions
	public void resolver(){
		/**
		 * This method resolves an object's velocity and angle to velocities in positive x and y directions.
		 */
		this.angle = java.lang.Math.toRadians(this.angle);
		this.setxV(this.v*java.lang.Math.cos(this.angle));
		this.setyV(this.v*java.lang.Math.sin(this.angle));	
	}

	//to reset after a catch
	public void release(double v, double angle){
		/**
		 * This method maybe used to reactivate a frozen object
		 */
		this.g = -9.8;
		this.angle = angle;
		this.v = v;
		resolver();
	}
	

	//setter and getter methods
	
	public void setG(double g) {
		this.g = g;
	}


	public double getG() {
		return g;
	}


	public void setAngle(double angle) {
		this.angle = angle;
	}


	public double getAngle() {
		return angle;
	}
	
	public void setV(double v) {
		this.v = v;
	}


	public double getV() {
		return v;
	}

	public void setPosition(Point position) {
		this.position = position;
	}

	public Point getPosition() {
		return position;
	}

	public void setxV(double xV) {
		this.xV = xV;
	}

	public double getxV() {
		return xV;
	}

	public void setyV(double yV) {
		this.yV = yV;
	}

	public double getyV() {
		return yV;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getWidth() {
		return width;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}
}
