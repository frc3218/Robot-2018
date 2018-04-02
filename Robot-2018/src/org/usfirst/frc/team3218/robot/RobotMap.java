package org.usfirst.frc.team3218.robot;

import java.lang.Thread.State;

import edu.wpi.first.wpilibj.Timer.StaticInterface;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	
	
	// Driver Station USB Ports
	public static int guitarPort = 1;
	public static int xboxControllerPort = 3;
	
	public static int cameraPort = 0;
	public static int cameraPort2 = 1;
	//CAN bus channels
	//pdp is 0, pcm is 1
	public static final int leftBottomDriveID = 8;
	public static final int leftMidDriveID = 7;
	public static final int leftTopDriveID = 11;
	public static final int rightBottomDriveID = 2;
	public static final int rightMidDriveID = 5;
	public static final int rightTopDriveID = 3;
	public static final int lift1ID= 4;
	public static final int lift2ID= 6;
	public static final int leftCollectionID = 10;
	public static final int rightCollectionID = 9;
	public static int beltID =12;
	
	//PWM Channels

	//Digital Channels

	public static int bottomLiftSwitchPort = 8;
	public static int topLiftSwitchPort = 9;
	public static int liftEncoderPort1 = 0;
	public static int liftEncoderPort2 = 1;
	public static int leftEncoderPortA = 6;
	public static int leftEncoderPortB = 7;
	public static int rightEncoderPortA = 4;
	public static int rightEncoderPortB = 5;
	
	//Analog Channels
	public static int gyroPort = 0;
	public static int accelerometerPort = 1;
	public static int sonarAPort = 2;
	public static int sonarBPort = 3;
	
	//PCM Channels
	public static int rightHighGearShiftPort = 0;
	public static int rightLowGearShiftPort = 1;
	public static int leftHighGearShiftPort = 2;
	public static int leftLowGearShiftPort = 3;
	public static int climbGearPort = 4;
	

	
}
