package org.usfirst.frc.team3218.robot.commands.DriveTrain;

import org.usfirst.frc.team3218.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AverageEncoder extends Command {
	double bigNumber;
	int times = 0;
   boolean end = false;
	public AverageEncoder() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    if(times<250) {
    	bigNumber = bigNumber+Robot.driveTrain.rightEnc.getRate();
    }
    else {
    	System.out.println((bigNumber/250));
    	end = true;
    }
    	times++;
    }
    
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return end;
        
    }

    // Called once after isFinished returns true
    protected void end() {
    	times = 0;
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
