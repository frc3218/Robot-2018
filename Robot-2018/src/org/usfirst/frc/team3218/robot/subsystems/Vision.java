package org.usfirst.frc.team3218.robot.subsystems;

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
	//Relay lightSpike = new Relay(RobotMap.lightsPort);
	
	public void lightsOn(){
		
	//	lightSpike.set(Value.kOn);
		
	}

	public void lightsOut(){
		
	//	lightSpike.set(Value.kOff);
		
	}
	

    public void initDefaultCommand() {
    	
        // Set the default command for a subsystem here.
        setDefaultCommand(new Pixy());
    }
}

