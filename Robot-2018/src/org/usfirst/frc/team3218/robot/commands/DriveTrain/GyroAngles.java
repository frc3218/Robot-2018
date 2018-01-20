package org.usfirst.frc.team3218.robot.commands.DriveTrain;

import org.usfirst.frc.team3218.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class GyroAngles extends Command {
	public static double num;
    public GyroAngles() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveTrain.gyro.initGyro();
    	
    
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    num = Robot.driveTrain.gyro.getAngle();
    
    SmartDashboard.putNumber("AngleAverage",Robot.driveTrain.rollingAverage(num));
    SmartDashboard.putNumber("Gyro Angle", Robot.driveTrain.gyro.getAngle());
    SmartDashboard.putNumber("Gyro Rate", Robot.driveTrain.gyro.getRate());
    SmartDashboard.putNumber("Gyro Offset",Robot.driveTrain.gyro.getOffset());
    SmartDashboard.putNumber("Gyro Center", Robot.driveTrain.gyro.getCenter());
    
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
