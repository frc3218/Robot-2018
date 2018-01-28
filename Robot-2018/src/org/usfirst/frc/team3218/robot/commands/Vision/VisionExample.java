package org.usfirst.frc.team3218.robot.commands.Vision;

import org.usfirst.frc.team3218.robot.Robot;
import org.usfirst.frc.team3218.robot.subsystems.Blob;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class VisionExample {
	//find if an object is in the center of the screen
	boolean WithinThreshold = true;
	public static final float INNER_THRESHOLD = 25;
	public static final float OUTER_THRESHOLD = 35;
	public static final int SCREEN_WIDTH = 240;
	Pixy pixy = new Pixy();
	public VisionExample()
	{
		Blob blob = pixy.blobArray[0];
		/*SmartDashboard.putNumber("Pixy X:", blob.averageX);
		SmartDashboard.putNumber("Pixy Y:", blob.averageY);
		SmartDashboard.putNumber("Pixy Width:", blob.averageWidth);
		SmartDashboard.putNumber("Pixy Height:", blob.averageHeight);*/
		if(WithinThreshold)
		{
			if(Math.abs(pixy.currentX - SCREEN_WIDTH/2)<OUTER_THRESHOLD)
			{
				WithinThreshold = false;
				LookTowards();
			}
		} else {
			if(Math.abs(pixy.currentX - SCREEN_WIDTH/2)<INNER_THRESHOLD)
			{
				WithinThreshold = true;
			} else {
				LookTowards();
			}
		}
	}
	private void LookTowards()
	{
		Robot.driveTrain.drive(0, (pixy.currentX-SCREEN_WIDTH/2<0) ? -1 : 1);
	}
}
