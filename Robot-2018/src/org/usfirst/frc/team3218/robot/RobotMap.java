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
	//pdp is 0, pcm is 1

	public static final int leftBottomDriveID = 2;
	public static final int leftMidDriveID = 3;
	public static final int leftTopDriveID = 4;
	public static final int rightBottomDriveID = 5;
	public static final int rightMidDriveID = 6;
	public static final int rightTopDriveID = 7;
	public static final int lift1ID= 8;
	public static final int lift2ID= 9;
	public static final int leftCollectionID = 10;
	public static final int rightCollectionID = 11;
	
	//PWM Channels

	//Digital Channels
	public static int encoderLeftPortA = 0;
	public static int encoderLeftPortB = 1;
	public static int encoderRightPortA = 2;
	public static int encoderRightPortB = 3;
	public static int liftEncoderPortA = 4;
	public static int liftEncoderPortB = 5;
	

	public static int limitSwitchPortA = 7;

	//Analog Channels
	public static int sonarAPort = 0;
	public static int gyroPort = 1;
	public static int accelerometerPort = 2;
	//PCM Channels

	public static int rightHighGearShiftPort = 0;
	public static int righLowGearShiftPort = 1;
	public static int leftHighGearShiftPort = 2;
	public static int leftLowGearShiftPort = 3;
	public static int leftKoreyPort = 4;
	public static int rightKoreyPort = 5;
	public static int pistonKoreyPort = 6;
	public static int climbGearPort = 7;
	
	
	
}
