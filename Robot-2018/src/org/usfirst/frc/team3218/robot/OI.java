package org.usfirst.frc.team3218.robot;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;


import java.lang.invoke.SwitchPoint;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.


	
	
	
	 

	
	public static XboxController xbox = new XboxController(RobotMap.joyStickPort1);
	
	public static Button liftBottom = new JoystickButton(xbox,1);
	public static Button liftSwitch = new JoystickButton(xbox,1);
	public static Button liftScaleMid = new JoystickButton(xbox,1);
	public static Button liftScaleHigh = new JoystickButton(xbox,1);

	// Button button = new Joysti0ckButton(stick, buttonNumber);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());
	
	 public OI(){
		 

		
	 }
	 
	 
	 

	 public static double getXboxControllerLeftY(){
			
			return  -xbox.getY(Hand.kLeft);
	
	 }
	 public static double getXboxControllerLeftZ(){
			
		 return xbox.getX(Hand.kLeft);
	
	 }
	 
	 public static double getXboxControllerRightY(){
			
			return  -xbox.getY(Hand.kRight);
	
	 }
	 public static double getXboxControllerRightZ(){
			
		 return xbox.getX(Hand.kRight);
	
	 }
	 
	

}