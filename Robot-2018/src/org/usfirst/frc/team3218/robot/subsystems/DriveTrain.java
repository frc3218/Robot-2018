package org.usfirst.frc.team3218.robot.subsystems;

import org.usfirst.frc.team3218.robot.RobotMap;
import org.usfirst.frc.team3218.robot.commands.DriveTrain.DriveWithJoystick;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.AnalogAccelerometer;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 *
 */
public class DriveTrain extends Subsystem {
	
	public SpeedController leftTopDrive = new WPI_TalonSRX(RobotMap.leftTopDriveID);
	public SpeedController leftBottomDrive = new WPI_TalonSRX(RobotMap.leftBottomDriveID);
	public SpeedController rightTopDrive = new WPI_TalonSRX(RobotMap.rightTopDriveID);
	public SpeedController rightBottomDrive = new WPI_TalonSRX(RobotMap.rightBottomDriveID);
	//Grouping Together Drives
	SpeedControllerGroup leftDrive = new SpeedControllerGroup(leftBottomDrive, leftTopDrive);
	SpeedControllerGroup rightDrive = new SpeedControllerGroup(rightBottomDrive, rightTopDrive);
	DifferentialDrive drive = new DifferentialDrive(leftDrive, rightDrive);
	
	//Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(new DriveWithJoystick());
	}

	public void drive(double y, double z) {
	
    	drive.arcadeDrive(y, z);
		
    }
    
   
}

