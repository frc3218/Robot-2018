package org.usfirst.frc.team3218.robot.subsystems;

import org.usfirst.frc.team3218.robot.RobotMap;
import org.usfirst.frc.team3218.robot.commands.lift.SetLiftPosition;

import com.ctre.CANTalon;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Lift extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	//these values need testing
	 int CruiseVelocity; //encoderticks per 100ms
	 int Acceleration; //encoderticks per 100ms per second
	public  int desiredPosition=0;
	public float ticksPerInch;
	
	public int[] positionArray = new int[]{0,0,0,0,0};//array of positions for the lift in inches
	
	public WPI_TalonSRX liftCim = new WPI_TalonSRX(RobotMap.liftCimPort);
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new SetLiftPosition());
    	
    }
    
    public void liftPIDConfig(){
    	//timeouts and PIDidx are 0
    
    	liftCim.set(ControlMode.MotionMagic, 0);
    	liftCim.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
    	liftCim.configMotionCruiseVelocity(CruiseVelocity, 0);
    	liftCim.configMotionAcceleration(Acceleration, 0);
    	
    }
    
    
    
    
    
}

