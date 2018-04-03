package org.usfirst.frc.team3218.robot.subsystems;

import org.usfirst.frc.team3218.robot.OI;
import org.usfirst.frc.team3218.robot.Robot;
import org.usfirst.frc.team3218.robot.RobotMap;
import org.usfirst.frc.team3218.robot.commands.CubeControl.CubeCollectionOn;
import org.usfirst.frc.team3218.robot.commands.CubeControl.CubeControlOff;
import org.usfirst.frc.team3218.robot.commands.CubeControl.CubeControlXbox;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 *
 */
public class CubeControl extends Subsystem {
public double collectionSpeed = 1;
public double ejectionSpeed = 0.5;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public static  WPI_TalonSRX leftWheels = new WPI_TalonSRX(RobotMap.leftCollectionID);
	public static WPI_TalonSRX rightWheels = new WPI_TalonSRX(RobotMap.rightCollectionID);
	public static WPI_TalonSRX belt = new WPI_TalonSRX(RobotMap.beltID);
	public DifferentialDrive cubeCollect = new DifferentialDrive(leftWheels, rightWheels);
	public void initDefaultCommand() {
        // Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(new CubeControlXbox());

	}

	public void cubeCollection() {

	
			leftWheels.set(-collectionSpeed*.5);
			rightWheels.set(collectionSpeed);
	
		
	}
	public void cubeEjection(double power){
	    	leftWheels.set(power);
	    	rightWheels.set(-power);
	    	
	}
	public void cubeOff(){
		    leftWheels.set(0);
		    rightWheels.set(0);
		    belt.set(0);
	}
	public void beltOn(double power){
		belt.set(power);
	}
	
	public void cubeControlXbox(double y, double z){
		if(Robot.breakAuto){
		cubeCollect.arcadeDrive(-y * 0.75,y<-.1 ?.4:0);
		belt.set(z);
		}
	}
	
	

}
