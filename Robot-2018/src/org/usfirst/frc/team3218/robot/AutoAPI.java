package org.usfirst.frc.team3218.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team3218.robot.commands.CubeControl.CubeEjectionOn;
import org.usfirst.frc.team3218.robot.commands.DriveTrain.GearShiftLow;
import org.usfirst.frc.team3218.robot.commands.Vision.Pixy;
import org.usfirst.frc.team3218.robot.subsystems.Blob;
import org.usfirst.frc.team3218.robot.subsystems.CubeControl;
import org.usfirst.frc.team3218.robot.subsystems.DriveTrain;
import org.usfirst.frc.team3218.robot.subsystems.Lift;

import com.ctre.phoenix.motorcontrol.*;
public class AutoAPI {


 //distances in inches
	public final static int WALL_TO_SWITCH_CHANNEL = 67;
	 public final static int AUTOLINE = 120;
	 public final static int WALL_TO_SWITCH = 150;
	 public final static int WALL_TO_PLATFORM_CHANNEL = 210;
	 
	 public final static int MID_LINE = 240+5*12;
	 public final static int HORIZONTAL_FAR_SIDE = 162;
	 final static float TICKS_PER_INCH = 72; 
	
	 static double time ;
	 public static double cubeDistance;
	 public static double[] averages = new double[6];
	 static double[][] sensorValues = new double [6][200];
	 static double rateDelta;
	 static int autoPhase = 0;
	 static double time;
	 
	 
	static int position;
	 
	 public static  double ejectionTime;
	 public static Blob autoBlob;
	 public static boolean breakAuto = false;
 /**
 * @param distance in inches, positive forwards, negative, backwards
 * @param speed in ticks, 0<s<1
 */
 	public static void driveStraight(int distance, int speed, int acceleration){
 		resetDriveTrain();
		SmartDashboard.putString("autoState", "drive");
 		distance *= TICKS_PER_INCH;
 		autoPhase++;
<<<<<<< Updated upstream
 		time = 15 - Timer.getMatchTime();
=======
 		time = 15-Timer.getMatchTime();
>>>>>>> Stashed changes
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
 		

 		while( Math.abs(Robot.driveTrain.rightMidDrive.getSelectedSensorPosition(0)) < Math.abs(distance) &&
 			  Math.abs(Robot.driveTrain.leftMidDrive.getSelectedSensorPosition(0)) < Math.abs(distance) 
 			  && !breakAuto && Math.abs(15-Timer.getMatchTime())<15){
 			
 		
 			double z =  (-Robot.driveTrain.gyro.getAngle()/50)*Math.signum(speed);
 			double y = 5500/speed;
 			
<<<<<<< Updated upstream
 			if(Robot.objective.getSelected() != "Switch"){
 			Robot.driveTrain.automaticTransmission();
 			}
 				if(time < 15-Timer.getMatchTime()){
 				y=(15-Timer.getMatchTime()-time)*Math.signum(speed);
 			}
=======
 			
 	if(Robot.Gear.getSelected() && Robot.objective.getSelected()!= "Switch"){
 		Robot.driveTrain.automaticTransmission();
 	}
>>>>>>> Stashed changes
 			SmartDashboard.putNumber("timer", 15-Timer.getMatchTime());
 			Robot.driveTrain.rightMidDrive.setSelectedSensorPosition( Robot.driveTrain.rightEnc.get(), 0, 0);
 			Robot.driveTrain.leftMidDrive.setSelectedSensorPosition( Robot.driveTrain.leftEnc.get(), 0, 0);
 			if(time < 15 - Timer.getMatchTime()) y = 15 - Timer.getMatchTime() - time;
 				
 			
 			Robot.driveTrain.autoDrive(y, z);
 			
 		}
 		resetDriveTrain();
 		Robot.driveTrain.lowGear();
 	}
	public static void simpleDrive(int distance){
		resetDriveTrain();
		SmartDashboard.putString("autoState", "simple");
		autoPhase++;
		distance *= TICKS_PER_INCH;
		while((Math.abs(Robot.driveTrain.leftEnc.get()) + Math.abs(Robot.driveTrain.rightEnc.get()))/2 < Math.abs(distance)
				&& !breakAuto && Math.abs(15-Timer.getMatchTime())<15){
		
			Robot.driveTrain.drive(.5, 0);
		}
		Robot.driveTrain.drive(0, 0);
		
		
	}
 	
