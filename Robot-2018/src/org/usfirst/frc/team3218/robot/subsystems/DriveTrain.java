package org.usfirst.frc.team3218.robot.subsystems;

import java.security.PublicKey;

import org.usfirst.frc.team3218.robot.Robot;
import org.usfirst.frc.team3218.robot.RobotMap;
import org.usfirst.frc.team3218.robot.commands.DriveTrain.DriveWithJoystick;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveTrain extends Subsystem {

	TalonSRX leftDrive1 = new TalonSRX(RobotMap.leftDrive1Port);
	TalonSRX leftDrive2 = new TalonSRX(RobotMap.leftDrive2Port);
	TalonSRX leftDrive3 = new TalonSRX(RobotMap.leftDrive3Port);
	TalonSRX rightDrive1 = new TalonSRX(RobotMap.rightDrive1Port);
	TalonSRX rightDrive2 = new TalonSRX(RobotMap.rightDrive2Port);
	TalonSRX rightDrive3 = new TalonSRX(RobotMap.rightDrive3Port);
	
	RobotDrive robotDrive =  new RobotDrive(leftDrive1, leftDrive2, rightDrive1, rightDrive2); 
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new DriveWithJoystick());
    }
    
    public void drive(double y, double z){
    	robotDrive.arcadeDrive(y, z);
    }
    
}

