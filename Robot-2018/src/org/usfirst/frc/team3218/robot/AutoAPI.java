package org.usfirst.frc.team3218.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;

import edu.wpi.first.wpilibj.Talon;

import org.usfirst.frc.team3218.robot.commands.CubeControl.CubeEjectionOn;
import org.usfirst.frc.team3218.robot.subsystems.CubeControl;
import org.usfirst.frc.team3218.robot.subsystems.DriveTrain;
import org.usfirst.frc.team3218.robot.subsystems.Lift;

import com.ctre.phoenix.motorcontrol.*;
public class AutoAPI {


 //distances in inches
 public final static int WALL_TO_SWITCH_CHANNEL = 67;
 public final static int AUTOLINE = 84;
 public final static int WALL_TO_SWITCH = 134;
 public final static int WALL_TO_PLATFORM_CHANNEL = 210;
 
 public final static int MID_LINE = 240;
 public final static int HORIZONTAL_FAR_SIDE = 150;
 final static float TICKS_PER_INCH = 72; 
 
 public static double[] averages = new double[6];
 static double[][] sensorValues = new double [6][200];
 static double rateDelta;
 
	
 /**
 * @param distance in inches, positive forwards, negative, backwards
 * @param speed in ticks, 0<s<1
 */
 	public static void driveStraight(int distance, int speed, int acceleration){
 		resetDriveTrain();
 		distance *= TICKS_PER_INCH;
 		//speed *= Math.signum(distance);// may not be needed
 		Robot.driveTrain.rightMidDrive.setSensorPhase(true);
 		Robot.driveTrain.rightMidDrive.configMotionCruiseVelocity(speed, 0);
 		Robot.driveTrain.rightMidDrive.configMotionAcceleration(acceleration, 0);
 		
 		Robot.driveTrain.rightMidDrive.set(ControlMode.MotionMagic, -distance);
 		Robot.driveTrain.rightTopDrive.set(ControlMode.Follower,RobotMap.rightMidDriveID);
 		Robot.driveTrain.rightBottomDrive.set(ControlMode.Follower,RobotMap.rightMidDriveID);
 		
 		
 		Robot.driveTrain.leftMidDrive.configMotionCruiseVelocity(speed, 0);
 		Robot.driveTrain.leftMidDrive.configMotionAcceleration(acceleration, 0);
 		
 		Robot.driveTrain.leftMidDrive.set(ControlMode.MotionMagic, distance);
 		Robot.driveTrain.leftTopDrive.set(ControlMode.Follower,RobotMap.leftMidDriveID);
 		Robot.driveTrain.leftBottomDrive.set(ControlMode.Follower,RobotMap.leftMidDriveID);
 		
 		while( Math.abs(Robot.driveTrain.rightMidDrive.getSelectedSensorPosition(0)) < distance &&
 			  Math.abs(Robot.driveTrain.leftMidDrive.getSelectedSensorPosition(0)) < distance){
 			
 			
 			rateDelta = Math.abs(Robot.driveTrain.rightEnc.getRate()) / (Robot.driveTrain.leftEnc.getRate());
 		
 			//Robot.driveTrain.rightMidDrive.configMotionAcceleration((int) (acceleration / rateDelta), 0);
 			
 			Robot.driveTrain.rightMidDrive.setSelectedSensorPosition( Robot.driveTrain.rightEnc.get(), 0, 0);
 			Robot.driveTrain.leftMidDrive.setSelectedSensorPosition( Robot.driveTrain.leftEnc.get(), 0, 0);
 			
 			
 			
 		}
 	    
 	}
 	
	public static void simpleDrive(double distance){
		distance *= TICKS_PER_INCH;
		resetDriveTrain();
		
		while((Math.abs(Robot.driveTrain.leftEnc.get())+Math.abs(Robot.driveTrain.rightEnc.get()))/2 < distance){
			
			Robot.driveTrain.drive(.3, 0);		
			
		}

	}
 	
	public static void rotate(int angle, int speed, int acceleration){
	    resetDriveTrain();
		
	    Robot.driveTrain.rightMidDrive.setSensorPhase(false);
		Robot.driveTrain.rightMidDrive.configSelectedFeedbackSensor(FeedbackDevice.Analog, 0, 0);
 		Robot.driveTrain.rightMidDrive.configMotionCruiseVelocity(speed, 0);
 		Robot.driveTrain.rightMidDrive.configMotionAcceleration(acceleration, 0);
 		
 		Robot.driveTrain.rightMidDrive.set(ControlMode.MotionMagic, angle/*+5*Math.signum(angle)*/);
 		Robot.driveTrain.rightTopDrive.set(ControlMode.Follower,RobotMap.rightMidDriveID);
 		Robot.driveTrain.rightBottomDrive.set(ControlMode.Follower,RobotMap.rightMidDriveID);
 		
 		
 		Robot.driveTrain.leftMidDrive.configSelectedFeedbackSensor(FeedbackDevice.Analog, 0, 0);
 		Robot.driveTrain.leftMidDrive.configMotionCruiseVelocity(speed, 0);
 		Robot.driveTrain.leftMidDrive.configMotionAcceleration(acceleration, 0);
 		
 		Robot.driveTrain.leftMidDrive.set(ControlMode.MotionMagic, angle/*+5*Math.signum(angle)*/);
 		Robot.driveTrain.leftTopDrive.set(ControlMode.Follower,RobotMap.leftMidDriveID);
 		Robot.driveTrain.leftBottomDrive.set(ControlMode.Follower,RobotMap.leftMidDriveID);
 		
 		while(Math.abs(Robot.driveTrain.leftMidDrive.getSelectedSensorPosition(0)) <Math.abs(angle)){
 	 		
 	 		Robot.driveTrain.rightMidDrive.setSelectedSensorPosition((int) Robot.driveTrain.gyro.getAngle(), 0, 0);
 	 		Robot.driveTrain.leftMidDrive.setSelectedSensorPosition((int) Robot.driveTrain.gyro.getAngle(), 0, 0);
 	 		}
	}
	
	
	
	public static void moveToHeight(int position){
		resetDriveTrain();
		Robot.lift.gearHigh();
		Robot.lift.setPosition(Robot.lift.positionArray[position]);
		Robot.lift.liftMaster.setSelectedSensorPosition(Robot.lift.liftEnc.get(),0,0);
		while(Robot.lift.liftMaster.getSelectedSensorPosition(0) < Robot.lift.positionArray[position]-500){
			Robot.lift.setPosition(Robot.lift.positionArray[position]);
		}
		Robot.lift.gearLow();
	}
	
	public static void resetDriveTrain()
	{		
		Robot.driveTrain.rightMidDrive.setSelectedSensorPosition(0, 0, 0);
 		Robot.driveTrain.leftMidDrive.setSelectedSensorPosition(0, 0, 0);
 		Robot.driveTrain.leftBottomDrive.set(0);
 		Robot.driveTrain.leftMidDrive.set(0);
 		Robot.driveTrain.leftMidDrive.set(0);
 		Robot.driveTrain.rightBottomDrive.set(0);
 		Robot.driveTrain.rightMidDrive.set(0);
 		Robot.driveTrain.rightMidDrive.set(0);
 		Robot.driveTrain.leftEnc.reset();
 		Robot.driveTrain.rightEnc.reset();
 		Robot.driveTrain.gyro.reset();
	
	}
	
	
	//methods specific to this year
	
	
	
	
	
	
	
	
	
}





