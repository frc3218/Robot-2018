package org.usfirst.frc.team3218.robot.subsystems;

import org.usfirst.frc.team3218.robot.Robot;
import org.usfirst.frc.team3218.robot.RobotMap;
import org.usfirst.frc.team3218.robot.commands.Lift.ManualLiftControl;
import org.usfirst.frc.team3218.robot.commands.Lift.SetLiftPositionBottom;

import com.ctre.CANTalon;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Lift extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	//these values need testing
	int CruiseVelocity; //encoderticks per 100ms
	int Acceleration; //encoderticks per 100ms per second
	final int HOLD_POSITION_POWER = 0; //power required for arm to stay at position
	final int GUITAR_MANUAL_UP = 0;
	final int GUITAR_MANUAL_DOWN = 180;
	final int MANUAL_UP_POWER = 1;
	final int MANUAL_DOWN_POWER = -1;
	
	public float ticksPerInch;
	final int TICKS_PER_INCH = 100;
	
	public int[] positionArray = new int[]{0,0,0,0,0,0};//array of positions for the lift in inches
	
	
	public static Solenoid climbGear = new Solenoid(RobotMap.climbGearPort);
    public static Encoder liftEnc = new Encoder(RobotMap.liftEncoderPortA,RobotMap.liftEncoderPortB);

	
	public  WPI_TalonSRX lift1 = new WPI_TalonSRX(RobotMap.lift1ID);
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new ManualLiftControl());
    	
    }
    
    public void liftPIDConfig(){
    	//timeouts and PIDidx are 0

    	lift1.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
    	lift1.configMotionCruiseVelocity(CruiseVelocity, 0);
    	lift1.configMotionAcceleration(Acceleration, 0);
    	
    }
    
    public  void setPosition(int position){
    	
    		lift1.set(ControlMode.MotionMagic, Robot.lift.positionArray[position] * Robot.lift.TICKS_PER_INCH);
        
   
    }
    
    public void manual(){
    	//0 is up -1 is hold 180 is down
    	//Cim values need to be checked against the actual motor
    	
    	
		switch (Robot.oi.guitar.getPOV()) {

		case GUITAR_MANUAL_UP:
			lift1.set(MANUAL_UP_POWER);
			break;

		case GUITAR_MANUAL_DOWN:
			lift1.set(MANUAL_DOWN_POWER);
			break;

		default:
			lift1.set(HOLD_POSITION_POWER);
			break;
			
			
    	}
    
    }
   public void gearLow(){
	   climbGear.set(false);
   }
   public void gearHigh(){
	   climbGear.set(true);
   }
}

