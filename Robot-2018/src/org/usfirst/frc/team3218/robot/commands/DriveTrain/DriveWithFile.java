package org.usfirst.frc.team3218.robot.commands.DriveTrain;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormat; 
import org.usfirst.frc.team3218.robot.Robot;
import org.usfirst.frc.team3218.robot.subsystems.CubeControl;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveWithFile extends Command {
	boolean setter = false;
	private File folder;
	private DecimalFormat format = new DecimalFormat("#");
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
	public DriveWithFile() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
		requires(Robot.driveTrain);
		requires(Robot.lift);
		requires(Robot.cubeControl);
	}

    // Called just before this Command runs the first time
    protected void initialize() {
    	setter=false;
    	folder = new File(Robot.autoFile.getSelected());
    	System.out.println("FOLDER IS : " +Robot.autoFile.getSelected());
    	yValues = new File(folder.getAbsolutePath()+"/yValues.txt");
        zValues = new File(folder.getAbsolutePath()+"/zValues.txt");
        collectionValues = new File(folder.getAbsolutePath()+"/collectionValues.txt");
        liftValues = new File(folder.getAbsolutePath()+"/liftValues.txt");
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
			numberY = bufReadY.readLine();
			numberZ = bufReadZ.readLine();
			if(numberY !=null && numberZ != null){
			
			
					
					Robot.driveTrain.driveWithXbox(Double.parseDouble(numberY), Double.parseDouble(numberZ));
					
					
					//Robot.cubeControl.cubeControlFile(Double.parseDouble(numberCollect));
					
					//Robot.lift.setPosition(Robot.lift.positionArray[(int)Double.parseDouble(numberLift)]);
					
			}	
			else{
				System.out.println("didnt pass if");
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
