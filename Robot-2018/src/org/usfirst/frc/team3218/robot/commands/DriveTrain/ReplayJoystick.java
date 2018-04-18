package org.usfirst.frc.team3218.robot.commands.DriveTrain;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;

import org.usfirst.frc.team3218.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ReplayJoystick extends Command {
	boolean setter = false;
	private File folder;
	private DecimalFormat format = new DecimalFormat("#.##");
	private File yValues;
	private File zValues;
	private File collectionValues;
	private File liftValues;
	private FileReader yReader;
	private FileReader zReader;
	private FileReader collectionReader;
	private FileReader liftReader;
	private BufferedReader bufReadY;
	private BufferedReader bufReadZ;
	private BufferedReader bufReadCollection;
	private BufferedReader bufReadLift;
	private String numberY;
	private String numberZ;
	private String numberCollect;
	private String numberLift;
	private String startingLoc = "/home/lvuser/"+Robot.autoFile;
	public ReplayJoystick() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	setter=false;
    	System.out.println("ReplayStartingLoc = " + startingLoc);
    	yValues = new File(startingLoc+"/yValues.txt");
        zValues = new File(startingLoc+"/zValues.txt");
        collectionValues = new File(startingLoc+"/collectionValues.txt");
        liftValues = new File(startingLoc+"/liftValues.txt");
       if(zValues.exists()){
   	   System.out.println("file exists");
      }
      
        yReader = null;
        zReader = null;
         collectionReader = null;
        liftReader = null;
       
       System.out.println("made readers & files");
       try{
       	yReader = new FileReader(yValues);
       	zReader = new FileReader(zValues);
       	collectionReader = new FileReader(collectionValues);
       	liftReader = new FileReader(liftValues);
       
       }
       catch(IOException e){
       	e.printStackTrace();
       }
        bufReadY = new BufferedReader(yReader);
        bufReadZ = new BufferedReader(zReader);
        bufReadCollection = new BufferedReader(collectionReader);
        bufReadLift = new BufferedReader(liftReader);
        numberY = null;
        numberZ = null;
        numberCollect = null;
        numberLift = null;
       
}

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	

		try {
			if((numberY=bufReadY.readLine())!=null &&(numberZ=bufReadZ.readLine())!=null
			&&(numberCollect=bufReadCollection.readLine())!=null&&(numberLift=bufReadLift.readLine())!=null){
			
			
					
					SmartDashboard.putNumber("y Value", ((Double.parseDouble(numberY))));
					SmartDashboard.putNumber("z value", (Double.parseDouble(numberZ)));
					
					SmartDashboard.putNumber("collect value", (Double.parseDouble(numberCollect)));
					SmartDashboard.putNumber("lift value",Double.parseDouble(numberLift));
					
					
					
			
			}	
			else{
				setter=true;
			}
		} catch (NumberFormatException | IOException e) {
			// TODO Auto-generated catch block
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
    	 
        
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
