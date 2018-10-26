package org.usfirst.frc.team3218.robot;

import org.usfirst.frc.team3218.robot.commands.Auto.AutoDrive;
import org.usfirst.frc.team3218.robot.commands.Auto.AutoEncodedOperators;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Command;

public class EazyBreezy_Auto {
	 
	
	 public final static int WALL_TO_SWITCH_CHANNEL = 67;
	 public final static int AUTOLINE = 120;
	 public final static int WALL_TO_SWITCH = 150;
	 public final static int WALL_TO_PLATFORM_CHANNEL = 210;
	 
	 public final static int MID_LINE = 240+5*12;
	 public final static int HORIZONTAL_FAR_SIDE = 162;
	 public final static int TICKS_PER_INCH = 72; 
	 
	 
	 public static void autoDriving(Command now) {
		 Command autoDrive = now;
	 	 autoDrive.start();
	 }
	 public static void autoLifting(int height, int distanceIn, double speed, WPI_TalonSRX motor, Encoder operator) {
		 //All measurements are in inches, which are then converted to encoder ticks inside of the commands.
		 
		 Command autoLift = new AutoEncodedOperators(height, speed, distanceIn, motor, operator);
		 autoLift.start();
	 }

	 public static void resetDriveTrain()
		{		
			Robot.driveTrain.rightMidDrive.setSelectedSensorPosition(0, 0, 0);
	 		Robot.driveTrain.leftMidDrive.setSelectedSensorPosition(0, 0, 0);
	 		Robot.driveTrain.leftEnc.reset();
	 		Robot.driveTrain.rightEnc.reset();
	 		Robot.driveTrain.gyro.reset();
		
		}
	 public static void fullResetDriveTrain()
		{		
			Robot.driveTrain.rightMidDrive.setSelectedSensorPosition(0, 0, 0);
	 		Robot.driveTrain.leftMidDrive.setSelectedSensorPosition(0, 0, 0);
	 		Robot.driveTrain.leftBottomDrive.set(0);
	 		Robot.driveTrain.leftMidDrive.set(0);
	 		Robot.driveTrain.leftMidDrive.set(0);
	 		Robot.driveTrain.rightBottomDrive.set(0);
	 		Robot.driveTrain.rightMidDrive.set(0);
	 		Robot.driveTrain.rightMidDrive.set(0);
	 		Robot.driveTrain.leftEnc.reset();
	 		Robot.driveTrain.rightEnc.reset();
	 		Robot.driveTrain.gyro.reset();
		
		}

	 public static double findLowerMotorPercentage(double bigEnc, double smallEnc, double bigSpeed) {
		
		 //old formula y= 68.308x-558.133
		 //new formula y= -7.6767x10^-4(x^3)+.0553x^2+61.917x-507.642
		 //new inverse y= 0.0168x + 6.3983
		 
		 double pRate = smallEnc/bigEnc;
		 double hypoBig = (-7.6767*Math.pow(10,-4)*Math.pow(bigSpeed,3)) + 0.553*Math.pow(bigSpeed,2)
		 +61.917*bigSpeed -507.642;
		 double hypoSmall = hypoBig*pRate;
		 System.out.println("hypo small: "+ hypoSmall);
		 System.out.println("Hypo big: "+ hypoBig);
		 return (0.0168*hypoSmall)+6.3983;
		 
		 
		 
	 }
}

