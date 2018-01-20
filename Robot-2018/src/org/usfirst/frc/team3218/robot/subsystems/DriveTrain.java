package org.usfirst.frc.team3218.robot.subsystems;

import org.usfirst.frc.team3218.robot.Robot;
import org.usfirst.frc.team3218.robot.RobotMap;
import org.usfirst.frc.team3218.robot.commands.DriveTrain.DriveWithJoystick;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PWMTalonSRX;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.interfaces.Gyro;

/**
 *
 */
public class DriveTrain extends Subsystem {
	public static double[] pyroAr = new double[15]; 
	public static double sum;
	double power = .5;
	public static double pastValue;
	public static double newValue;
	public static int times;
	SpeedController leftDrive1 = new Talon(RobotMap.leftDrive1Port);
	SpeedController leftDrive2 = new Talon(RobotMap.leftDrive2Port);
	SpeedController leftDrive3 = new Spark(RobotMap.leftDrive3Port);
	SpeedController rightDrive1 = new Talon(RobotMap.rightDrive1Port);
	SpeedController rightDrive2 = new Talon(RobotMap.rightDrive2Port);
	SpeedController rightDrive3 = new Spark(RobotMap.rightDrive3Port);
	
	public WPI_TalonSRX leftBackDrive = new WPI_TalonSRX(RobotMap.leftDrive1Port);
	public WPI_TalonSRX leftMidDrive = new WPI_TalonSRX(RobotMap.leftDrive2Port);
	public WPI_TalonSRX leftFrontDrive = new WPI_TalonSRX(RobotMap.leftDrive3Port);
	public WPI_TalonSRX rightBackDrive = new WPI_TalonSRX(RobotMap.rightDrive1Port);
	public WPI_TalonSRX rightMidDrive = new WPI_TalonSRX(RobotMap.rightDrive2Port);
	public WPI_TalonSRX rightFrontDrive = new WPI_TalonSRX(RobotMap.rightDrive3Port);
	
	public AnalogInput sonarA = new AnalogInput(RobotMap.sonarAPort);

	public static Encoder leftEnc = new Encoder(RobotMap.encoderLeftPortA, RobotMap.encoderLeftPortB, true);
	public static Encoder rightEnc = new Encoder(RobotMap.encoderRightPortA, RobotMap.encoderRightPortB, false);

	public static AnalogGyro gyro = new AnalogGyro(RobotMap.gyroPort);
	
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
		rightDrive1.setInverted(true);
		rightDrive2.setInverted(true);
		rightDrive3.setInverted(true);

		// sets a max drive power
		if (y >= power) {

			y = power;
		} else if (y <= -power) {
			y = -power;
		}

		if (z >= power) {

			z = power;
		} else if (z <= -power) {
			z = -power;
		}
    	drive.arcadeDrive(y, z);
		
    }
    public static double rollingAverage(double num){
    sum = 0;
    pyroAr[14] = num;
    for(int x = 0; x<pyroAr.length;x++){
    	sum += pyroAr[x];
    }
    for(int i=0; i<=13; i++){
    	pyroAr[i] = pyroAr[i + 1];
    	
    		
    
    }
    return (sum/pyroAr.length);
    }
}

