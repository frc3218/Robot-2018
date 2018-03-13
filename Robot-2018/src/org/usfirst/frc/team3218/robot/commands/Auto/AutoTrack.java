package org.usfirst.frc.team3218.robot.commands.Auto;

import org.usfirst.frc.team3218.robot.AutoAPI;
import org.usfirst.frc.team3218.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoTrack extends Command {

    public AutoTrack() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	AutoAPI.autoTurnToTarget();
		
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (AutoAPI.autoBlob.averageX < 170 && AutoAPI.autoBlob.averageX > 140);
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrain.drive(0, 0);
    	AutoAPI.cubeDistance = ((AutoAPI.autoBlob.averageY-130)/-30)*12; 
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}