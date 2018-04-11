package org.usfirst.frc.team3218.robot.subsystems;

import org.usfirst.frc.team3218.robot.RobotMap;
import org.usfirst.frc.team3218.robot.commands.CubeControl.CubeControlOff;
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
public class CubeControl extends Subsystem {
	
	public SpeedController leftWheels = new Talon(RobotMap.leftCubeControlID);
	public SpeedController rightWheels = new Talon(RobotMap.rightCubeControlID);
	
	//Grouping Together Drives
	
	DifferentialDrive cubeDrive = new DifferentialDrive(leftWheels, rightWheels);
	
	//Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(new CubeControlOff());
	}

    
    	public void cubeControlOff(){
    	leftWheels.set(0);
    	rightWheels.set(0);
    	}
   

	public void cubeEject(double ejectPower){
	leftWheels.set(ejectPower);
	rightWheels.set(ejectPower);
	
	}

	public void cubeCollect(double collectPower){
	leftWheels.set(collectPower);
	rightWheels.set(collectPower);
	
	
	}
	
	public void cubeControlXbox(double y, double z){
		cubeDrive.arcadeDrive(y*0.75,0);
	
	}
	
	public void cubeControlFile(double power){
	cubeDrive.arcadeDrive(power,0);
	
	}


}




