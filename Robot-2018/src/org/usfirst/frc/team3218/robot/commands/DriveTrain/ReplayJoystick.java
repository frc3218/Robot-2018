package org.usfirst.frc.team3218.robot.commands.DriveTrain;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ReplayJoystick extends Command {
	boolean setter = false;
    public ReplayJoystick() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    setter=true;
    
    
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
       if(zValues.exists()){
    	   System.out.println("file exists");
       }
       
        FileReader yReader = null;
        FileReader zReader = null;
        System.out.println("made readers & files");
        try{
        	yReader = new FileReader(yValues);
        	zReader = new FileReader(zValues);
        }
        catch(IOException e){
        	e.printStackTrace();
        }
        BufferedReader bufReadY = new BufferedReader(yReader);
        BufferedReader bufReadZ = new BufferedReader(zReader);
        String numberY = null;
        String numberZ = null;
        try {
        	for(int i=0; i<=750; i++){
        		while((numberY=bufReadY.readLine())!=null &&(numberZ=bufReadZ.readLine())!=null){
        		System.out.println("Y: " +numberY);
    		
        	
        		//System.out.println("Z: " +numberZ);
        		}
        		}
        } catch (IOException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
        
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
