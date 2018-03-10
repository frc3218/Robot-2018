package org.usfirst.frc.team3218.robot;

import java.security.PublicKey;

import javax.print.attribute.standard.Compression;
import javax.print.attribute.standard.MediaSize.Other;

import org.omg.PortableServer.LIFESPAN_POLICY_ID;
import org.usfirst.frc.team3218.robot.commands.ExampleCommand;
import org.usfirst.frc.team3218.robot.commands.Auto.AutoGroup;
import org.usfirst.frc.team3218.robot.commands.Auto.CrossLine;
import org.usfirst.frc.team3218.robot.commands.Auto.Nothing;
import org.usfirst.frc.team3218.robot.commands.Auto.Scale;
import org.usfirst.frc.team3218.robot.commands.Auto.Switch;
import org.usfirst.frc.team3218.robot.commands.Auto.SwitchScale;
import org.usfirst.frc.team3218.robot.commands.DriveTrain.DriveWithXbox;
import org.usfirst.frc.team3218.robot.commands.Lift.ManualLiftControl;
import org.usfirst.frc.team3218.robot.subsystems.CubeControl;
import org.usfirst.frc.team3218.robot.subsystems.DriveTrain;
import org.usfirst.frc.team3218.robot.subsystems.ExampleSubsystem;
import org.usfirst.frc.team3218.robot.subsystems.Lift;
import org.usfirst.frc.team3218.robot.subsystems.Vision;
import org.w3c.dom.events.EventException;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static final DriveTrain driveTrain = new DriveTrain();
	public static final ExampleSubsystem exampleSubsystem = new ExampleSubsystem();
	public static final Vision vision = new Vision();
	public static final Lift lift =  new Lift();
	public static final CubeControl cubeControl = new CubeControl();
	public static OI oi;
	
	PowerDistributionPanel pdp = new PowerDistributionPanel(0);
	public static Command autonomousCommand;
	//game data returns capital combinations of L or R from that teams perspective
	public static String gameData;
	
	public static SendableChooser<String> position = new SendableChooser<>();
	public static SendableChooser<String> objective = new SendableChooser<>();
	public static SendableChooser<String> path = new SendableChooser<>();
	//public static CameraServer cameraServer;
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		oi = new OI();
		try{
			CameraServer.getInstance().startAutomaticCapture("Camera",RobotMap.cameraPort);
			
		}
		catch(Exception e){
			
		}
		
		driveTrain.compressor.clearAllPCMStickyFaults();
		driveTrain.leftBottomDrive.setInverted(true);
		driveTrain.leftMidDrive.setInverted(true);
		driveTrain.leftTopDrive.setInverted(true);
		driveTrain.rightBottomDrive.setInverted(true);
		driveTrain.rightMidDrive.setInverted(true);
		driveTrain.rightTopDrive.setInverted(true);
		
		position.addDefault("1", "1");
		position.addObject("2", "2");
		position.addObject("3", "3");
		
		objective.addDefault("Nothing", "Nothing");
		objective.addObject("Line", "Line");
		objective.addObject("Switch", "Switch");
		objective.addObject("Scale", "Scale");
		objective.addObject("SwitchScale", "SwitchScale");
		
		path.addDefault("Close", "close");
		path.addObject("Far", "far");
		
		
		SmartDashboard.putData("position",position);
		SmartDashboard.putData("objective",objective);
		SmartDashboard.putData("path",path);
		
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {
		lift.gearHigh();
		AutoAPI.breakAuto = true;
		
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
	
		driveTrain.gyro.reset();
		lift.liftPIDConfig();
		driveTrain.drivePIDConfig();
		pdp.clearStickyFaults();
		AutoAPI.breakAuto = false;
		//autonomousCommand = chooser.getSelected();
		
		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */
		gameData = DriverStation.getInstance().getGameSpecificMessage();
			switch(objective.getSelected()){
			case "Nothing": autonomousCommand = new Nothing(); break; 
			case "Line": autonomousCommand = new CrossLine(); break;
			case "Switch": autonomousCommand = new Switch(); break;
			case "Scale": autonomousCommand = new Scale(); break;
			case "SwitchScale": autonomousCommand = new AutoGroup(); break;
			}
		
			
		
		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		SmartDashboard.putData(driveTrain);
		SmartDashboard.putNumber("timer", Timer.getFPGATimestamp());
		SmartDashboard.putNumber("left Drivetrain Power", Robot.driveTrain.leftMidDrive.getMotorOutputPercent());
		SmartDashboard.putNumber("right Drivetrain Power", Robot.driveTrain.rightMidDrive.getMotorOutputPercent());
		SmartDashboard.putNumber("Left Encoder", driveTrain.leftMidDrive.getSelectedSensorPosition(0));
		SmartDashboard.putNumber("Right Encoder", driveTrain.rightMidDrive.getSelectedSensorPosition(0));
	
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		AutoAPI.breakAuto = true;
		new CrossLine().cancel();
		new Nothing().cancel();
		new AutoGroup().cancel();
		new Scale().cancel();
		new Switch().cancel();
		new SwitchScale().cancel();
		new DriveWithXbox().start();
		new ManualLiftControl().start();
		driveTrain.compressor.clearAllPCMStickyFaults();
		if (autonomousCommand != null)
			autonomousCommand.cancel();
		
		driveTrain.gyro.reset();
		lift.liftPIDConfig();
		driveTrain.drivePIDConfig();
		pdp.clearStickyFaults();
		lift.gearHigh();
		SmartDashboard.putData("position",position);
		SmartDashboard.putData("objective",objective);
		SmartDashboard.putData("path",path);
	}
	
	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		
	driveTrain.compressorControl();
		
	if(lift.bottomSwitch.get()){
		lift.liftEnc.reset();
	}
	
	lift.liftMaster.setSelectedSensorPosition(lift.liftEnc.get(), 0, 0);
	driveTrain.rightMidDrive.setSelectedSensorPosition( driveTrain.rightEnc.get(), 0, 0);
	driveTrain.leftMidDrive.setSelectedSensorPosition( driveTrain.leftEnc.get(), 0, 0);
			Scheduler.getInstance().run();
			SmartDashboard.putData(driveTrain);
		SmartDashboard.putNumber("AngleAverage",driveTrain.gyro.getAngle());
		//SmartDashboard.putNumber("Sonar Average", driveTrain.sonarA.getAverageVoltage());
		//SmartDashboard.putNumber("Sonar B Average", driveTrain.sonarB.getAverageVoltage());
		SmartDashboard.putNumber("Left Encoder", driveTrain.leftMidDrive.getSelectedSensorPosition(0));
		SmartDashboard.putNumber("Right Encoder", driveTrain.rightMidDrive.getSelectedSensorPosition(0));
		SmartDashboard.putNumber("Lift Encoder", lift.liftEnc.get());
		
		SmartDashboard.putNumber("Right Encoder rate",driveTrain.rightEnc.getRate());
		SmartDashboard.putNumber("left encoder rate", driveTrain.leftEnc.getRate());
		SmartDashboard.putNumber("Lift Encoder rate", lift.liftEnc.getRate());

		SmartDashboard.putNumber("lift power", lift.liftMaster.getMotorOutputPercent());
		SmartDashboard.putBoolean("Bottom Limit Switch", lift.bottomSwitch.get());
	    SmartDashboard.putBoolean("Top Limit Switch", lift.topSwitch.get());
	    
	    SmartDashboard.putBoolean("Compressor Pressure Switch", driveTrain.compressor.getPressureSwitchValue());
	    SmartDashboard.putNumber("Xbox Y Left Joy", Robot.oi.getXboxControllerLeftY());
	    SmartDashboard.putNumber("Xbox Z Right Joy", Robot.oi.getXboxControllerLeftZ());
	    SmartDashboard.putNumber("Xbox Left Trigger Axis", Robot.oi.xbox.getTriggerAxis(Hand.kLeft));
	    SmartDashboard.putNumber("Xbox Left Trigger Axis", Robot.oi.xbox.getTriggerAxis(Hand.kRight));
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}
