package org.usfirst.frc.team3218.robot.subsystems;

import java.util.EventListenerProxy;

import org.usfirst.frc.team3218.robot.Robot;
import org.usfirst.frc.team3218.robot.RobotMap;
import org.usfirst.frc.team3218.robot.commands.DriveTrain.DriveWithXbox;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.IFollower;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.AnalogAccelerometer;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveTrain extends Subsystem {

	
	private final int SHIFT_DOWN_SPEED = 3500;
	private final int SHIFT_UP_SPEED= 5000;
	
	public WPI_TalonSRX leftBottomDrive = new WPI_TalonSRX(RobotMap.leftBottomDriveID);
	public WPI_TalonSRX leftMidDrive = new WPI_TalonSRX(RobotMap.leftMidDriveID);
	public WPI_TalonSRX leftTopDrive = new WPI_TalonSRX(RobotMap.leftTopDriveID);
	public WPI_TalonSRX rightBottomDrive = new WPI_TalonSRX(RobotMap.rightBottomDriveID);
	public WPI_TalonSRX rightMidDrive = new WPI_TalonSRX(RobotMap.rightMidDriveID);
	public WPI_TalonSRX rightTopDrive = new WPI_TalonSRX(RobotMap.rightTopDriveID);
	
	public AnalogAccelerometer accelerometer = new AnalogAccelerometer(RobotMap.accelerometerPort);
	//public AnalogInput sonarA = new AnalogInput(RobotMap.sonarAPort);
	//public AnalogInput sonarB = new AnalogInput(RobotMap.sonarBPort);
	public AnalogGyro gyro = new AnalogGyro(RobotMap.gyroPort);

	public Solenoid leftHighGearShift = new Solenoid(1, RobotMap.leftHighGearShiftPort);
	public Solenoid leftLowGearShift = new Solenoid(1, RobotMap.leftLowGearShiftPort);
	public Solenoid rightHighGearShift = new Solenoid(1,RobotMap.rightHighGearShiftPort);
	public Solenoid rightLowGearShift = new Solenoid(1, RobotMap.rightLowGearShiftPort);
	
	public Encoder leftEnc = new Encoder(RobotMap.leftEncoderPortA, RobotMap.leftEncoderPortB);
	public Encoder rightEnc = new Encoder(RobotMap.rightEncoderPortA, RobotMap.rightEncoderPortB);
	// Grouping Together Drives
	
	public SpeedControllerGroup leftDrive = new SpeedControllerGroup(leftBottomDrive, leftMidDrive, leftTopDrive);
	public 	SpeedControllerGroup rightDrive = new SpeedControllerGroup(rightBottomDrive, rightMidDrive, rightTopDrive);
	
	
	DifferentialDrive drive = new DifferentialDrive(leftDrive, rightDrive);
	


	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(new DriveWithXbox());
	}
	public void drivePIDConfig(){	
		lowGear();
		drive.setSafetyEnabled(false);
		rightMidDrive.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
		leftMidDrive.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0,0);
		leftMidDrive.setSelectedSensorPosition(0, 0, 0);
		rightMidDrive.setSelectedSensorPosition(0, 0, 0);
		
		leftMidDrive.selectProfileSlot(0, 0);
		leftMidDrive.config_kF(0, 7, 0);
		leftMidDrive.config_kP(0, 0, 0);
		leftMidDrive.config_kI(0, 0, 0);
		leftMidDrive.config_kD(0, 0, 0);
    	
    	rightMidDrive.selectProfileSlot(0, 0);
    	rightMidDrive.config_kF(0, 7, 0);
    	rightMidDrive.config_kP(0, 0, 0);
    	rightMidDrive.config_kI(0, 0, 0);
    	rightMidDrive.config_kD(0, 0, 0);
		rightEnc.reset();
		leftEnc.reset();
		
	}
	
	public void drive(double y, double z) {
		
    	drive.arcadeDrive(y, z);
		
		
    }
	/*public void autoDrive(double left, double right) {
			SmartDashboard.putNumber("auto drive left" , left);
			SmartDashboard.putNumber("auto drive right" , right);
	    	drive.tankDrive(left, right,false);
	    	
    }
    */
	
	public void autoDrive(double y, double z) {
		//	if(Robot.pdp.getVoltage()>8){
				
		    	drive.arcadeDrive(y, z,false);
		    	
			//	}
			//	else{
		//			
			//		drive.arcadeDrive(y/2, z/2,false);
			//	}
	}
	
	public void driveWithXbox(double y,double z){
		if(Math.abs(y) < .2)  y=0;
		
		if(Math.abs(z)<.2) z = 0;
		if(y>-0.45){
			Robot.driveTrain.drive(y,z);
		}else{
			Robot.driveTrain.drive(y,-z);
		}
		
	}
    public void lowGear(){
    	leftHighGearShift.set(true);
    	leftLowGearShift.set(false);
    	rightHighGearShift.set(false);
    	rightLowGearShift.set(true);
    }
    
    public void highGear(){
    	leftHighGearShift.set(false);
    	leftLowGearShift.set(true);
    	rightHighGearShift.set(true);
    	rightLowGearShift.set(false);
    
    }
   public void automaticTransmission(){
	   if(Math.abs((leftEnc.getRate()+rightEnc.getRate())/2) > SHIFT_UP_SPEED){
		   highGear();
	   }
	   else if(Math.abs((leftEnc.getRate()+rightEnc.getRate())/2) < SHIFT_DOWN_SPEED){
		   lowGear();
	   }
	  
   }

}

