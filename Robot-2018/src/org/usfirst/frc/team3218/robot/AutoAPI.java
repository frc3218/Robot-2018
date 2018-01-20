package org.usfirst.frc.team3218.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;

public class AutoAPI {


 //distances
 public final static float WALL_TO_SWITCH_CHANNEL = 67.188f;
 public final static float AUTOLINE = 120;
 public final static float WALL_TO_SWITCH = 140.188f;
 public final static float WALL_TO_PLATFORM_CHANNEL = 235.25f;
 public final static float MID_LINE = 323.16f;
 
 final static float TICKS_PER_INCH = 1/256; 	
 
	
 /**
 * @param distance in inches, positive forwards, negative, backwards
 * @param speed in motor power, 0<s<1
 */
 	public static void driveStraight(float distance, int speed, int acceleration){
 		
 		
 		Robot.driveTrain.rightMidDrive.set(ControlMode.MotionMagic, 0);
 		Robot.driveTrain.rightMidDrive.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
 		Robot.driveTrain.rightMidDrive.configMotionCruiseVelocity(speed, 0);
 		Robot.driveTrain.rightMidDrive.configMotionAcceleration(acceleration, 0);
 		Robot.driveTrain.rightMidDrive.set(distance);
    
    
    /*
    	liftCim.set(ControlMode.MotionMagic, 0);
    	liftCim.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
    	liftCim.configMotionCruiseVelocity(CruiseVelocity, 0);
    	liftCim.configMotionAcceleration(Acceleration, 0);
    	*/
    	
 		speed *= Math.signum(distance);
	
 	
	}
	
 	
	public static void rotate(float angle, float speed){
		
		speed *= Math.signum(angle);
		
	}
	
	
	public static void moveToHeight(){
		
	}
	
	public static void resetEncoders()
	{
		//Robot.lift..setSelectedSensorPosition(0, 0, 0);
	
	}
}
