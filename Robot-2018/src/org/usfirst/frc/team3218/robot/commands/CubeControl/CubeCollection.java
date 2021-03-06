package org.usfirst.frc.team3218.robot.commands.CubeControl;

import org.usfirst.frc.team3218.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CubeCollection extends Command {

    public CubeCollection() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.cubeControl);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	
  if (Robot.cubeControl.limitSwitch.get() == false) 
    {
	  Robot.cubeControl.leftWheel.set(.2);
	  Robot.cubeControl.rightWheel.set(.2);
    }
  
  else{
	  Robot.cubeControl.leftWheel.set(0);
	  Robot.cubeControl.rightWheel.set(0);
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
