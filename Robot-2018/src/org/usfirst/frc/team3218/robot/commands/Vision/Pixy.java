package org.usfirst.frc.team3218.robot.commands.Vision;

import org.usfirst.frc.team3218.robot.Robot;
import org.usfirst.frc.team3218.robot.subsystems.Blob;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Pixy extends Command {

	public static final int MAX_SIGNATURES = 3; //TODO: make constants final
	public static final int MAX_OBJECTS = 4;
	public static final int SAMPLE_COUNT = 10;
	float smoothingFactor=0.5f;//figure out the best number for this
	int maxBytes = 14 * MAX_OBJECTS + 2;

	char currentChecksum;
	char currentSig;
	char currentX;
	char currentY;
	char currentWidth;
	char currentHeight;
	
	//start of object sync: 0xaa55
	
	//boolean[] wasUpdated = new boolean[MAX_SIGNATURES];
	
	
	//[signature] [sampleCount]
	//Blob blob = new Blob();
	public Blob[] blobArray = new Blob[MAX_SIGNATURES];
	
    public Pixy() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.vision);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	for(int i = 0; i < blobArray.length; i++)
    	{
    		blobArray[i]=new Blob();
    	}

    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	byte[] pixyValues = new byte[maxBytes];
    	for(int i = 0; i< pixyValues.length;i++){
    		pixyValues[i] = 1;
    	}
    	if(Robot.vision.pixyi2c.readOnly(pixyValues, maxBytes))
    		System.out.println("FailedToRead"); //if this line is running you likely have one of two problems, either your interface (found in pxymon-> configure-> interface-> data output:i2c), or you didn't plug it in right
    	for(int i = 0; i < pixyValues.length; i++)
    	{
    		//System.out.println(pixyValues[i]);
    	}
	// set was updated array to false.
    	for(int i = 0; i<blobArray.length; i++)  
    	{
    		blobArray[i].wasUpdated=false;
    	}
    	int i = 0;
    
	//checks if data has been put into storage array
    	if(pixyValues!=null)
    	{
    		//search through data array to find object
    		while(littleEndianToBigEndian(pixyValues[i],pixyValues[i+1])!=0xaa55)
    		{
    			i++;
    			
			//if no object is found within the data exit the loop
    			if (i>maxBytes-2)
    			{
    				break;
    			}
    	}
	
    	i+=2;
	
	//if there is room for an object's data in the array and an object is found
    	//System.out.println("82");
    	if(i<maxBytes-14 && littleEndianToBigEndian(pixyValues[i],pixyValues[i+1])==0xaa55)
    	{
    		//segments chunks of object Data
    		System.out.println("85");
    		for(;i < pixyValues.length-14; i+=14)	
    		{
			//checks for beginning of object
    			System.out.println("89");
    			if(littleEndianToBigEndian(pixyValues[i],pixyValues[i+1]) == 0xaa55)  
    			{
    				System.out.println("92");
    				//sets all variables for current object in for loop
    				currentChecksum = littleEndianToBigEndian(pixyValues[i + 2],pixyValues[i + 3]);
    				currentSig = littleEndianToBigEndian(pixyValues[i + 4],pixyValues[i + 5]);
    				currentX = littleEndianToBigEndian(pixyValues[i + 6],pixyValues[i + 7]);
    				currentY = littleEndianToBigEndian(pixyValues[i + 8],pixyValues[i + 9]);
    				currentWidth = littleEndianToBigEndian(pixyValues[i + 10],pixyValues[i + 11]); 
    				currentHeight = littleEndianToBigEndian(pixyValues[i + 12],pixyValues[i + 13]);
    				
				//checksum for one object
    				if( currentChecksum == (currentSig + currentX + currentY + currentWidth + currentHeight) && (currentChecksum > 0 )){//make sure data is good		
    					System.out.println("it ran");
    					int tempInt = currentSig;
    					/*SmartDashboard.putNumber("sig" , currentSig);   			
    					SmartDashboard.putNumber("X" +tempInt, blobArray[currentSig].averageX);
    			    	SmartDashboard.putNumber("Y" +tempInt, blobArray[currentSig].averageY);
    			    	SmartDashboard.putNumber("Width"+tempInt, blobArray[currentSig].averageWidth);
    			    	SmartDashboard.putNumber("Height"+tempInt, blobArray[currentSig].averageHeight);
    					*/
    					calculateAverage(currentX, currentY, currentWidth, currentHeight, blobArray[currentSig]);
    					
    				}//checksum if close				
    			}//check for object and successful parse if close
    		}//for loop that segments object data close
    	}//if that checks for object in data close
    }//if the array has any data check close
  }//execute close

	/**
	 * this will calculate the average location & size over time of previous blob samples
	 * @param X: the horizontal position of the blob
	 * @param Y: the vertical position of the blob
	 * @param Width: the width of the blob
	 * @param Height: the height of the blob
	 */
	public void calculateAverage(char X, char Y, char Width, char Height, Blob blob) 
	{
		if(blob.wasUpdated)
		{
			smoothingFactor = (float) SmartDashboard.getNumber("smoothness", 0.5);
			blob.averageHeight = (smoothingFactor*(float)Height*(1-smoothingFactor)*blob.averageHeight-1);
			blob.averageWidth = (smoothingFactor*(float)Height*(1-smoothingFactor)*blob.averageWidth-1);
			blob.averageX = (smoothingFactor*(float)Height*(1-smoothingFactor)*blob.averageX-1);
			blob.averageY = (smoothingFactor*(float)Height*(1-smoothingFactor)*blob.averageY-1);	
		} else {
			blob.averageHeight = Height;
			blob.averageWidth = Width;
			blob.averageX = X;
			blob.averageY = Y;
		}
		SmartDashboard.putNumber("X", blob.averageX);
		SmartDashboard.putNumber("Y", blob.averageY);
		SmartDashboard.putNumber("W", blob.averageWidth);
		SmartDashboard.putNumber("H", blob.averageHeight);
	}
	public void ChangeBrightness(byte Brightness)
	{
		byte[] lightnessArr = new byte[] {(byte) 0xFE, 0x00, Brightness};
		Robot.vision.pixyi2c.writeBulk(lightnessArr);
	}
    private char littleEndianToBigEndian(byte one, byte two)
    {
    	return (char) (((two & 0xff) << 8) | (one & 0xff)); 
	    
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
