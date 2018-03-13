package org.usfirst.frc.team3218.robot.commands.Auto;

import org.usfirst.frc.team3218.robot.AutoAPI;
import org.usfirst.frc.team3218.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Scale extends Command {

    public Scale() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	String sendableChosenString = Robot.position.getSelected()
    			+Robot.gameData.substring(0,1);
    			switch(sendableChosenString){
    			case "1L": 
    			AutoAPI.driveStraight(AutoAPI.MID_LINE, 2000, 250);
    			AutoAPI.rotate(90, 700, 700);
    			AutoAPI.moveToHeight(5);
    			Robot.cubeControl.cubeEjection();
    			Timer.delay(1);
    			Robot.cubeControl.cubeOff();
    			AutoAPI.moveToHeight(0);
    			AutoAPI.rotate(70, 700, 700);
    			break;

    			case "1R": 
    			AutoAPI.driveStraight(AutoAPI.WALL_TO_PLATFORM_CHANNEL, 2000, 250);
    			AutoAPI.rotate(90, 700, 700);
    			AutoAPI.driveStraight(AutoAPI.HORIZONTAL_FAR_SIDE+18, 2000, 250);
    			AutoAPI.rotate(-90,700,700);
    			AutoAPI.driveStraight(AutoAPI.MID_LINE-AutoAPI.WALL_TO_PLATFORM_CHANNEL-43,2000,250);
    			AutoAPI.simpleDrive(0);
    			AutoAPI.moveToHeight(5);
    			//AutoAPI.simpleDrive(6);
    			Robot.cubeControl.cubeEjection();
    			Timer.delay(1);
    			Robot.cubeControl.cubeOff();
    			AutoAPI.simpleDrive(-6);
    			AutoAPI.moveToHeight(0);
    			AutoAPI.rotate(45, 700, 700);
    			break;
    			}
    			
    			/*case "1FarRight": methodCall;
    			break;
    			case "2CloseLeft": methodCall;
    			break;
    			case "2FarLeft": methodCall;
    			break;
    			case "2CloseRight": methodCall;
    			break;
    			case "2FarRight": methodCall;
    			break;
    			case "3CloseLeft": methodCall;
    			break;
    			case "3FarLeft": methodCall;
    			break;
    			case "3CloseRight": methodCall;
    			break;
    			case "3FarRight": methodCall;
    			break;
    			default:nothing;
    			}
    */
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
