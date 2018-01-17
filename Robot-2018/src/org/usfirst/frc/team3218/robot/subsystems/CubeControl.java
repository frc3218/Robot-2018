package org.usfirst.frc.team3218.robot.subsystems;

import org.usfirst.frc.team3218.robot.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 *
 */
public class CubeControl extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public static SpeedController leftWheel = new Talon(RobotMap.leftCollection1Port);
	public static SpeedController rightWheel = new Talon(RobotMap.rightCollection1Port);
	
	
	public static DigitalInput limitSwitch = new DigitalInput(RobotMap.limitSwitchPortA);
    
	public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
		
    }
}

