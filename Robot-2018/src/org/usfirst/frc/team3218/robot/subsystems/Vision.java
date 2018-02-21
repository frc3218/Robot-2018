package org.usfirst.frc.team3218.robot.subsystems;

import org.usfirst.frc.team3218.robot.OI;
import org.usfirst.frc.team3218.robot.Robot;
import org.usfirst.frc.team3218.robot.RobotMap;
import org.usfirst.frc.team3218.robot.commands.Vision.Dixy;
//import org.usfirst.frc.team3218.robot.commands.Vision.LightsOut;
//import org.usfirst.frc.team3218.robot.commands.Vision.USBCamera;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Vision extends Subsystem {
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private byte[] BrightnessArray = new byte[] {(byte) 0xFE, 0x00, 0x64};
	public static I2C pixyi2c = new I2C(I2C.Port.kOnboard, 0x54);
	public int tapeMinX = 130;
	public int tapeMaxX = 190;
	public int tapeRangeX = tapeMaxX - tapeMinX;
	//Relay lightSpike = new Relay(RobotMap.lightsPort);
	
	public void lightsOn(){
		
	//	lightSpike.set(Value.kOn);
		
	}
	
	static public void turnToTarget()
	{ 
		for(int i = 0; i < Dixy.blobArray.length; i++){
			System.out.println(i + ": " + Dixy.blobArray[i].averageX);
		}
		System.out.println();
		Blob blob = Dixy.blobArray[1];
		SmartDashboard.putNumber("Important", blob.averageX);
		SmartDashboard.putBoolean("YEEEEEEEEET", blob.wasUpdated);
		SmartDashboard.putBoolean("YOOOOOOOOOT", Dixy.blobArray[0].wasUpdated);
		Robot.driveTrain.drive(OI.getXboxControllerLeftY(), blob.averageX>160 ? 0.6 : -0.6);
	}

	public void lightsOut(){
		
	//	lightSpike.set(Value.kOff);
		
	}
	

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new Dixy());
    }
}

