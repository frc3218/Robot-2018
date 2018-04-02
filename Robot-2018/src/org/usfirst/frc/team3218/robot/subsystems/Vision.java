package org.usfirst.frc.team3218.robot.subsystems;

import org.usfirst.frc.team3218.robot.Robot;
import org.usfirst.frc.team3218.robot.RobotMap;
import org.usfirst.frc.team3218.robot.commands.Vision.Pixy;
//import org.usfirst.frc.team3218.robot.commands.Vision.LightsOut;
//import org.usfirst.frc.team3218.robot.commands.Vision.USBCamera;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Vision extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private byte[] BrightnessArray = new byte[] {(byte) 0xFE, 0x00, 0x64};
	public I2C pixyi2c = new I2C(I2C.Port.kOnboard, 0x54);
	public int tapeMinX = 130;
	public int tapeMaxX = 190;
	public int tapeRangeX = tapeMaxX - tapeMinX;
	public static double ratioToCenter;
	//Relay lightSpike = new Relay(RobotMap.lightsPort);
	
	static public void turnToTarget()
	{ 
		for(int i = 0; i < Pixy.blobArray.length; i++){
			//System.out.
		//	println(i + ": " + Dixy.blobArray[i].averageX);
		}
	//	System.out.println();
		
		Blob blob = Pixy.blobArray[1];
		ratioToCenter = (blob.averageX-160)/160;
		//if(Dixy.blobArray[]){
		if(/*Math.abs(ratioToCenter) >.15 &&*/ blob.wasUpdated && blob.averageWidth < 260){
		Robot.driveTrain.drive(Robot.oi.getXboxControllerLeftY(), ratioToCenter*1.8);
	}
	
		
	else{
		Robot.driveTrain.drive(Robot.oi.getXboxControllerLeftY(),0);
	}
		
	
	}


    public void initDefaultCommand() {
    	
        // Set the default command for a subsystem here.
        setDefaultCommand(new Pixy());
    }
}

