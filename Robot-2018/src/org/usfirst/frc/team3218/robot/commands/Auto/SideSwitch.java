package org.usfirst.frc.team3218.robot.commands.Auto;

import javax.swing.text.html.FormSubmitEvent;

import org.usfirst.frc.team3218.robot.AutoAPI;
import org.usfirst.frc.team3218.robot.Robot;
import org.usfirst.frc.team3218.robot.RobotMap;
import org.usfirst.frc.team3218.robot.commands.CubeControl.CubeControlOff;
import org.usfirst.frc.team3218.robot.commands.CubeControl.CubeControlXbox;
import org.usfirst.frc.team3218.robot.commands.CubeControl.CubeEjectionOn;
import org.usfirst.frc.team3218.robot.subsystems.Lift;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SideSwitch extends Command {

    public SideSwitch() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    	requires(Robot.cubeControl);
    	requires(Robot.lift);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    
    	String sendableChosenString = Robot.position.getSelected() + Robot.gameData.substring(0,1);
    			switch(sendableChosenString){
    			case "1L": 
    				AutoAPI.driveStraight(AutoAPI.WALL_TO_SWITCH, 2000, 250);
    				Robot.cubeControl.beltOn(1);
    				Timer.delay(1);
    				Robot.cubeControl.cubeOff();
    			break;
    			case "1R": 
    				AutoAPI.driveStraight(AutoAPI.WALL_TO_PLATFORM_CHANNEL-8, 2000, 250);
    				AutoAPI.rotate(90, 300, 300);
    				AutoAPI.driveStraight(AutoAPI.HORIZONTAL_FAR_SIDE,2000,250);//drive across field
    				Robot.cubeControl.beltOn(1);
    				Timer.delay(1);
    				Robot.cubeControl.cubeOff();
    			break;
    			case "3R":
    				AutoAPI.driveStraight(AutoAPI.WALL_TO_SWITCH, 2000, 250);
    				Robot.cubeControl.beltOn(-1);
    				Timer.delay(1);
    				Robot.cubeControl.cubeOff();
    			case "3L":
    				AutoAPI.driveStraight(AutoAPI.WALL_TO_PLATFORM_CHANNEL-8, 2000, 250);
    				AutoAPI.rotate(-90, 300, 300);
    				AutoAPI.driveStraight(AutoAPI.HORIZONTAL_FAR_SIDE,2000,250);//drive across field
    				Robot.cubeControl.beltOn(-1);
    				Timer.delay(1);
    				Robot.cubeControl.cubeOff();
    			break;
    			}
    	
    	
    
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.lift.gearLow();  
    	Robot.lift.liftMaster.set(0);
    	Robot.lift.lift2.set(0);
    }
    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
