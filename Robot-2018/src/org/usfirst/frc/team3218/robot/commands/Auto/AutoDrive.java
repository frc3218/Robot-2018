package org.usfirst.frc.team3218.robot.commands.Auto;

import org.usfirst.frc.team3218.robot.AutoAPI;
import org.usfirst.frc.team3218.robot.EazyBreezy_Auto;
import org.usfirst.frc.team3218.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoDrive extends Command {
  //distance in inches
	double distance,
  // degrees needed for arc 
	degrees;
  // motor speed (from 0 to 1)
    double speed;
  //left VS right (turning while moving)
    String arc, 
  //low VS high gear
    gear;
  //ending variable (set to true when encoder 
    boolean end = false;
  //if there is a sequential command set to true
    boolean sequentialCommand = false;
  // Sequential command 
    Command sequence;
  //tells whether this command comes after another one
    boolean isSequential = false;
  // The encoder distance the left side will follow
    double leftEncTarget;
  // The encoder distance the right side will follow
    double rightEncTarget;
  // The bigger radius encoder distance
    double biggerEncoderDistance;
  //The smaller radius encoder distance
    double smallerEncoderDistance;
  // The amount of arcs per full circumference (used for finding radius)
    double arcsPerFull;
  // The bigger radius, in inches
    double biggerRadius;
  //The smaller radius, in inches
    double smallerRadius;
  // The gap between the wheels, used to calculate the secondary radius from the first (measured in inches)
    final double wheelGap = 22.5;
  // speeds for each side
    double leftSpeed, rightSpeed;
  // used for calculating the distance in inches that the robot moved on the x and y axes.
    double radianMeasure;
  // The higher efficiency rate
    double primaryRate; 
  // the lower efficiency rate
    double secondaryRate;
  // percentage to be used to equalize the efficiency
    double efficiencyDifference;
    double smallSpeed;
    boolean leftFinished = false,rightFinished = false;
    public AutoDrive(double distance, double speed, double degrees, String arc, String gear, boolean sequentialCommand, Command sequence, boolean isSequential) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    	
    	try {
    	//setting variables to the asked situation
    	this.distance = distance;
    	this.speed = speed;
    	this.degrees = degrees;
    	this.arc = arc;
    	this.gear = gear;
    	this.sequentialCommand = sequentialCommand;
    	this.sequence = sequence;
    	this.isSequential = isSequential;
    	this.arcsPerFull = 360/degrees;
    	this.biggerRadius = (distance*arcsPerFull)/(2*3.14156);
    	this.smallerRadius = biggerRadius-wheelGap;
    	this.smallerEncoderDistance = (double) (smallerRadius*2*Math.PI)*(degrees/360) *EazyBreezy_Auto.TICKS_PER_INCH;
    	this.biggerEncoderDistance = distance*EazyBreezy_Auto.TICKS_PER_INCH;
    	this.radianMeasure = degrees/57.296;
    	
    	}
    	catch(NullPointerException e){
    		e.printStackTrace();
    	}
    	
    	}
    	
    // Called just before this Command runs the first time
   
    protected void initialize() {
   	
    //Full reset sets motor speed to zero, the other does not. If it is a sequential, you would want it to
    // not reset the motors so that it coasts to it.
  
    	if(isSequential)
    	EazyBreezy_Auto.resetDriveTrain();
    else
    	EazyBreezy_Auto.fullResetDriveTrain();
    //
   
    //Setting the distances and speed dependent on which side it is arcing. 
    switch(arc) {
    case "left": leftEncTarget = smallerEncoderDistance; rightEncTarget = biggerEncoderDistance;
    	rightSpeed = speed; leftSpeed = EazyBreezy_Auto.findLowerMotorPercentage(rightEncTarget,leftEncTarget,speed);
    break;
    case "right": rightEncTarget = smallerEncoderDistance; leftEncTarget = biggerEncoderDistance;
    	leftSpeed = speed; rightSpeed = EazyBreezy_Auto.findLowerMotorPercentage(leftEncTarget, rightEncTarget, speed);
    break;
    default: rightSpeed = speed; leftSpeed=speed;
    }
    
    //Switching depending on gear requested
   
    switch(gear) {
    case "low" : Robot.driveTrain.lowGear();
    break;
    case "high" : Robot.driveTrain.highGear();
    break;
    default: Robot.driveTrain.lowGear();
    System.out.println("initalized it all");
    }
    //Calculating the efficiency difference
    
    
    leftSpeed = Math.signum(leftEncTarget)*leftSpeed;
    rightSpeed = Math.signum(rightEncTarget)*rightSpeed;
    System.out.println("two pi" +(2*Math.PI));
    System.out.println("per full" + arcsPerFull);
    System.out.println("big rad" +biggerRadius);
    System.out.println("small rad" +smallerRadius);
    System.out.println("big arc" +biggerEncoderDistance);
    System.out.println("small arc" + smallerEncoderDistance);
    System.out.println("left sped" +leftSpeed);
    System.out.println("right sped" +rightSpeed);
    System.out.println("right target" +rightEncTarget);
    System.out.println("left target" + leftEncTarget);
    System.out.println("Signum ofleftEncTarget is: "+ Math.signum(leftEncTarget));
    }
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    //The driving command call
    // If both encoders hit their mark or if a button is pushed that cancels autonomous (if in Tele-op and is stuck)
    if(Math.signum(leftEncTarget)==1) {
    /*
    if(Robot.driveTrain.rightEnc.get()>rightEncTarget)
    	rightFinished = true;
    if(Robot.driveTrain.leftEnc.get()>leftEncTarget)
    	leftFinished = true;
    if(rightFinished&&leftFinished)
    	end = true;
    else if(!rightFinished&&leftFinished)
    	Robot.driveTrain.autoDrive(0,rightSpeed);
    else if(!leftFinished&&rightFinished)
    	Robot.driveTrain.autoDrive(leftSpeed,0);
    else if(!leftFinished&&!rightFinished)
    	Robot.driveTrain.autoDrive(leftSpeed,rightSpeed);
    */  
      if(Robot.driveTrain.rightEnc.get()<rightEncTarget&&Robot.driveTrain.leftEnc.get()<leftEncTarget){
      Robot.driveTrain.autoDrive(leftSpeed,rightSpeed);
      }
      else {
      end = true;
      System.out.println("Signum was ==1");
      System.out.println("Gyro at end: "+ Robot.driveTrain.gyro.getAngle());
      System.out.println("Left enc at end :"+ Robot.driveTrain.leftEnc.get());
      System.out.println("Right enc at end :"+ Robot.driveTrain.rightEnc.get());
      }
    }
    
    else {
        if(Robot.driveTrain.rightEnc.get()>rightEncTarget&&Robot.driveTrain.leftEnc.get()>leftEncTarget) 
        	Robot.driveTrain.autoDrive(leftSpeed, rightSpeed);
        
        else {
        	System.out.println("Gyro at end: "+ Robot.driveTrain.gyro.getAngle());
            System.out.println("Left enc at end :"+ Robot.driveTrain.leftEnc.get());
            System.out.println("Right enc at end :"+ Robot.driveTrain.rightEnc.get());
        	end = true;
        }
    }
    
    }
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if(end) {
        	return true;
        }
        else {
        	return false;
        }
    }

    // Called once after isFinished returns true
    protected void end() {
    	
    	if(sequentialCommand) 
    		sequence.start();
    	
    	else
    		EazyBreezy_Auto.fullResetDriveTrain();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
   
    }

}
