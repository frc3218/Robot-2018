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
public class Switch extends Command {

    public Switch() {
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
    			case"2R":
    				AutoAPI.moveToHeight(2);
    				AutoAPI.driveStraight(AutoAPI.WALL_TO_SWITCH-50, 2000, 250);//which distance should this be
    				Robot.cubeControl.cubeEjection(.6);
    				new CubeEjectionOn();
    			break;
    			case"2L":
    				AutoAPI.driveStraight((AutoAPI.WALL_TO_SWITCH/2-36), 2000, 250);
    				AutoAPI.rotate(-90, 1200, 1200);
    				AutoAPI.driveStraight(96, 2000, 250);
    				AutoAPI.rotate(90, 1200, 1200);
    				AutoAPI.moveToHeight(2);
    				AutoAPI.driveStraight((AutoAPI.WALL_TO_SWITCH/2-36), 2000, 250);
    				Robot.cubeControl.cubeEjection(.6);
    				new CubeEjectionOn();
    				
    			break;
    			case "1L": 
    				AutoAPI.driveStraight(AutoAPI.WALL_TO_SWITCH-36, 2000, 250);
    				AutoAPI.rotate(90, 900, 900);
    				AutoAPI.moveToHeight(2);
    				AutoAPI.simpleDrive(30);
    				Robot.cubeControl.cubeEjection(.8);
    				Timer.delay(2);
       				AutoAPI.simpleDrive(-18);
    			
    			break;
    			case "1R": 
    				AutoAPI.driveStraight(AutoAPI.WALL_TO_PLATFORM_CHANNEL, 2000, 250);
    				AutoAPI.rotate(90, 700, 700);
    				AutoAPI.moveToHeight(1);
    				AutoAPI.driveStraight(AutoAPI.HORIZONTAL_FAR_SIDE+50,2000,250);//drive across field
    				AutoAPI.rotate(90,700,700);
    				AutoAPI.simpleDrive(60);
    				AutoAPI.rotate(90,700,700);
    				AutoAPI.moveToHeight(2);
    				AutoAPI.simpleDrive(16);
    				Robot.cubeControl.cubeEjection(.8);
    				Timer.delay(2);
    				AutoAPI.simpleDrive(-18);
    			
    			break;
    			case "3R":
    				AutoAPI.driveStraight(AutoAPI.WALL_TO_SWITCH-36, 2000, 250);
    				AutoAPI.rotate(-90, 900, 700);
    				AutoAPI.moveToHeight(2);
    				AutoAPI.simpleDrive(30);
    				Robot.cubeControl.cubeEjection(.8);
    				Timer.delay(2);
    				AutoAPI.simpleDrive(-18);
    				break;
    			case "3L":
    				AutoAPI.driveStraight(AutoAPI.WALL_TO_PLATFORM_CHANNEL, 2000, 250);
    				AutoAPI.rotate(-90, 700, 700);
    				AutoAPI.moveToHeight(1);
    				AutoAPI.driveStraight(AutoAPI.HORIZONTAL_FAR_SIDE+50,2000,250);//drive across field
    				AutoAPI.rotate(-90,700,700);
    				AutoAPI.simpleDrive(60);
    				AutoAPI.rotate(-90,700,700);
    				AutoAPI.moveToHeight(2);
    				AutoAPI.simpleDrive(16);
    				Robot.cubeControl.cubeEjection(.8);
    				Timer.delay(2);
    				AutoAPI.simpleDrive(-18);
    				
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
    	Robot.cubeControl.cubeEjection(.8);
    	new CubeEjectionOn().start();
    }
    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