	public static void rotate(int angle, int speed, int acceleration){
	    resetDriveTrain();
		SmartDashboard.putString("autoState", "rotate");
		autoPhase++;
	    Robot.driveTrain.rightMidDrive.setSensorPhase(false);
		Robot.driveTrain.rightMidDrive.configSelectedFeedbackSensor(FeedbackDevice.Analog, 0, 0);
 		Robot.driveTrain.rightMidDrive.configMotionCruiseVelocity(speed, 0);
 		Robot.driveTrain.rightMidDrive.configMotionAcceleration(acceleration, 0);
 		
 		Robot.driveTrain.rightMidDrive.set(ControlMode.MotionMagic, angle);
 		Robot.driveTrain.rightTopDrive.set(ControlMode.Follower,RobotMap.rightMidDriveID);
 		Robot.driveTrain.rightBottomDrive.set(ControlMode.Follower,RobotMap.rightMidDriveID);
 		
 		
 		Robot.driveTrain.leftMidDrive.configSelectedFeedbackSensor(FeedbackDevice.Analog, 0, 0);
 		Robot.driveTrain.leftMidDrive.configMotionCruiseVelocity(speed, 0);
 		Robot.driveTrain.leftMidDrive.configMotionAcceleration(acceleration, 0);
 		
 		Robot.driveTrain.leftMidDrive.set(ControlMode.MotionMagic, angle);
 		Robot.driveTrain.leftTopDrive.set(ControlMode.Follower,RobotMap.leftMidDriveID);
 		Robot.driveTrain.leftBottomDrive.set(ControlMode.Follower,RobotMap.leftMidDriveID);
 		
 		while(Math.abs(Robot.driveTrain.leftMidDrive.getSelectedSensorPosition(0)) <Math.abs(angle)
 				&& !breakAuto && Math.abs(15-Timer.getMatchTime())<15){
 		
 			SmartDashboard.putNumber("timer", 15-Timer.getMatchTime());
 	 		Robot.driveTrain.rightMidDrive.setSelectedSensorPosition((int) Robot.driveTrain.gyro.getAngle(), 0, 0);
 	 		Robot.driveTrain.leftMidDrive.setSelectedSensorPosition((int) Robot.driveTrain.gyro.getAngle(), 0, 0);
 	 		}
 		/*
 		if(Math.abs(Robot.driveTrain.leftEnc.get()) > 300 && Math.abs(Robot.driveTrain.rightEnc.get()) > 300){
				breakAuto = true;
			}
 		*/
	}
	public static void turnCheck(int degree){
		double error = Math.abs(degree) - Math.abs(Robot.driveTrain.gyro.getAngle());
		if(error > 10 || error < -10){
		rotate(degree-(int) Robot.driveTrain.gyro.getAngle(), 1200, 1200);
		}
	}

	public static void moveToHeight(int position){
		SmartDashboard.putString("autoState", "lift");
		resetDriveTrain();
		autoPhase++;
	Robot.lift.gearHigh();
		Robot.lift.setPosition(Robot.lift.positionArray[position]);
		
		if(Robot.lift.positionArray[position] > Robot.lift.liftMaster.getSelectedSensorPosition(0)){
			
			while(Robot.lift.liftMaster.getSelectedSensorPosition(0) < Robot.lift.positionArray[position]-300  
				&& !breakAuto && Math.abs(Timer.getMatchTime())-15<15){
			
				SmartDashboard.putNumber("timer", 15-Timer.getMatchTime());
				Robot.lift.setPosition(Robot.lift.positionArray[position]);
				SmartDashboard.putNumber("Lift Encoder", Robot.lift.liftMaster.getSelectedSensorPosition(0));
				if(Robot.lift.bottomSwitch.get()){
					Robot.lift.liftEnc.reset();
				}
		}
	
		
		}else{
			while(Robot.lift.liftMaster.getSelectedSensorPosition(0) > Robot.lift.positionArray[position]
					&& !breakAuto && Math.abs(Timer.getMatchTime())-15<15){
				if(Robot.lift.bottomSwitch.get()){
					Robot.lift.liftEnc.reset();
				}
					SmartDashboard.putNumber("timer", 15-Timer.getMatchTime());
					Robot.lift.setPosition(Robot.lift.positionArray[position]);
					SmartDashboard.putNumber("Lift Encoder", Robot.lift.liftMaster.getSelectedSensorPosition(0));	
			
			}
		}
		
		Robot.lift.gearLow();
	}
	static public void autoTurnToTarget()
	{ 
		
	
			autoBlob = Pixy.blobArray[1];
			SmartDashboard.putString("autoState", "vision");
			autoBlob = Pixy.blobArray[1];
			Robot.vision.ratioToCenter = (autoBlob.averageX-160)/160;
			Robot.driveTrain.drive(1, Robot.vision.ratioToCenter*1.4);
				
		
		
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





