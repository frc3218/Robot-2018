package org.usfirst.frc.team3218.robot;

public class AutoAPI {


 //distances
 public final static float WALL_TO_SWITCH_CHANNEL = 67.188f;
 public final static float AUTOLINE = 120;
 public final static float WALL_TO_SWITCH = 140.188f;
 public final static float WALL_TO_PLATFORM_CHANNEL = 235.25f;
 public final static float MID_LINE = 323.16f;
 
	
	
	
 /**
 * @param distance in inches, positive forwards, negative, backwards
 * @param speed in motor power, 0<s<1
 */
 	public static void driveStraight(float distance, float speed){
 		
 		
 		
 		speed *= Math.signum(distance);
	
 	
	}
	
 	
	public static void rotate(float angle, float speed){
		
		speed *= Math.signum(angle);
		
	}
	
	
	public static void moveToHeight(){
		
	}
	
}
