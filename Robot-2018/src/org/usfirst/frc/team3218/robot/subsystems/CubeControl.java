package org.usfirst.frc.team3218.robot.subsystems;

import org.usfirst.frc.team3218.robot.OI;
import org.usfirst.frc.team3218.robot.Robot;
import org.usfirst.frc.team3218.robot.RobotMap;

import org.usfirst.frc.team3218.robot.commands.CubeControl.CubeControlXbox;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 *
 */
public class CubeControl extends Subsystem {
public double collectionSpeed = 1;
public double ejectionSpeed = -1;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public static  WPI_TalonSRX leftWheels = new WPI_TalonSRX(RobotMap.leftCollectionID);
	public static WPI_TalonSRX rightWheels = new WPI_TalonSRX(RobotMap.rightCollectionID);
	public DifferentialDrive cubeCollect = new DifferentialDrive(leftWheels, rightWheels);
	public void initDefaultCommand() {
        // Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(new CubeControlXbox());

	}
	public void cubeEjection(){
		leftWheels.set(ejectionSpeed);
		rightWheels.set(ejectionSpeed);
	}
	public void cubeOff(){
		leftWheels.set(0);
		rightWheels.set(0);
	}
	public void cubeControlXbox(){
		if(Robot.oi.getXboxControllerRightY()>=0.7){
			
		}
		
		cubeCollect.arcadeDrive(Robot.oi.getXboxControllerRightY(),Robot.oi.getXboxControllerRightZ());
		
		
	}
}
