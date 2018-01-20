package org.usfirst.frc.team3218.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;


import java.lang.invoke.SwitchPoint;

import org.usfirst.frc.team3218.robot.CubeControl.commands.CubeControlOff;
import org.usfirst.frc.team3218.robot.CubeControl.commands.CubeCollectionOn;
import org.usfirst.frc.team3218.robot.CubeControl.commands.CubeEjectionOn;
import org.usfirst.frc.team3218.robot.commands.ExampleCommand;
import org.usfirst.frc.team3218.robot.commands.DriveTrain.GyroAngles;
import org.usfirst.frc.team3218.robot.commands.DriveTrain.SonarTest;

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
	public static Joystick buttonPanel = new Joystick(RobotMap.buttonPanelPort);
	
	public static Button liftBottom = new JoystickButton(buttonPanel, 1);
	public static Button liftSwitch = new JoystickButton(buttonPanel, 2);
	public static Button liftScaleLow = new JoystickButton(buttonPanel, 3);
	public static Button liftScaleMid= new JoystickButton(buttonPanel, 4);
	public static Button liftScaleHigh =  new JoystickButton(buttonPanel, 5);
	public static Button button4 = new JoystickButton(joystick,4);
	public static Button button1 = new JoystickButton(joystick,1);
	public static Button button2 = new JoystickButton(joystick, 2);
	public static Button button3 = new JoystickButton(joystick,3);

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
		 
		 button2.toggleWhenPressed(new SonarTest());
		 button1.whileHeld(new CubeCollectionOn());
		 button3.whileHeld(new CubeEjectionOn());
		 button4.whileHeld(new GyroAngles());
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
	 public static void liftPositionSelector(){
		 if(liftBottom.get()){
			 Robot.lift.desiredPosition =1;
		 }else if(liftSwitch.get()){
			 
			 Robot.lift.desiredPosition =2;
		 }
		 else if(liftScaleLow.get()){
			 
			 Robot.lift.desiredPosition =3;
			 
		 }else if(liftScaleMid.get()){
			 
			 Robot.lift.desiredPosition =4;
			 
		 }else if(liftScaleHigh.get()){
			 
			 Robot.lift.desiredPosition =5;
		 
	 }
	 }
	

}