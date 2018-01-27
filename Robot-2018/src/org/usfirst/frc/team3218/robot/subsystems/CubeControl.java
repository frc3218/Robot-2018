package org.usfirst.frc.team3218.robot.subsystems;

import org.usfirst.frc.team3218.robot.OI;
import org.usfirst.frc.team3218.robot.Robot;
import org.usfirst.frc.team3218.robot.RobotMap;
import org.usfirst.frc.team3218.robot.commands.CubeControl.CubeCollectionOn;
import org.usfirst.frc.team3218.robot.commands.CubeControl.CubeControlOff;

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
public double collectionSpeed = 0.3;
public double ejectionSpeed = 1;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public static  WPI_TalonSRX leftWheels = new WPI_TalonSRX(RobotMap.leftCollectionID);
	public static WPI_TalonSRX rightWheels = new WPI_TalonSRX(RobotMap.rightCollectionID);
	public static DigitalInput limitSwitch = new DigitalInput(RobotMap.limitSwitchPortA);
	
	public static Solenoid leftKorey = new Solenoid(RobotMap.leftKoreyPort);
	public static Solenoid rightKorey = new Solenoid(RobotMap.rightKoreyPort);
	public static Solenoid pistonKorey = new Solenoid(RobotMap.pistonKoreyPort);
	
	public void initDefaultCommand() {
        // Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(new CubeControlOff());

	}

	public void cubeCollection() {

		if (limitSwitch.get() == false) {
			leftWheels.set(collectionSpeed);
			rightWheels.set(-collectionSpeed);
		}

		else {
			leftWheels.set(0);
			rightWheels.set(0);
		}
	}
	public void cubeEjection(){
		if(OI.button1.get()==false){
	    	leftWheels.set(-ejectionSpeed);
	    	rightWheels.set(ejectionSpeed);
	    }
	}
	public void cubeOff(){
		    leftWheels.set(0);
		    rightWheels.set(0);
		    pistonKorey.set(false);
	}
	public void koreyOn(){
		leftKorey.set(true);
		rightKorey.set(true);
	}
	public void koreyOff(){
		leftKorey.set(false);
		rightKorey.set(false);
	}
	public void koreyEject(){
		pistonKorey.set(true);
	}
}
