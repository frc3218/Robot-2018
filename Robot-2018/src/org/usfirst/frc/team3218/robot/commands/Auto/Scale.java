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
    	requires(Robot.driveTrain);
    	requires(Robot.lift);
    	requires(Robot.cubeControl);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    String sendableChosenString = Robot.position.getSelected()+Robot.gameData.substring(1,2);
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
        			AutoAPI.moveToHeight(1);
        			AutoAPI.driveStraight(AutoAPI.HORIZONTAL_FAR_SIDE+18, 2000, 250);
        			AutoAPI.rotate(-90,700,700);
        			AutoAPI.driveStraight(AutoAPI.MID_LINE-AutoAPI.WALL_TO_PLATFORM_CHANNEL-43,2000,250);
        			AutoAPI.simpleDrive(0);
        			AutoAPI.moveToHeight(5);
        			Robot.cubeControl.cubeEjection();
        			Timer.delay(1);
        			Robot.cubeControl.cubeOff();
        			AutoAPI.simpleDrive(-6);
        			AutoAPI.moveToHeight(0);
        			AutoAPI.rotate(70, 700, 700);
        			break;
        			
        			case "3R": 
        				AutoAPI.driveStraight(AutoAPI.MID_LINE, 2000, 250);
            			AutoAPI.rotate(-90, 700, 700);
            			AutoAPI.moveToHeight(5);
            			Robot.cubeControl.cubeEjection();
            			Timer.delay(1);
            			Robot.cubeControl.cubeOff();
            			AutoAPI.moveToHeight(0);
            			AutoAPI.rotate(-70, 700, 700);
        				break;
        				
        			case "3L": 
            			AutoAPI.driveStraight(AutoAPI.WALL_TO_PLATFORM_CHANNEL, 2000, 250);
            			AutoAPI.rotate(-90, 700, 700);
            			AutoAPI.moveToHeight(1);
            			AutoAPI.driveStraight(AutoAPI.HORIZONTAL_FAR_SIDE+18, 2000, 250);
            			AutoAPI.rotate(90,700,700);
            			AutoAPI.driveStraight(AutoAPI.MID_LINE-AutoAPI.WALL_TO_PLATFORM_CHANNEL-43,2000,250);
            			AutoAPI.simpleDrive(0);
            			AutoAPI.moveToHeight(5);
            			Robot.cubeControl.cubeEjection();
            			Timer.delay(1);
            			Robot.cubeControl.cubeOff();
            			AutoAPI.simpleDrive(-6);
            			AutoAPI.moveToHeight(0);
            			AutoAPI.rotate(-70, 700, 700);
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
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
