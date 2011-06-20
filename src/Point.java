//a point class that may store doubles
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
}
