package org.usfirst.frc.team3218.robot.subsystems;

import org.usfirst.frc.team3218.robot.RobotMap;
import org.usfirst.frc.team3218.robot.commands.DriveTrain.DriveWithJoystick;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.AnalogAccelerometer;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 *
 */
public class DriveTrain extends Subsystem {
	public static double[] gyroAr = new double[15]; 
	public static double sum;

	double power = .5;
	static int timesRolled = 50;
	public static int gyroTimes = 50;
	public static double pastValue;
	public static double newValue;
	public static int times;
	
	final int SHIFT_UP_SPEED = 0;
	final int SHIFT_DOWN_SPEED = 0;
	
	SpeedController leftDrive1 = new Talon(RobotMap.leftDrive1Port);
	SpeedController leftDrive2 = new Talon(RobotMap.leftDrive2Port);
	SpeedController leftDrive3 = new Spark(RobotMap.leftDrive3Port);
	SpeedController rightDrive1 = new Talon(RobotMap.rightDrive1Port);
	SpeedController rightDrive2 = new Talon(RobotMap.rightDrive2Port);
	SpeedController rightDrive3 = new Spark(RobotMap.rightDrive3Port);
	
	public WPI_TalonSRX leftBottomDrive = new WPI_TalonSRX(RobotMap.leftBottomDriveID);
	public WPI_TalonSRX leftMidDrive = new WPI_TalonSRX(RobotMap.leftMidDriveID);
	public WPI_TalonSRX leftTopDrive = new WPI_TalonSRX(RobotMap.leftTopDriveID);
	public WPI_TalonSRX rightBottomDrive = new WPI_TalonSRX(RobotMap.rightBottomDriveID);
	public WPI_TalonSRX rightMidDrive = new WPI_TalonSRX(RobotMap.rightMidDriveID);
	public WPI_TalonSRX rightTopDrive = new WPI_TalonSRX(RobotMap.rightTopDriveID);
	
	public AnalogAccelerometer accelerometer = new AnalogAccelerometer(RobotMap.accelerometerPort);
	public AnalogInput sonarA = new AnalogInput(RobotMap.sonarAPort);

	public static Encoder leftEnc = new Encoder(RobotMap.encoderLeftPortA, RobotMap.encoderLeftPortB, true);
	public static Encoder rightEnc = new Encoder(RobotMap.encoderRightPortA, RobotMap.encoderRightPortB, false);

	public static AnalogGyro gyro = new AnalogGyro(RobotMap.gyroPort);
	
	public static Solenoid leftGearShift = new Solenoid(RobotMap.leftGearShiftPort);
	public static Solenoid rightGearShift = new Solenoid(RobotMap.rightGearShiftPort);
	
	
	
	
	
	
	
	// Grouping Together Drives
	SpeedControllerGroup leftDrive = new SpeedControllerGroup(leftDrive1, leftDrive2, leftDrive3);
	SpeedControllerGroup rightDrive = new SpeedControllerGroup(rightDrive1, rightDrive2, rightDrive3);
	DifferentialDrive drive = new DifferentialDrive(leftDrive, rightDrive);

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(new DriveWithJoystick());
	}

	public void drive(double y, double z) {
		// Inverting right drive
		//rightDrive1.setInverted(true);
		//rightDrive2.setInverted(true);
		//rightDrive3.setInverted(true);
		if(leftEnc.getRate() >= SHIFT_UP_SPEED && rightEnc.getRate() >= SHIFT_UP_SPEED){
			highGear();
		}
		else if(leftEnc.getRate() < SHIFT_DOWN_SPEED && rightEnc.getRate() < SHIFT_DOWN_SPEED){
			lowGear();
		}
		
    	drive.arcadeDrive(y, z);
		
    }
    
    public void lowGear(){
    	leftGearShift.set(false);
    	rightGearShift.set(false);
    }
    
    public void highGear(){
    	leftGearShift.set(true);
    	rightGearShift.set(true);

    }
}

