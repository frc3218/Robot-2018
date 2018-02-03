package org.usfirst.frc.team3218.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;


import java.lang.invoke.SwitchPoint;

import org.usfirst.frc.team3218.robot.commands.ExampleCommand;
import org.usfirst.frc.team3218.robot.commands.DriveTrain.GearShiftHigh;
import org.usfirst.frc.team3218.robot.commands.DriveTrain.GearShiftLow;
import org.usfirst.frc.team3218.robot.commands.ExampleCommand;
import org.usfirst.frc.team3218.robot.commands.CubeControl.CubeCollectionOn;
import org.usfirst.frc.team3218.robot.commands.CubeControl.CubeControlOff;
import org.usfirst.frc.team3218.robot.commands.CubeControl.CubeEjectionOn;
import org.usfirst.frc.team3218.robot.commands.DriveTrain.SonarTest;
import org.usfirst.frc.team3218.robot.commands.Lift.ClimbingGearOff;
import org.usfirst.frc.team3218.robot.commands.Lift.ClimbingGearOn;
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


	
	
	
	 

	public static Joystick joystick = new Joystick(RobotMap.joyStickPort1);
	public static Joystick guitar = new Joystick(RobotMap.guitarPort);
	
	public static Button button1 = new JoystickButton(joystick,1);
	public static Button button2 = new JoystickButton(joystick, 2);
	public static Button button3 = new JoystickButton(joystick,3);
	public static Button button4 = new JoystickButton(joystick,4);
	public static Button button5 = new JoystickButton(joystick,5);
	public static Button button6 = new JoystickButton(joystick,6);
	public static Button button7 = new JoystickButton(joystick,7);
	public static Button button8 = new JoystickButton(joystick,8);
	public static Button button9 = new JoystickButton(joystick,9);
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
		 

		 button1.whenPressed(new SonarTest());
		 button3.whileHeld(new CubeCollectionOn());
		 button4.whileHeld(new CubeEjectionOn());

		 button5.whenPressed(new GearShiftLow());
		 button6.whenPressed(new GearShiftHigh());
		 button8.whenPressed(new ClimbingGearOn());
		 button9.whenPressed(new ClimbingGearOff());
		 
		 liftBottom.whenPressed(new SetLiftPositionBottom());
		 liftSwitch.whenPressed(new SetLiftPositionSwitch());
		 liftScaleLow.whenPressed(new SetLiftPositionScaleLow());
		 liftScaleMid.whenPressed(new SetLiftPositionScaleMid());
		 liftScaleHigh.whenPressed(new SetLiftPositionScaleHigh());

	 }
	 
	 
	 
	 
	 public static double getJoystickX(){
	
		 return -joystick.getX();
	
	 }
	 public static double getJoystickY(){
			
			return  -joystick.getY();
	
	 }
	 public static double getJoystickZ(){
			
		 return joystick.getZ();
	
	 }
	 
	

}