package org.usfirst.frc.team3218.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;

import edu.wpi.first.wpilibj.Talon;

import org.usfirst.frc.team3218.robot.subsystems.DriveTrain;

import com.ctre.phoenix.motorcontrol.*;
public class AutoAPI {


 //distances in inches
 public final static int WALL_TO_SWITCH_CHANNEL = 67;
 public final static int AUTOLINE = 120;
 public final static int WALL_TO_SWITCH = 140;
 public final static int WALL_TO_PLATFORM_CHANNEL = 235;
 public final static int MID_LINE = 323;
 final static float TICKS_PER_INCH = 90; 
 
 public static double[] averages = new double[6];
 static double[][] sensorValues = new double [6][200];
 	
 
	
 /**
 * @param distance in inches, positive forwards, negative, backwards
 * @param speed in ticks, 0<s<1
 */
 	public static void driveStraight(int distance, int speed, int acceleration){
 		
 		distance *= TICKS_PER_INCH;
 		//speed *= Math.signum(distance);// may not be needed
 	
 		Robot.driveTrain.rightMidDrive.configMotionCruiseVelocity(speed, 0);
 		Robot.driveTrain.rightMidDrive.configMotionAcceleration(acceleration, 0);
 		
 		Robot.driveTrain.rightMidDrive.set(ControlMode.MotionMagic, distance);
 		Robot.driveTrain.rightTopDrive.set(ControlMode.Follower,RobotMap.rightMidDriveID);
 		Robot.driveTrain.rightBottomDrive.set(ControlMode.Follower,RobotMap.rightMidDriveID);
 		
 		
 		Robot.driveTrain.leftMidDrive.configMotionCruiseVelocity(speed, 0);
 		Robot.driveTrain.leftMidDrive.configMotionAcceleration(acceleration, 0);
 		
 		Robot.driveTrain.leftMidDrive.set(ControlMode.MotionMagic, distance);
 		Robot.driveTrain.leftTopDrive.set(ControlMode.Follower,RobotMap.leftMidDriveID);
 		Robot.driveTrain.leftBottomDrive.set(ControlMode.Follower,RobotMap.leftMidDriveID);
 		
 		while(Robot.driveTrain.rightMidDrive.getSelectedSensorPosition(0) < distance &&
 			  Robot.driveTrain.leftMidDrive.getSelectedSensorPosition(0) < distance){
 			Robot.driveTrain.rightMidDrive.setSelectedSensorPosition( Robot.driveTrain.rightEnc.get(), 0, 0);
 			Robot.driveTrain.leftMidDrive.setSelectedSensorPosition( Robot.driveTrain.leftEnc.get(), 0, 0);
 			
 			
 			
 		}
 	    
 	}
	
 	
	public static void rotate(int angle, int speed, int acceleration){
	    resetSonsors();
		
		Robot.driveTrain.rightMidDrive.configSelectedFeedbackSensor(FeedbackDevice.SoftwareEmulatedSensor, 0, 0);
 		Robot.driveTrain.rightMidDrive.configMotionCruiseVelocity(-speed, 0);
 		Robot.driveTrain.rightMidDrive.configMotionAcceleration(-acceleration, 0);
 		
 		Robot.driveTrain.rightMidDrive.set(ControlMode.MotionMagic, angle);
 		Robot.driveTrain.rightTopDrive.set(ControlMode.Follower,RobotMap.rightMidDriveID);
 		Robot.driveTrain.rightBottomDrive.set(ControlMode.Follower,RobotMap.rightMidDriveID);
 		
 		Robot.driveTrain.gyro.getAngle();
 		Robot.driveTrain.leftMidDrive.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
 		Robot.driveTrain.leftMidDrive.configMotionCruiseVelocity(speed, 0);
 		Robot.driveTrain.leftMidDrive.configMotionAcceleration(acceleration, 0);
 		
 		Robot.driveTrain.leftMidDrive.set(ControlMode.MotionMagic, angle);
 		Robot.driveTrain.leftTopDrive.set(ControlMode.Follower,RobotMap.leftMidDriveID);
 		Robot.driveTrain.leftBottomDrive.set(ControlMode.Follower,RobotMap.leftMidDriveID);
 		
 		while(Math.abs(Robot.driveTrain.rightMidDrive.getSelectedSensorPosition(0)) <Math.abs(angle)){
 	 			
 	 		Robot.driveTrain.rightMidDrive.setSelectedSensorPosition((int) Robot.driveTrain.gyro.getAngle(), 0, 0);
 	 		Robot.driveTrain.leftMidDrive.setSelectedSensorPosition((int) Robot.driveTrain.gyro.getAngle(), 0, 0);
 	 		}
	}
	
	
	public static void moveToHeight(int position){
		
		Robot.lift.setPosition(position);
		while(Robot.lift.liftMaster.getSelectedSensorPosition(0) < Robot.lift.positionArray[position]){
			
		}
		Robot.cubeControl.cubeEjection();
	}
	
	public static void resetSonsors()
	{
		Robot.lift.liftMaster.setSelectedSensorPosition(0, 0, 0);
		Robot.driveTrain.rightMidDrive.setSelectedSensorPosition(0, 0, 0);
 		Robot.driveTrain.leftMidDrive.setSelectedSensorPosition(0, 0, 0);
 		Robot.lift.liftEnc.reset();
 		Robot.driveTrain.leftEnc.reset();
 		Robot.driveTrain.rightEnc.reset();
 		Robot.driveTrain.gyro.reset();
	
	}

	
	public static double sensorAverage (double newValue, String sensorName){
		int sensorIndex;
		int sampleCount = 0;
		switch(sensorName){
		case "gyro":sensorIndex = 1; sampleCount = 30;
		break;
		case "accelerometer": sensorIndex = 2; sampleCount = 150;
		break;
		case "leftEnc":sensorIndex = 3; sampleCount = 30;
		break;
		case "rightEnc":sensorIndex = 4; sampleCount = 30;
		break;
		case "liftEnc": sensorIndex = 5; sampleCount = 30;
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
	
	
	//methods specific to this year
	
	
	
	
	
	
	
	
	
}





