package org.usfirst.frc.team3218.robot;

import java.security.PublicKey;

import javax.print.attribute.standard.Compression;

import org.omg.PortableServer.LIFESPAN_POLICY_ID;
import org.usfirst.frc.team3218.robot.commands.ExampleCommand;
import org.usfirst.frc.team3218.robot.commands.Auto.CrossLine;

import org.usfirst.frc.team3218.robot.commands.Auto.DoubleSwitch;

import org.usfirst.frc.team3218.robot.commands.Auto.Nothing;
import org.usfirst.frc.team3218.robot.commands.Auto.Scale;
import org.usfirst.frc.team3218.robot.commands.Auto.Switch;
import org.usfirst.frc.team3218.robot.commands.CubeControl.CubeControlOff;
import org.usfirst.frc.team3218.robot.commands.CubeControl.CubeControlXbox;
import org.usfirst.frc.team3218.robot.commands.DriveTrain.DriveWithFile;
import org.usfirst.frc.team3218.robot.commands.DriveTrain.DriveWithXbox;
import org.usfirst.frc.team3218.robot.commands.DriveTrain.GearShiftLow;
import org.usfirst.frc.team3218.robot.commands.Lift.ManualLiftControl;
import org.usfirst.frc.team3218.robot.subsystems.CubeControl;
import org.usfirst.frc.team3218.robot.subsystems.DriveTrain;
import org.usfirst.frc.team3218.robot.subsystems.ExampleSubsystem;
import org.usfirst.frc.team3218.robot.subsystems.Lift;
import org.usfirst.frc.team3218.robot.subsystems.Vision;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.interfaces.Gyro;
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
	
	public static PowerDistributionPanel pdp = new PowerDistributionPanel(0);
	public  Compressor compressor = new Compressor(1);
	public static Command autonomousCommand;
	//game data returns capital combinations of L or R from that teams perspective
	public static String gameData;
	public static boolean breakAuto;
	CameraServer cameraServer;
	CameraServer cameraServer2;
	public static String autoFile;
	public static SendableChooser<String> position = new SendableChooser<>();
	public static SendableChooser<String> objective = new SendableChooser<>();
	public static SendableChooser<Boolean> Gear = new SendableChooser<>();
	public static SendableChooser<Boolean> Record = new SendableChooser<>();
	/**
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		oi = new OI();
		
		try{
	   		 cameraServer = CameraServer.getInstance(); 
	   		 cameraServer.startAutomaticCapture("USB Camera", RobotMap.cameraPort);
	   		 cameraServer2 = CameraServer.getInstance(); 
	   		 cameraServer2.startAutomaticCapture("USB Camera2", RobotMap.cameraPort2);
	   	}catch(Exception e){
	   	}	
	
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
		
		objective.addObject("DoubleScale", "DoubleScale");
		objective.addObject("DoubleSwitch", "DoubleSwitch");
		objective.addObject("TripleSwitch", "TripleSwitch");
		
		Gear.addDefault("Low", false);
		Gear.addObject("High", true);
		
		Record.addDefault("Dont Record", false);
		Record.addObject("Record", true);
		
		SmartDashboard.putData("position",position);
		SmartDashboard.putData("objective",objective);
		SmartDashboard.putData("Gear", Gear);
		SmartDashboard.putData("record", Record);
	}
	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {
		lift.gearHigh();
		compressor.clearAllPCMStickyFaults();
		AutoAPI.resetDriveTrain();
		breakAuto = true;
		
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		breakAuto = true;
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
		lift.gearHigh();
		
		
		
		//autonomousCommand = chooser.getSelected();
		
		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */
		
		gameData = DriverStation.getInstance().getGameSpecificMessage();
			autoFile=position.getSelected()+objective.getSelected()+gameData.substring(0,1);
			switch(objective.getSelected()){
			case "Nothing": autonomousCommand = new Nothing(); break; 
			case "Line": autonomousCommand = new CrossLine(); break;
			case "Switch": autonomousCommand = new Switch(); break;
			case "DoubleSwitch": autonomousCommand = new DriveWithFile(); break;
			case "DoubleScale": autonomousCommand = new DriveWithFile(); break;
			case "TripleSwitch": autonomousCommand = new DriveWithFile();break;
			case "TripleScale" : autonomousCommand = new DriveWithFile();break;
			case "Scale": autonomousCommand = new Scale(); break;
			
			}
			
		if (autonomousCommand != null)
			autonomousCommand.start();
		SmartDashboard.putString("autoString",  autoFile);
	
	}
	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		SmartDashboard.putData(driveTrain);
		SmartDashboard.putNumber("Angle",driveTrain.gyro.getAngle());
		SmartDashboard.putNumber("left Drivetrain Power", Robot.driveTrain.leftMidDrive.getMotorOutputPercent());
		SmartDashboard.putNumber("right Drivetrain Power", Robot.driveTrain.rightMidDrive.getMotorOutputPercent());
		SmartDashboard.putNumber("Left Encoder", driveTrain.leftMidDrive.getSelectedSensorPosition(0));
		SmartDashboard.putNumber("Right Encoder", driveTrain.rightMidDrive.getSelectedSensorPosition(0));
		SmartDashboard.putNumber("Lift Encoder", lift.liftMaster.getSelectedSensorPosition(0));
	
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
		new DoubleSwitch().cancel();
		new Scale().cancel();
		new Switch().cancel();		
		new DriveWithXbox().start();
		new ManualLiftControl().start();
		new CubeControlXbox().start();
		if (autonomousCommand != null)
			autonomousCommand.cancel();
		
		driveTrain.gyro.reset();
		
		lift.liftPIDConfig();
		pdp.clearStickyFaults();
		driveTrain.drivePIDConfig();
		
		lift.gearHigh();
		SmartDashboard.putData("position",position);
		SmartDashboard.putData("objective",objective);
		//SmartDashboard.putData("path",path);
	}
	
	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		autoFile=position.getSelected()+objective.getSelected()+gameData.substring(0,1);
	if(lift.bottomSwitch.get()){
		lift.liftEnc.reset();
	}
	AutoAPI.breakAuto = true;

	lift.liftMaster.setSelectedSensorPosition(lift.liftEnc.get(), 0, 0);
	driveTrain.rightMidDrive.setSelectedSensorPosition( driveTrain.rightEnc.get(), 0, 0);
	driveTrain.leftMidDrive.setSelectedSensorPosition( driveTrain.leftEnc.get(), 0, 0);
			Scheduler.getInstance().run();
			SmartDashboard.putData(driveTrain);
		SmartDashboard.putNumber("Angle",driveTrain.gyro.getAngle());
		SmartDashboard.putNumber("Left Encoder", driveTrain.leftMidDrive.getSelectedSensorPosition(0));
		SmartDashboard.putNumber("Right Encoder", driveTrain.rightMidDrive.getSelectedSensorPosition(0));
		SmartDashboard.putNumber("Lift Encoder", lift.liftMaster.getSelectedSensorPosition(0));
		
		SmartDashboard.putNumber("Right Encoder rate",driveTrain.rightEnc.getRate());
		SmartDashboard.putNumber("left encoder rate", driveTrain.leftEnc.getRate());
		SmartDashboard.putNumber("Lift Encoder rate", lift.liftEnc.getRate());

		SmartDashboard.putBoolean("Bottom Limit Switch", lift.bottomSwitch.get());
	    SmartDashboard.putBoolean("Top Limit Switch", lift.topSwitch.get());
	    
	    SmartDashboard.putBoolean("Compressor Pressure Switch", compressor.getPressureSwitchValue());
	    SmartDashboard.putNumber("Right stick y", oi.getXboxControllerRightY());
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}
