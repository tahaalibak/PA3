
public class TestMethods {
	
	public static void main(String[] args){
		
		int GPErrors = GamePlay.testThis();
		int LCErrors = LevelCreator.testThis();
		int ObjErrors = Object.testThis();
		int PointErrors = Point.testThis();
		int TotalErrors = GPErrors+LCErrors+ObjErrors+PointErrors;
		
		System.out.println(GPErrors+" errors in GamePlay class.");
		System.out.println(LCErrors+" errors in LevelCreator class.");
		System.out.println(ObjErrors+" errors in Object class.");
		System.out.println(PointErrors+" errors in Point class.");
		System.out.println(TotalErrors+" errors combined.");
	}
}
