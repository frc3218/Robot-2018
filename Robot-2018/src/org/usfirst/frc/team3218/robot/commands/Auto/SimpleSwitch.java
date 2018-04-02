package org.usfirst.frc.team3218.robot.commands.Auto;

import javax.swing.text.html.FormSubmitEvent;

import org.usfirst.frc.team3218.robot.AutoAPI;
import org.usfirst.frc.team3218.robot.Robot;
import org.usfirst.frc.team3218.robot.RobotMap;
import org.usfirst.frc.team3218.robot.commands.CubeControl.CubeControlOff;
import org.usfirst.frc.team3218.robot.commands.CubeControl.CubeControlXbox;
import org.usfirst.frc.team3218.robot.commands.CubeControl.CubeEjectionOn;
import org.usfirst.frc.team3218.robot.subsystems.Lift;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SimpleSwitch extends Command {

    public SimpleSwitch() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    	requires(Robot.cubeControl);
    	requires(Robot.lift);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    
    	String sendableChosenString = Robot.position.getSelected() + Robot.gameData.substring(0,1);
    			switch(sendableChosenString){
    			case "1L": 
    				AutoAPI.moveToHeight(2);
    				AutoAPI.driveStraight(AutoAPI.AUTOLINE-25, 2000, 250);
      				Robot.cubeControl.cubeEjection(.5);
    				new CubeEjectionOn().start();
    				//Timer.delay(2);
    			//	AutoAPI.simpleDrive(-18);
    				
    			break;
    			case "3R":
    				AutoAPI.moveToHeight(2);
    				AutoAPI.driveStraight(AutoAPI.AUTOLINE-25, 2000, 250);
    				Robot.cubeControl.cubeEjection(.5);
    				new CubeEjectionOn().start();	
    			//	Timer.delay(2);
    			//	AutoAPI.simpleDrive(-18);
    				break;
    			
    			default:
    				AutoAPI.driveStraight(AutoAPI.AUTOLINE-20, 2000, 250);
    			break;
    	
    			}
    
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.lift.gearLow();  
    	Robot.lift.liftMaster.set(0);
    	Robot.lift.lift2.set(0);
    	Robot.cubeControl.cubeEjection(.5);
    	
    	
    }
    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
