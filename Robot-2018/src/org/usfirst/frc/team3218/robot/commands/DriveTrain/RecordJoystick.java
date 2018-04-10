package org.usfirst.frc.team3218.robot.commands.DriveTrain;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.usfirst.frc.team3218.robot.Robot;
import org.usfirst.frc.team3218.robot.subsystems.Lift;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RecordJoystick extends Command {
	public static double[][] joystickValues = new double[4][750];
	//y value = 0;
	//z value = 1;
	//collection = 2;
	//lift =3;
	boolean setter=false;
	int i = 0;
	
	private File folder;
	private BufferedWriter bufWriteY;
	private BufferedWriter bufWriteZ;
	private BufferedWriter bufWriteCollection;
	private BufferedWriter bufWriteLift;
	private FileWriter writerY;
	private FileWriter writerZ;
	private FileWriter writerCollection;
	private FileWriter writerLift;
	private File yValues;
	private File zValues;
	private File collectionValues;
	private File liftValues;
	private String startingLoc = "/home/lvuser/"+Robot.autoFile;
	public RecordJoystick() {
		
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
		setTimeout(15);
	}
	
    // Called just before this Command runs the first time
    protected void initialize() {
    	setter=false;
    	i=0;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	try{
    	if(Robot.oi.liftBottom.get()){
    		joystickValues[3][i] = 1;
    	}
    	else if(Robot.oi.liftSwitch.get()){
    		joystickValues[3][i] = 2;
    	}
    		else if(Robot.oi.liftScaleLow.get()){
    			joystickValues[3][i] = 3;
    		}
    		else if(Robot.oi.liftScaleMid.get()){
    			joystickValues[3][i] = 4;
    		}
    	
    	else if(Robot.oi.liftScaleHigh.get()){
    		joystickValues[3][i] = 5;

    	}
    	else{
    		
    		joystickValues[3][i] = 0;
    		
    	}
    	joystickValues[0][i] = Robot.oi.getXboxControllerLeftY();
    	joystickValues[1][i] = Robot.oi.getXboxControllerLeftZ();
    	joystickValues[2][i] = Robot.oi.getXboxControllerRightY();
    	i++;
    	}
    	catch(Exception e){
    		e.printStackTrace();
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
    	System.out.println("RecordStartingLoc = " + startingLoc);
    	yValues = new File(startingLoc+"/yValues.txt");
        zValues = new File(startingLoc+"/zValues.txt");
        collectionValues = new File(startingLoc+"/collectionValues.txt");
        liftValues = new File(startingLoc+"/liftValues.txt");

    System.out.println("files new files");
    
    if(!zValues.exists()){
    	try {
			zValues.createNewFile();
			yValues.createNewFile();
			collectionValues.createNewFile();
			liftValues.createNewFile();
			
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
			collectionValues.delete();
			liftValues.delete();
			yValues.createNewFile();
			zValues.createNewFile();
			collectionValues.createNewFile();
			liftValues.createNewFile();
		}
		catch(IOException e){
			
		}
    }
     writerY=null;
     writerZ=null;
     writerCollection = null;
     writerLift = null;
    
    System.out.println("made files");
    try{
    	System.out.println("about to make writers");
    	writerY = new FileWriter(yValues);
    	writerZ = new FileWriter(zValues);
    	writerCollection = new FileWriter(collectionValues);
    	writerLift = new FileWriter(liftValues);
    	if(writerY==null){
    		System.out.println("writers are still null");
    	}
    	System.out.println("made writers");
    }
    catch(IOException e){
    	e.printStackTrace();
    }
    
     bufWriteY = new BufferedWriter(writerY);
     bufWriteZ = new BufferedWriter(writerZ);
     bufWriteCollection = new BufferedWriter(writerCollection);
     bufWriteLift = new BufferedWriter(writerLift);
     System.out.println("about to start loop");
     int h=0;
    for(double i: joystickValues[0]){
    	
    	try {
    		bufWriteY.write(String.valueOf(joystickValues[0][h])+"\n");
			
			bufWriteZ.write(String.valueOf(joystickValues[1][h])+"\n");
			
			bufWriteCollection.write(String.valueOf(joystickValues[2][h])+"\n");
			
			bufWriteLift.write(String.valueOf(joystickValues[3][h])+"\n");
			
			h++;
    	} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("failed writing");
    		e.printStackTrace();
		}
   
    }
    try{
    	bufWriteY.close();
    	bufWriteZ.close();
    	bufWriteCollection.close();
    	bufWriteLift.close();
    }
    catch(IOException e){
    	e.printStackTrace();
    }
    System.out.println(joystickValues[0][749]);
    	System.out.println(joystickValues[1][749]);
    	//System.out.println(joystickValues[2][749]);
    	//System.out.println(joystickValues[3][749]);
    	System.out.println("went to ");
	}
    
    

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    
    }
}
