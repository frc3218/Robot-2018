package org.usfirst.frc.team3218.robot.commands.Auto;

import org.usfirst.frc.team3218.robot.EazyBreezy_Auto;
import org.usfirst.frc.team3218.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class NewScale extends Command {
	boolean stop = false;
    public NewScale() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	System.out.println("in scale new");
		
    	Command arcing = new AutoDrive(84, 1, 90, "right", "low", false, null, false);
		//Command straight = new AutoDrive(12,1,0,"none","low",true,arcing,false);
    	//Command toSwitch = new AutoDrive(24,1,0,"straight","low",true,arcing,false);
		
		System.out.println("made command");
		EazyBreezy_Auto.autoDriving(arcing);
		System.out.println("did auto driving to switch");
		stop = true;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
		
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
       if(stop)
    	   return true;
       else
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
