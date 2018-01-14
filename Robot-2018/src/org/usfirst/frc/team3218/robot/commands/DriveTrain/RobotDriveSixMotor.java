package org.usfirst.frc.team3218.robot.commands.DriveTrain;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.TalonSRX;

public class RobotDriveSixMotor extends RobotDrive
{
	protected SpeedController m_centerRightMotor;
	protected SpeedController m_centerLeftMotor;
	
	public RobotDriveSixMotor(final int frontLeftMotor, final int rearLeftMotor, final int centerLeftMotor, final int centerRightMotor, final int frontRightMotor, final int rearRightMotor)
	{
		super(frontLeftMotor, rearLeftMotor, frontRightMotor, rearRightMotor);
		
		m_centerRightMotor = new Talon(centerRightMotor);
		m_centerLeftMotor = new Talon(centerLeftMotor);
	}
	
}
