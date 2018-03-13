package org.usfirst.frc.team3218.robot.subsystems;

import org.usfirst.frc.team3218.robot.Robot;
import org.usfirst.frc.team3218.robot.RobotMap;
import org.usfirst.frc.team3218.robot.commands.Lift.ManualLiftControl;
import org.usfirst.frc.team3218.robot.commands.Lift.SetLiftPositionBottom;

import com.ctre.CANTalon;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.RemoteSensorSource;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Lift extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	//these values need testing
	private int CruiseVelocity=1760; //encoderticks per 100ms
	private int upAcceleration=1760;
	private int downAcceleration=200;
	//encoderticks per 100ms per second
	private double HOLD_POSITION_POWER = .1; //power required for arm to stay at position
	private static final int GUITAR_MANUAL_UP = 0;
	private static final int GUITAR_MANUAL_DOWN = 180;
	
	private static final double MANUAL_UP_POWER = .5;
	private double MANUAL_DOWN_POWER = -0.1;
	private static final double TICKS_PER_INCH = 5000/37;
	private static final int MAX_TICK_HEIGHT = 5300;
	public int[] positionArray = new int[]{0,700,2000,3500,4400,5100};//array of positions for the lift in ticks 0 index is empty
	
	
	public static Solenoid highLiftGear = new Solenoid(1,RobotMap.climbGearPort);
	
	public  WPI_TalonSRX liftMaster = new WPI_TalonSRX(RobotMap.lift1ID);
	public  WPI_TalonSRX lift2 = new WPI_TalonSRX(RobotMap.lift2ID);
	public static Encoder liftEnc = new Encoder(RobotMap.liftEncoderPort1, RobotMap.liftEncoderPort2);
	public static DigitalInput bottomSwitch = new DigitalInput(RobotMap.bottomLiftSwitchPort);
	public static DigitalInput topSwitch = new DigitalInput(RobotMap.topLiftSwitchPort);
	public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new ManualLiftControl());
    	
    }
    
    public void liftPIDConfig(){
    	//timeouts and PIDidx are 0
    	gearHigh();
    	liftMaster.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
    	liftMaster.configMotionCruiseVelocity(CruiseVelocity, 0);
    	liftMaster.configMotionAcceleration(upAcceleration, 0);
    	liftMaster.setInverted(false);
    	lift2.setInverted(true);
    	liftMaster.selectProfileSlot(0, 0);
    	liftMaster.config_kF(0, 1, 0);
    	liftMaster.config_kP(0, 0, 0);
    	liftMaster.config_kI(0, 0, 0);
    	liftMaster.config_kD(0, 0, 0);
    	
    }
    
    public  void setPosition(int position){
    	if(Robot.lift.bottomSwitch.get()){
			Robot.lift.liftEnc.reset();
		}
    		Robot.lift.liftMaster.setSelectedSensorPosition(liftEnc.get(),0,0);
    		liftMaster.set(ControlMode.MotionMagic, position);
    		lift2.set(ControlMode.Follower, RobotMap.lift1ID);
    		liftMaster.configMotionAcceleration(position < liftEnc.get()? downAcceleration:upAcceleration, 0);
    		
    		
    }
    
   
    public void manual(){
    	//0 is up -1 is hold 180 is down
    	//Cim values need to be checked against the actual motor


    
		switch (Robot.oi.guitar.getPOV()) {

		case GUITAR_MANUAL_UP:
			if(liftEnc.get() < MAX_TICK_HEIGHT && !topSwitch.get()){
			liftMaster.set(ControlMode.PercentOutput,MANUAL_UP_POWER);
			
			lift2.set(ControlMode.PercentOutput,MANUAL_UP_POWER);
			}else{
				liftMaster.set(ControlMode.PercentOutput,HOLD_POSITION_POWER);
				lift2.set(ControlMode.PercentOutput,HOLD_POSITION_POWER);
			}
			break;

		case GUITAR_MANUAL_DOWN:
	
		if(!bottomSwitch.get()){
			liftMaster.set(ControlMode.PercentOutput,MANUAL_DOWN_POWER);
			lift2.set(ControlMode.PercentOutput,MANUAL_DOWN_POWER);
			if(liftEnc.get()<1000){
				liftMaster.set(ControlMode.PercentOutput,MANUAL_DOWN_POWER/2);
				lift2.set(ControlMode.PercentOutput,  MANUAL_DOWN_POWER/2);	
			}
		}else{
			liftMaster.set(ControlMode.PercentOutput, 0);
			lift2.set(ControlMode.PercentOutput, 0);
		}
			break;

		default:
			liftMaster.set(ControlMode.PercentOutput,HOLD_POSITION_POWER);
			lift2.set(ControlMode.PercentOutput,HOLD_POSITION_POWER);
			break;
			
    	}
		
 
    }
   public void gearLow(){
	   highLiftGear.set(true);
   }
   public void gearHigh(){
	   highLiftGear.set(false);
   }

}

