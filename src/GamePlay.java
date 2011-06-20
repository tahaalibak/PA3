import java.util.LinkedList;
import java.util.Scanner;
// this class contains methods to update the state of the game w.r.t time


public class GamePlay {
	
	//default constructor
	public GamePlay(){
	//this is the default constructor
	}
	
	//carries out calculations based on Newtonian mechanics
	//time elapsed (t) is in seconds
	//updates object accordingly
	public static void updater(double t, Object object){
		
		//Fields
		Point temp = new Point();
		
		//use s = u*t + 0.5*a*t^2 to update object position
				
		double tempX = (object.getPosition().getX()+object.getxV()*t);
		double tempY = (object.getPosition().getY()+object.getyV()*t+(0.5*object.getG()*t*t));
		temp.setLocation(tempX,tempY);
		object.setPosition(temp);
		
		//uses v = u + a*t to update object velocity
		object.setyV(object.getyV()+object.getG()*t);
		
		//fixes object position and velocity to allow it to bounce on the x  and y axis
		if (object.getPosition().getX()<object.getWidth()/2){
			object.setxV(java.lang.Math.abs(object.getxV()));
			temp.setLocation(object.getWidth()/2,object.getPosition().getY());
			object.setPosition(temp);
		}
		if (object.getPosition().getY()<object.getWidth()/2){
			object.setyV(java.lang.Math.abs(object.getyV()));
			temp.setLocation(object.getPosition().getX(),object.getWidth()/2);
			object.setPosition(temp);
		}
	}
	
	//freezes objects if they collide and locks them from being moved again
	public static boolean collisionControl(Object a, Object b){
		//Sets a proximity level at which objects freeze
		double distanceThreshold = a.getWidth()/2+b.getWidth()/2;
		//calculates the actual distance between centers of objects
		double actualDistance = java.lang.Math.sqrt(java.lang.Math.pow((a.getPosition().getX()-b.getPosition().getX()),2)+java.lang.Math.pow((a.getPosition().getY()-b.getPosition().getY()),2));
		if (actualDistance<=distanceThreshold){
			a.freezer();
			b.freezer();
			a.setType("collided");
			b.setType("collided");
			return true;
		}
		return false;
	}
	
	//checks an object for collision with all other objects
	public static boolean collides(Object object, LinkedList<Object> objects) {
		LinkedList<Object> copy = new LinkedList<Object>();
		copy.addAll(objects);
		copy.remove(object);
		for (int i = 0; i<copy.size(); i++){
			if (collisionControl(object,copy.get(i))==true){
				return true;
			}
		}
		return false;
	}
	
	//prints types and locations of all objects in given level
	public static void printer(LinkedList<Object> objects){
		System.out.println();
		for (int i = 0; i<objects.size(); i++){
			System.out.println(objects.get(i).getType()+": "+"("+objects.get(i).getPosition().getX()+","+objects.get(i).getPosition().getY()+")");
		}
		System.out.println();
	}
	
	//returns whether player has any disks left in given level
	public static boolean disksRemain(LinkedList<Object> level){
		boolean disksRemain = false;
		for (int i=0; i<level.size(); i++){
			if (level.get(i).getType().equals("disk")){
				disksRemain = true;
			}
		}
		return disksRemain;
	}
	
	//returns whether there are any jewels left in given level
	public static boolean jewelsRemain(LinkedList<Object> level){
		boolean jewelsRemain = false;
		for (int i=0; i<level.size(); i++){
			if (level.get(i).getType().equals("jewel")){
				jewelsRemain = true;
			}
		}
		return jewelsRemain;
	}
	
	//returns whether the level is still playable
	public static boolean isPlayable(boolean disksRemain, boolean jewelsRemain, double timeRemaining){
		boolean isPlayable = true;
		if (disksRemain==false || jewelsRemain==false || timeRemaining<=0){
			isPlayable=false;
		}
		return isPlayable;
	}
	
	
	
	//continuesPlaying the game until out of disks or jewels or time
	public static void continuePlay(LinkedList<Object> level){

		double timeRemaining = 200; //total time to finish level in seconds
		double moveTime = 0;
		boolean disksRemain = disksRemain(level);
		boolean jewelsRemain = jewelsRemain(level);
		boolean isPlayable = isPlayable(disksRemain,jewelsRemain, timeRemaining);
		
		while(isPlayable == true){
			
			System.out.println("Time Remaining: "+timeRemaining); //Prints out total time left
			moveTime = play(level, timeRemaining);
			timeRemaining = timeRemaining - moveTime;
			disksRemain = disksRemain(level);
			jewelsRemain = jewelsRemain(level);
			isPlayable = isPlayable(disksRemain,jewelsRemain,timeRemaining);

		}
		
		System.out.println("Time Remaining = "+timeRemaining);
		
		if (jewelsRemain==false){
			System.out.println("YOU WON!!!  --CONGRATULATIONS!!!---  ROCK ON!!!");
		}
		else{
			System.out.println("GAME OVER - TRY AGAIN");
		}
	}
	
	//play level
	public static double play (LinkedList<Object> level, double timeLeft){
		
		//fields
		Scanner console = new Scanner(System.in);
		double v;
		double angle;
		double catchTime;
		double timeSpanned = 0; // this records the actual time while the disk was  moving as directed
		boolean collision = false;
		int currentDisk = 0;
		
		//launch details
		System.out.print("Launch Speed (meters per second): ");
		v = console.nextDouble();
		System.out.print("Launch Direction (angle from horizontal in degrees): ");
		angle = console.nextDouble();
		System.out.print("Time before next catch: (seconds)");
		catchTime = console.nextDouble();
		
		
		//sets launch details to further most disk
		for (int i = 0; i<level.size();i++){
			if (level.get(i).getType().equals("disk")){
				currentDisk = i;
			}
		}
		level.get(currentDisk).setV(v);
		level.get(currentDisk).setAngle(angle);

		level.get(currentDisk).resolver();
		
		for (double t=0; t<catchTime;){
			double incrementer = 0.01;
			updater(incrementer, level.get(currentDisk));
			t = t + incrementer;
			collision = collides(level.get(currentDisk),level);
			if (collision == false){
				timeSpanned = timeSpanned + incrementer;
				if (timeSpanned>=timeLeft){
					return timeSpanned;
				}
			}
		}
		
		if (collision == true){
			System.out.println("Disk Lost!");
		}
		
		printer(level);
		
		return timeSpanned;
	}
}
