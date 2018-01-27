package org.usfirst.frc.team3218.robot.subsystems;

import org.usfirst.frc.team3218.robot.OI;
import org.usfirst.frc.team3218.robot.Robot;
import org.usfirst.frc.team3218.robot.RobotMap;
import org.usfirst.frc.team3218.robot.CubeControl.commands.CubeControlOff;
import org.usfirst.frc.team3218.robot.CubeControl.commands.CubeCollectionOn;
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
public double ejectionSpeed = 0.5;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public static SpeedController leftWheel = new Talon(RobotMap.leftCollectionPort);
	public static SpeedController rightWheel = new Talon(RobotMap.rightCollectionPort);
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
			leftWheel.set(collectionSpeed);
			rightWheel.set(-collectionSpeed);
		}

		else {
			leftWheel.set(0);
			rightWheel.set(0);
		}
	}
	public void cubeEjection(){
		if(OI.button1.get()==false){
	    	leftWheel.set(-ejectionSpeed);
	    	rightWheel.set(ejectionSpeed);
	    }
	}
	public void cubeOff(){
		    leftWheel.set(0);
		    rightWheel.set(0);
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
