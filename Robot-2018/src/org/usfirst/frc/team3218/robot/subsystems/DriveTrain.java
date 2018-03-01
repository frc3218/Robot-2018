package org.usfirst.frc.team3218.robot.subsystems;

import java.util.EventListenerProxy;

import org.usfirst.frc.team3218.robot.OI;
import org.usfirst.frc.team3218.robot.Robot;
import org.usfirst.frc.team3218.robot.RobotMap;
import org.usfirst.frc.team3218.robot.commands.DriveTrain.DriveWithXbox;
import org.usfirst.frc.team3218.robot.commands.DriveTrain.GearShiftHigh;
import org.usfirst.frc.team3218.robot.commands.DriveTrain.GearShiftLow;

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
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveTrain extends Subsystem {

	
	private final static int SHIFT_DOWN_SPEED = 3500;
	private final static int SHIFT_UP_SPEED= 5000;
	
	public WPI_TalonSRX leftBottomDrive = new WPI_TalonSRX(RobotMap.leftBottomDriveID);
	public WPI_TalonSRX leftMidDrive = new WPI_TalonSRX(RobotMap.leftMidDriveID);
	public WPI_TalonSRX leftTopDrive = new WPI_TalonSRX(RobotMap.leftTopDriveID);
	public WPI_TalonSRX rightBottomDrive = new WPI_TalonSRX(RobotMap.rightBottomDriveID);
	public WPI_TalonSRX rightMidDrive = new WPI_TalonSRX(RobotMap.rightMidDriveID);
	public WPI_TalonSRX rightTopDrive = new WPI_TalonSRX(RobotMap.rightTopDriveID);
	
	public AnalogAccelerometer accelerometer = new AnalogAccelerometer(RobotMap.accelerometerPort);
	//public AnalogInput sonarA = new AnalogInput(RobotMap.sonarAPort);
	//public AnalogInput sonarB = new AnalogInput(RobotMap.sonarBPort);
	public static Compressor compressor = new Compressor(1);
	public static AnalogGyro gyro = new AnalogGyro(RobotMap.gyroPort);

	public static Solenoid leftHighGearShift = new Solenoid(1, RobotMap.leftHighGearShiftPort);
	public static Solenoid leftLowGearShift = new Solenoid(1, RobotMap.leftLowGearShiftPort);
	public static Solenoid rightHighGearShift = new Solenoid(1,RobotMap.rightHighGearShiftPort);
	public static Solenoid rightLowGearShift = new Solenoid(1, RobotMap.rightLowGearShiftPort);
	
	public static  Encoder leftEnc = new Encoder(RobotMap.leftEncoderPortA, RobotMap.leftEncoderPortB);
	public static  Encoder rightEnc = new Encoder(RobotMap.rightEncoderPortA, RobotMap.rightEncoderPortB);
	
	  static boolean toggleShifting = false;

	// Grouping Together Drives
	
	
	SpeedControllerGroup leftDrive = new SpeedControllerGroup(leftBottomDrive, leftMidDrive, leftTopDrive);
	SpeedControllerGroup rightDrive = new SpeedControllerGroup(rightBottomDrive, rightMidDrive, rightTopDrive);
	
	
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
		rightMidDrive.setSafetyEnabled(false);
		leftMidDrive.setSafetyEnabled(false);
		rightBottomDrive.setSafetyEnabled(false);
		leftBottomDrive.setSafetyEnabled(false);
		rightTopDrive.setSafetyEnabled(false);
		leftTopDrive.setSafetyEnabled(false);
		drive.setSafetyEnabled(false);
		rightMidDrive.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
		leftMidDrive.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0,0);
		leftMidDrive.setSelectedSensorPosition(0, 0, 0);
		rightMidDrive.setSelectedSensorPosition(0, 0, 0);
		
		leftMidDrive.selectProfileSlot(0, 0);
		leftMidDrive.config_kF(0, 6, 0);
		leftMidDrive.config_kP(0, 0, 0);
		leftMidDrive.config_kI(0, 0, 0);
		leftMidDrive.config_kD(0, 0, 0);
    	
    	rightMidDrive.selectProfileSlot(0, 0);
    	rightMidDrive.config_kF(0, 6, 0);
    	rightMidDrive.config_kP(0, 0, 0);
    	rightMidDrive.config_kI(0, 0, 0);
    	rightMidDrive.config_kD(0, 0, 0);
		rightEnc.reset();
		leftEnc.reset();
		rightEnc.setReverseDirection(true);
		leftEnc.setReverseDirection(true);
	}
	public void drive(double y, double z) {
	
    	drive.arcadeDrive(y, z*.95);
    	
    }
	public void autoDrive(double y, double z) {
		
    	drive.arcadeDrive(y, z, false);
    	
    }
    public static void lowGear(){
    	leftHighGearShift.set(true);
    	leftLowGearShift.set(false);
    	rightHighGearShift.set(false);
    	rightLowGearShift.set(true);
    
    }
    
    public static void highGear(){
    	leftHighGearShift.set(false);
    	leftLowGearShift.set(true);
    	rightHighGearShift.set(true);
    	rightLowGearShift.set(false);
    }
   public static void automaticTransmission(){
	  if(Math.abs((leftEnc.getRate()+rightEnc.getRate())/2) > SHIFT_UP_SPEED){
		   highGear();
	   }
	   else if(Math.abs((leftEnc.getRate()+rightEnc.getRate())/2) < SHIFT_DOWN_SPEED){
		   lowGear();
	   }
	  
   }
   	
   public void driveWithXbox(double y, double z) {
	   /*
	   switch((int)y*10){
	   case 4: z *= 0.6;
	   break;
	   case 5: z *= 0.5;
	   break;
	   case 6: z *= 0.4;
	   break;
	   case 7: z *= 0.3;
	   break;
	   case 8: z *= 0.2;
	   break;
	   case 10:z *= 0.1;
	   break;
	   }
	   */
	   if(Math.abs(y) < 0.2)
			   y = 0;
	   
	   if(Math.abs(y) == y){
		   Robot.driveTrain.drive(y,z);
	   }
	   else{
	   Robot.driveTrain.drive(y,-z);
	   }
	   
	   
   }
   public void compressorControl(){
	   if(compressor.getPressureSwitchValue()){
			compressor.stop();
		}
		else{
			compressor.start();
		}
   }
}

