package org.usfirst.frc.team3218.robot.commands.Auto;

import org.usfirst.frc.team3218.robot.AutoAPI;
import org.usfirst.frc.team3218.robot.Robot;
import org.usfirst.frc.team3218.robot.commands.CubeControl.CubeEjectionOn;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DoubleScale extends Command {

    public DoubleScale() {
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
				  AutoAPI.driveStraight(AutoAPI.MID_LINE-36, 2000, 250);
     			  AutoAPI.rotate(45,1200,1200);
     			  AutoAPI.moveToHeight(5);
     			  Robot.cubeControl.cubeEjection(.085);
     			  Timer.delay(.3);
     			  Robot.cubeControl.cubeCollection();
     			  AutoAPI.moveToHeight(0);
     			  AutoAPI.rotate(90,1200,1200);
     			  AutoAPI.driveStraight(64, 2000, 250);
     			  Robot.cubeControl.cubeOff();
     			 AutoAPI.driveStraight(64, -2000, 250);//speed negative for reverse
     			 AutoAPI.rotate(-90,1200,1200);
     			 AutoAPI.moveToHeight(5);
    			  Robot.cubeControl.cubeEjection(.085);
    			  
    			  
 			break;

 			case "1R": 
 				AutoAPI.driveStraight(AutoAPI.WALL_TO_PLATFORM_CHANNEL, 2000, 250);
     			AutoAPI.rotate(90, 1200, 1200);
     			AutoAPI.moveToHeight(1);
     			AutoAPI.driveStraight(AutoAPI.HORIZONTAL_FAR_SIDE+30, 2000, 250);
     			AutoAPI.rotate(-90,1200,1200);
     			AutoAPI.simpleDrive(2);
     			AutoAPI.moveToHeight(5);
     			AutoAPI.simpleDrive(15);
     			Robot.cubeControl.cubeEjection(.085);
     			Timer.delay(.3);
     			Robot.cubeControl.cubeCollection();
     			AutoAPI.moveToHeight(0);
   			  AutoAPI.rotate(180,1200,1200);
 			break;
 			
 			case "3R": 
     				
     			
     			  AutoAPI.driveStraight(AutoAPI.MID_LINE-36, 2000, 250);
     			  AutoAPI.rotate(-45,1200,1200);
     			  AutoAPI.moveToHeight(5);
     			  Robot.cubeControl.cubeEjection(.085);
     			  Timer.delay(.3);
     			  Robot.cubeControl.cubeCollection();
     			  AutoAPI.moveToHeight(0);
     			  AutoAPI.rotate(-90,1200,1200);
     			  AutoAPI.driveStraight(64, 2000, 250);
     			  Robot.cubeControl.cubeOff();
     			 AutoAPI.driveStraight(64, -2000, 250);//speed negative for reverse
     			 AutoAPI.rotate(90,1200,1200);
     			 AutoAPI.moveToHeight(5);
    			  Robot.cubeControl.cubeEjection(.085);
    			  
 				break;
 				
 			case "3L": 
 				
     			AutoAPI.driveStraight(AutoAPI.WALL_TO_PLATFORM_CHANNEL, 2000, 250);
     			AutoAPI.rotate(-90, 1200, 1200);
     			AutoAPI.moveToHeight(1);
     			AutoAPI.driveStraight(AutoAPI.HORIZONTAL_FAR_SIDE+30, 2000, 250);
     			AutoAPI.rotate(90,1200,1200);
     			AutoAPI.simpleDrive(2);
     			AutoAPI.moveToHeight(5);
     			AutoAPI.simpleDrive(15);
     			Robot.cubeControl.cubeEjection(.085);
     	
     			Timer.delay(.3);
     			
     			Robot.cubeControl.cubeCollection();
     			AutoAPI.moveToHeight(0);
     			AutoAPI.rotate(180,1200,1200);
     			AutoAPI.driveStraight(64, 2000, 250);
     			 Robot.cubeControl.cubeOff();
     			 AutoAPI.driveStraight(32, -2000, 250);//speed negative for reverse
     			AutoAPI.rotate(180,1200,1200);
     			AutoAPI.moveToHeight(5);
     			 AutoAPI.driveStraight(32, 2000, 250);
     			Robot.cubeControl.cubeEjection(.085);
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
    	//new CubeEjectionOn().start();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
