package org.usfirst.frc.team3218.robot;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;


import java.lang.invoke.SwitchPoint;
import java.util.function.DoubleToLongFunction;

import org.usfirst.frc.team3218.robot.commands.ExampleCommand;
import org.usfirst.frc.team3218.robot.commands.DriveTrain.GearShiftHigh;
import org.usfirst.frc.team3218.robot.commands.DriveTrain.GearShiftLow;
import org.usfirst.frc.team3218.robot.commands.ExampleCommand;
import org.usfirst.frc.team3218.robot.commands.CubeControl.CubeCollectionOn;
import org.usfirst.frc.team3218.robot.commands.CubeControl.CubeControlOff;
import org.usfirst.frc.team3218.robot.commands.CubeControl.CubeEjectionOn;
import org.usfirst.frc.team3218.robot.commands.Lift.DriverDown;
import org.usfirst.frc.team3218.robot.commands.Lift.LiftGearHigh;
import org.usfirst.frc.team3218.robot.commands.Lift.LiftGearLow;
import org.usfirst.frc.team3218.robot.commands.Lift.SetLiftPositionBottom;
import org.usfirst.frc.team3218.robot.commands.Lift.SetLiftPositionScaleHigh;
import org.usfirst.frc.team3218.robot.commands.Lift.SetLiftPositionScaleLow;
import org.usfirst.frc.team3218.robot.commands.Lift.SetLiftPositionScaleMid;
import org.usfirst.frc.team3218.robot.commands.Lift.SetLiftPositionSwitch;

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


	
	
	
	 

	public static Joystick guitar = new Joystick(RobotMap.guitarPort);
	public static XboxController xbox = new XboxController(RobotMap.xboxControllerPort);
	
	public static Button aButton= new JoystickButton(xbox,1);
	public static Button bButton = new JoystickButton(xbox, 2);
	public static Button xButton = new JoystickButton(xbox,3);
	public static Button yButton = new JoystickButton(xbox,4);
	public static Button leftBumper = new JoystickButton(xbox,5);
	public static Button rightBumper = new JoystickButton(xbox,6);
	public static Button backButton = new JoystickButton(xbox,7);
	public static Button startButton = new JoystickButton(xbox,8);
	public static Button leftJoyClick = new JoystickButton(xbox,9);
	public static Button rightJoyClick = new JoystickButton(xbox,10);
	
	
	public static Button liftBottom = new JoystickButton(guitar, 5);
	public static Button liftSwitch = new JoystickButton(guitar, 3);
	public static Button liftScaleLow = new JoystickButton(guitar, 4);
	public static Button liftScaleMid= new JoystickButton(guitar, 2);
	public static Button liftScaleHigh =  new JoystickButton(guitar, 1);
	
	 
	



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
		 

		leftBumper.whenPressed(new GearShiftLow());
		rightBumper.whenPressed(new GearShiftHigh());
		leftJoyClick.toggleWhenPressed(new LiftGearLow());
		rightJoyClick.toggleWhenPressed(new LiftGearHigh());
		startButton.whileHeld(new DriverDown());
		
		 liftBottom.whileHeld(new SetLiftPositionBottom());
		 liftSwitch.whileHeld(new SetLiftPositionSwitch());
	//	 liftScaleLow.whileHeld(new SetLiftPositionScaleLow());
		 liftScaleMid.whileHeld(new SetLiftPositionScaleMid());
		 liftScaleHigh.whileHeld(new SetLiftPositionScaleHigh());

	 }
	 
	 
	 
	 
	 public static double getXboxControllerLeftY(){
	
		 return -xbox.getY(Hand.kLeft);
	
	 }
	 public static double getXboxControllerLeftZ(){
			
		 return xbox.getX(Hand.kLeft);
	
	 }
	 public static double getXboxControllerRightY(){
			
		 return xbox.getY(Hand.kRight);
	
	 }
	 public static double getXboxControllerRightZ(){
			
		 return xbox.getX(Hand.kRight);
	
	 }

}