package org.usfirst.frc.team3218.robot.commands.Auto;

import org.usfirst.frc.team3218.robot.AutoAPI;
import org.usfirst.frc.team3218.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ScaleSwitch extends Command {

    public ScaleSwitch() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    	requires(Robot.cubeControl);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	String sendableChosenString = Robot.position.getSelected() + Robot.gameData.substring(0,2);
		switch(sendableChosenString){
		case "1LL": 
			Robot.cubeControl.cubeCollection();
			AutoAPI.simpleDrive((int)AutoAPI.cubeDistance + 10);
			Robot.cubeControl.cubeOff();
			AutoAPI.simpleDrive(-24);
			AutoAPI.rotate(90, 700, 700);
			AutoAPI.driveStraight(AutoAPI.HORIZONTAL_FAR_SIDE-122,2000,250);
			AutoAPI.rotate(90, 700, 700);
			AutoAPI.driveStraight(AutoAPI.MID_LINE-AutoAPI.WALL_TO_PLATFORM_CHANNEL-12,2000,250);
			AutoAPI.rotate(90, 700, 700);
			AutoAPI.moveToHeight(5);
			Robot.cubeControl.cubeEjection();
			Timer.delay(1);
			Robot.cubeControl.cubeOff();
			AutoAPI.moveToHeight(0);
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
