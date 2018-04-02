package org.usfirst.frc.team3218.robot.commands.Lift;

import org.usfirst.frc.team3218.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriverDown extends Command {

    public DriverDown() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.lift);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Robot.lift.liftEnc.get()>1000){
    		Robot.lift.liftMaster.set(-.4);
    		Robot.lift.lift2.set(-.4);
    	}
    	else{
    		Robot.lift.liftMaster.set(0);
    		Robot.lift.lift2.set(0);

    	}
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
