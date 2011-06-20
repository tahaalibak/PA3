/**
 * This Class is used to create random levels
 * Game levels do have a difficulty level that may be specified
 */
import java.util.LinkedList;
import java.util.Random;

public class LevelCreator {
	
	public LevelCreator(){
		/**
		 * This is the default constructor.
		 */
	}
	
	//Creates a game level, given a difficulty setting
	public static LinkedList<Object> level(int difficulty){
		
		//fields
		int squares;
		int jewels;
		int disks;
		final double diskWidth = 2;
		final double jewelWidth = 2;
		final double squareWidth = 2;
		final int dimension = 30;
		double xMarker = diskWidth/2; // marks the x coordinate at which a new disk will be placed
		LinkedList<Object> objects = new LinkedList<Object>(); //a list of all objects being deployed
		Random generator = new Random(); // randomizer to place boxes
		boolean recurse = false; //used to regenerate level if flaws exist
		
		
		//difficulty settings
		switch (difficulty){
		//hard
		case 3:
			squares = 8;
			jewels = 3;
			disks = 4;
			break;
			
		//medium
		case 2:
			squares = 6;
			jewels = 2;
			disks = 4;
			break;
			
		//easy
		case 1:
		default:
			squares = 4;
			jewels = 1;
			disks = 2;
			break;
		}
		
		
		for (int i = 0; i<disks; i++){
			Point temp = new Point();
			temp.setLocation(xMarker,diskWidth/2);
			objects.add(new Object("disk",diskWidth,temp,0,0));
			xMarker = xMarker + diskWidth + 1;
		}
		
		for(int i = 0; i<squares; i++){
			// squares are placed only at values_occupied_by_disks+1+squareWidth/2 and above
			
			//for x value
			int j = (int) (java.lang.Math.ceil(xMarker+squareWidth/2));
			int k = generator.nextInt(dimension-j);
			k = k+j;
			
			//for y value
			int l = (int) (java.lang.Math.ceil(diskWidth+1+squareWidth/2));
			int m = generator.nextInt(dimension-l);
			m = m+l;
			
			
			objects.add(new Object("square",squareWidth,new Point(k,m),0,0));
		}

		for(int i = 0; i<jewels; i++){
			// jewels are placed only on free spaces
			
			//for x value
			int j = (int) (java.lang.Math.ceil(xMarker+jewelWidth/2));
			int k = generator.nextInt(dimension-j);
			k = k+j;
			
			//for y value
			int l = (int) (java.lang.Math.ceil(diskWidth+1+jewelWidth/2));
			int m = generator.nextInt(dimension-l);
			m = m+l;
			
			objects.add(new Object("jewel",jewelWidth,new Point(k,m),0,0));
			
			//checks if new jewel collides with any previously deployed object
			if (GamePlay.collides(objects.get(objects.size()-1), objects)==true){
				recurse = true;
			}
			
		}
		
		//checks for fundamental flaw of jewels colliding with other objects and resets level accordingly
		if (recurse == true){
			level(difficulty);
		}
		return objects; //this list of objects now defines the game level
		
	}

}
