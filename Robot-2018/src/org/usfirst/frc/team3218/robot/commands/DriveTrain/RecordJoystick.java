package org.usfirst.frc.team3218.robot.commands.DriveTrain;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;

import org.usfirst.frc.team3218.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RecordJoystick extends Command {
	public static double[][] joystickValues = new double[2][750];
	boolean setter=false;
	int i = 0;
	public RecordJoystick() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	setTimeout(15);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
   
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	try{
    	
    	
    	joystickValues[0][i] = Robot.driveTrain.leftDriveFront.getMotorOutputPercent();
    		joystickValues[1][i] = Robot.driveTrain.rightDriveFront.getMotorOutputPercent();
    		i++;
    	}
    	catch(Exception e){
    		setter=true;
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
    
    	
    File yValues = new File("/home/lvuser/yValues.txt");
    File zValues = new File("/home/lvuser/zValues.txt");
    System.out.println("files new files");
    if(!zValues.exists()){
    	try {
			zValues.createNewFile();
			yValues.createNewFile();
			System.out.println("files new files made");
    	} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    else{
		try{
			zValues.delete();
			yValues.delete();
			yValues.createNewFile();
			zValues.createNewFile();
		}
		catch(IOException e){
			
		}
    }
    FileWriter writerY=null;
    FileWriter writerZ=null;
    System.out.println("made files");
    try{
    	System.out.println("about to make writers");
    	writerY = new FileWriter(yValues);
    	writerZ = new FileWriter(zValues);
    	System.out.println("made writers");
    }
    catch(IOException e){
    	e.printStackTrace();
    }
    System.out.println("couldnt make bufwriters");
    BufferedWriter bufWriteY = new BufferedWriter(writerY);
    BufferedWriter bufWriteZ = new BufferedWriter(writerZ);
    
    int h=0;
    for(double i: joystickValues[0]){
    	
    	try {
			bufWriteY.write(String.valueOf(String.valueOf(joystickValues[0][h])));
			
			bufWriteZ.write(String.valueOf(String.valueOf(joystickValues[1][h])));
			bufWriteY.newLine();
			bufWriteZ.newLine();
			h++;
    	} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   
    }
    try{
    	bufWriteY.close();
    	bufWriteZ.close();
    }
    catch(IOException e){
    	e.printStackTrace();
    }
    System.out.println(joystickValues[0][749]);
    	System.out.println(joystickValues[1][749]);
    	System.out.println("went to ");
	}
    
    

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
