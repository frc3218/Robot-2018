package org.usfirst.frc.team3218.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.*;
public class AutoAPI {


 //distances
 public final static float WALL_TO_SWITCH_CHANNEL = 67.188f;
 public final static float AUTOLINE = 120;
 public final static float WALL_TO_SWITCH = 140.188f;
 public final static float WALL_TO_PLATFORM_CHANNEL = 235.25f;
 public final static float MID_LINE = 323.16f;
 public static double[] averages;
 static double[][] sensorValues;
 final static float TICKS_PER_INCH = 73; 	
 
	
 /**
 * @param distance in inches, positive forwards, negative, backwards
 * @param speed in motor power, 0<s<1
 */
 	public static void driveStraight(float distance, int speed, int acceleration){
 		distance *= TICKS_PER_INCH;
 		speed *= Math.signum(distance);// may not be needed
 		
 		Robot.driveTrain.rightMidDrive.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
 		Robot.driveTrain.rightMidDrive.configMotionCruiseVelocity(speed, 0);
 		Robot.driveTrain.rightMidDrive.configMotionAcceleration(acceleration, 0);
 		
 		Robot.driveTrain.rightMidDrive.set(ControlMode.MotionMagic, distance);
 		Robot.driveTrain.rightTopDrive.set(ControlMode.Follower,RobotMap.rightMidDriveID);
 		Robot.driveTrain.rightBottomDrive.set(ControlMode.Follower,RobotMap.rightMidDriveID);
 		
 		
 		Robot.driveTrain.leftMidDrive.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
 		Robot.driveTrain.leftMidDrive.configMotionCruiseVelocity(speed, 0);
 		Robot.driveTrain.leftMidDrive.configMotionAcceleration(acceleration, 0);
 		
 		Robot.driveTrain.leftMidDrive.set(ControlMode.MotionMagic, distance);
 		Robot.driveTrain.leftTopDrive.set(ControlMode.Follower,RobotMap.leftMidDriveID);
 		Robot.driveTrain.leftBottomDrive.set(ControlMode.Follower,RobotMap.leftMidDriveID);
 	
 	    
    
    	
 		
	
 	
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

	
	public static double sensorAverage (double newValue, String sensorName){
		int sensorIndex;
		int sampleCount = 0;
		switch(sensorName){
		case "gyro":sensorIndex = 1; sampleCount = 50;
		break;
		case "accelerometer": sensorIndex = 2; sampleCount = 50;
		break;
		case "leftEnc":sensorIndex = 3; sampleCount = 50;
		break;
		case "rightEnc":sensorIndex = 4; sampleCount = 50;
		break;
		case "liftEnc": sensorIndex = 5; sampleCount = 50;
		break;
		default: sensorIndex = 0; sampleCount = 0;
		}
		if(sensorIndex != 0){
	averages[sensorIndex] -= (sensorValues[sensorIndex][0])/sampleCount;
	for(int i=0; i<sampleCount-1;i++){
		sensorValues[sensorIndex][i] = sensorValues[sensorIndex][i+1];
		
	}
		sensorValues[sensorIndex][sampleCount-1] = newValue;
		averages[sensorIndex]+= newValue/sampleCount;
		return averages[sensorIndex];
		}
		return 0;
	}
}
