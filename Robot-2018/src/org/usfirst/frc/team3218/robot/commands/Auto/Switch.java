package org.usfirst.frc.team3218.robot.commands.Auto;

import javax.swing.text.html.FormSubmitEvent;

import org.usfirst.frc.team3218.robot.AutoAPI;
import org.usfirst.frc.team3218.robot.Robot;
import org.usfirst.frc.team3218.robot.subsystems.Lift;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Switch extends Command {

    public Switch() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    
    	String sendableChosenString = Robot.position.getSelected()
    			+Robot.path.getSelected()+Robot.gameData.substring(0,1);
    			switch(sendableChosenString){
    			case "1CloseL": 
    			AutoAPI.driveStraight(AutoAPI.WALL_TO_SWITCH, 0, 0);
    			AutoAPI.rotate(90, 0, 0);
    			AutoAPI.driveStraight(0,0,0);//drive to switch horizontal
    			AutoAPI.moveToHeight(2);
    			break;
    			case "1FarL":
    			AutoAPI.driveStraight(AutoAPI.WALL_TO_SWITCH, 0, 0);
        		AutoAPI.rotate(90, 0, 0);
        		AutoAPI.driveStraight(0,0,0);//drive to switch horizontal
        		AutoAPI.moveToHeight(2);
    			break;
    			case "1CloseR": 
    			AutoAPI.driveStraight(AutoAPI.WALL_TO_SWITCH_CHANNEL,0,0);
    			AutoAPI.rotate(90, 0, 0);
    			AutoAPI.driveStraight(0,0,0);//drive across field
    			AutoAPI.rotate(-90,0,0);
    			AutoAPI.driveStraight(0,0,0);//drive to switch vertical
    			AutoAPI.moveToHeight(2);
    			break;
    			case "1FarR":
    			AutoAPI.driveStraight(AutoAPI.WALL_TO_PLATFORM_CHANNEL,0,0);
    			AutoAPI.rotate(90,0,0);
    			AutoAPI.driveStraight(0,0,0);//drive across field
    			AutoAPI.rotate(90,0,0);
    			AutoAPI.driveStraight(0,0,0);//drive to switch vertical (backward)
    			AutoAPI.moveToHeight(2);
    			break;
    			/* "2CloseLeft": methodCall;
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
    			default:nothing;*/
    			}
    	
    	
    
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.lift.liftMaster.set(0);
    	Robot.lift.lift2.set(0);
    	Robot.cubeControl.cubeOff();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
