package org.usfirst.frc.team3218.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	
	
	// Driver Station USB Ports
	public static int joyStickPort1 = 0;
	public static int guitarPort = 1;
	
	//CAN bus channels
	public static final int liftCIMID = 10;
	public static final int leftBackDriveID = 0;
	public static final int leftMidDriveID = 1;
	public static final int leftFrontDriveID = 2;
	public static final int rightBackDriveID = 3;
	public static final int rightMidriveID = 4;
	public static final int rightFrontDriveID = 5;
	
	//PWM Channels
	public static int leftDrive1Port = 0;
	public static int leftDrive2Port = 1;
	public static int leftDrive3Port = 2;
	public static int rightDrive1Port = 3;
	public static int rightDrive2Port = 4;
	public static int rightDrive3Port = 5;

	public static int leftCollection1Port = 7;
	public static int rightCollection1Port = 8;

	//Digital Channels
	public static int encoderLeftPortA = 2;
	public static int encoderLeftPortB = 3;
	public static int encoderRightPortA = 0;
	public static int encoderRightPortB = 1;

	

	public static int limitSwitchPortA = 7;

	//Analog Channels
	public static int sonarAPort = 0;
	public static int gyroPort = 1;
	
}
