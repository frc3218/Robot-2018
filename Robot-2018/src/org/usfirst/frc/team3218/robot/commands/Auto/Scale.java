package org.usfirst.frc.team3218.robot.commands.Auto;

import org.usfirst.frc.team3218.robot.AutoAPI;
import org.usfirst.frc.team3218.robot.Robot;

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
    	
    String sendableChosenString = Robot.position.getSelected()+Robot.path.getSelected()+Robot.gameData.substring(1,2);
    			switch(sendableChosenString){
    			case "1closeR": 
    			AutoAPI.driveStraight(AutoAPI.MID_LINE, 4000, 4000);;
    			break;
    			/*
    			case "1FarLeft": methodCall;
    			break;
    			case "1CloseRight": methodCall;
    			break;
    			case "1FarRight": methodCall;
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
    			*/
    			default:;
    			}
    
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
