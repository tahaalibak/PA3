/**
 * My own Point class to easily deal store and retrieve point coordinates in doubles
 * @author tahaalibak
 *
 */
public class Point {

	//fields:
	private double x;
	private double y;
	
	public Point(){
		this.setX(0);
		this.setY(0);
	}
	
	public Point(double x, double y){
		this.setX(x);
		this.setY(y);
	}
	
	public Point(int x, int y){
		this.setX((double) x);
		this.setY((double) y);
	}
	
	public void setLocation(double x, double y){
		this.setX(x);
		this.setY(y);
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getX() {
		return x;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getY() {
		return y;
	}
	
	public static int testThis(){
		int check = 0;

		Point test1 = new Point(0,0);
		
		if (test1.getX()!=0){
			check++;
		}
		
		test1.setX(1);
		
		if (test1.getX()!=1){
			check++;
		}
		
		if (test1.getY()!=0){
			check++;
		}
		
		test1.setY(1);
		
		if (test1.getY()!=1){
			check++;
		}
		
		return check;
	}
}
