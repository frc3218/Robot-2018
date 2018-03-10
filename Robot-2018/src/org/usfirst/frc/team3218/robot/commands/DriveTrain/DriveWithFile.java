package org.usfirst.frc.team3218.robot.commands.DriveTrain;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.usfirst.frc.team3218.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveWithFile extends Command {
	public String leftMotorValue = null;
    public String rightMotorValue = null;
    BufferedReader bufReadY;
    BufferedReader bufReadZ;
    File yValues;
    File zValues;
    FileReader yReader;
    FileReader zReader;
    boolean setter;
    public DriveWithFile() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveTrain.leftDriveBack.setSafetyEnabled(false);
    	Robot.driveTrain.rightDriveBack.setSafetyEnabled(false);
    	Robot.driveTrain.leftDriveFront.setSafetyEnabled(false);
    	Robot.driveTrain.rightDriveFront.setSafetyEnabled(false);
    	setter=false; 
    	yValues = new File("/home/lvuser/yValues.txt");
         zValues = new File("/home/lvuser/zValues.txt");
        System.out.println("found files");
        
        if(zValues.exists()){
    	   System.out.println("file exists");
       }
       
         yReader = null;
         zReader = null;
        System.out.println("made readers & files");
        try{
        	yReader = new FileReader(yValues);
        	zReader = new FileReader(zValues);
        }
        catch(IOException e){
        	e.printStackTrace();
        }
         bufReadY = new BufferedReader(yReader);
         bufReadZ = new BufferedReader(zReader);
        
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
        try {
        	
        	leftMotorValue = bufReadY.readLine();
        	rightMotorValue = bufReadZ.readLine();
        	if(leftMotorValue!=null&& rightMotorValue!=null){
        		Robot.driveTrain.leftDriveFront.set(Double.parseDouble(leftMotorValue));
        		Robot.driveTrain.rightDriveFront.set(Double.parseDouble(rightMotorValue));
        		Robot.driveTrain.leftDriveBack.set(Double.parseDouble(leftMotorValue));
        		Robot.driveTrain.rightDriveBack.set(Double.parseDouble(rightMotorValue));
        	}
        	else{
        		
        		setter = true;
        	}
        } catch (IOException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
       if(setter){
    	   return true;
       }
       else{
    	   return false;
       }
    }

    // Called once after isFinished returns true
    protected void end() {
    	try{
    	bufReadY.reset();
    	bufReadZ.reset();
    	System.out.println("reset readers");
    	}catch(IOException e){
    		e.printStackTrace();
    	}
    	}

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	 System.out.println("was in interuppted");
    }
}
