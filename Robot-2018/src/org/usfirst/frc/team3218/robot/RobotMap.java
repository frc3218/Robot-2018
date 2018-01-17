package org.usfirst.frc.team3218.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	
	// Driver Station USB Ports
	public static int JoyStickPort1 = 0;
	
	//CAN bus channels
	public static int liftCimPort = 1;
	
	//PWM Channels
	public static int leftDrive1Port = 0;
	public static int leftDrive2Port = 1;
	public static int leftDrive3Port = 2;
	public static int rightDrive1Port = 3;
	public static int rightDrive2Port = 4;
	public static int rightDrive3Port = 5;
<<<<<<< HEAD
	
=======
	public static int leftCollection1Port = 6;
	public static int rightCollection1Port = 7;
>>>>>>> 698779d75f703aadac20a84c2fe5fa1d5fe14628
	//Digital Channels
	public static int encoderLeftPortA = 2;
	public static int encoderLeftPortB = 3;
	public static int encoderRightPortA = 0;
	public static int encoderRightPortB = 1;
<<<<<<< HEAD
	
=======
	public static int limitSwitchPortA = 8;
>>>>>>> 698779d75f703aadac20a84c2fe5fa1d5fe14628
	//Analog Channels
	public static int sonarAPort = 0;
	public static int gyroPort = 1;
}
