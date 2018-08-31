package org.usfirst.frc.team3218.robot.commands.Auto;

import org.usfirst.frc.team3218.robot.Robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoEncodedOperators extends Command {
	int height,distanceIn;
    int encoderTicksIn;
    double speed;
	WPI_TalonSRX motor;
    int distanceSignum;
	Encoder primaryEncoder;
    Encoder operatorEncoder;
	//Determines which way the DistanceIn encoders are read (if robot is going forward or backwards), 
    //and also if the height is going to go up or down.
    int encoderSignum,
	heightSignum; 
    boolean stop = false;
    public AutoEncodedOperators(int height, double speed, int distanceIn, WPI_TalonSRX motor, Encoder operator) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.lift);
    	this.height = height;
    	this.speed = speed;
    	this.distanceIn = distanceIn;
    	this.motor = motor;
    	this.encoderTicksIn = distanceIn*72;
    	this.operatorEncoder = operator;
	}

    // Called just before this Command runs the first time
    protected void initialize() {
   requires(Robot.lift);
    if(distanceIn<0) distanceSignum = -1;
    else if(distanceIn==0) distanceSignum = 0;
    else if(distanceIn>0) distanceSignum = 1;
   
    if(height<operatorEncoder.get()) 
    	heightSignum = -1;
    else 
    	heightSignum = 1;
    //Determining which encoder to use based off of which one is either more negative or positive
    // considering which direction the AutoEncodedOperator is tracking
    
    switch(distanceSignum) {
		case -1: if (Robot.driveTrain.rightEnc.get()<Robot.driveTrain.leftEnc.get()) primaryEncoder=Robot.driveTrain.rightEnc;
		else primaryEncoder=Robot.driveTrain.leftEnc;
			break;
		case 0: primaryEncoder = Robot.driveTrain.leftEnc;
			break;
		case 1: if(Robot.driveTrain.rightEnc.get()>Robot.driveTrain.leftEnc.get()) primaryEncoder = Robot.driveTrain.rightEnc;
		else primaryEncoder = Robot.driveTrain.leftEnc;
			break;
    }
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
   //Determining whether the encoder tick area has been passed.
    if(operatorEncoder.get()>height*heightSignum)
    	stop = true;
    
    switch(distanceSignum) {
    	case -1: if(primaryEncoder.get()<encoderTicksIn&&operatorEncoder.get()<height) 
    		motor.set(heightSignum*speed);
    	break;
    	
    	case 0: if(operatorEncoder.get()<height) 
    		motor.set(heightSignum*speed);
    	break;
    	
    	case 1: if(primaryEncoder.get()>encoderTicksIn) 
    		if(operatorEncoder.get()<height)
    		motor.set(heightSignum*speed);
    		
    	break;
        
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
       if(stop)
    	   return true;
       else
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
