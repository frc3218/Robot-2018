package org.usfirst.frc.team3218.robot.commands.Auto;

import org.usfirst.frc.team3218.robot.AutoAPI;
import org.usfirst.frc.team3218.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CrossLine extends Command {

    public CrossLine() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	AutoAPI.driveStraight(AutoAPI.AUTOLINE, 2000, 2000);

    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
   
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    //	Robot.autonomousCommand.cancel();
    	/*
    	Robot.lift.setPosition(Robot.lift.positionArray[0]);
    	Robot.driveTrain.rightMidDrive.set(0);
    	Robot.driveTrain.leftMidDrive.set(0);
    	*/
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
