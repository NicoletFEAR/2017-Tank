package org.usfirst.frc.team4786.robot.commands;

import org.usfirst.frc.team4786.robot.Robot;
import org.usfirst.frc.team4786.robot.RobotMap;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class OpenLoopDrive extends Command {

	public OpenLoopDrive() {
		// Use requires() here to declare subsystem dependencies
	    // eg. requires(chassis);
	    	
	    requires(Robot.driveTrain);
	}
	
    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//gets input from controller and drives bot
    	//experimental videogame style driving
    	//triggers and all! >:D
    	double forwardSpeed = Robot.oi.getXbox().getTriggerAxis(Hand.kRight);
    	double reverseSpeed = Robot.oi.getXbox().getTriggerAxis(Hand.kLeft);
    	double turnAmount = Robot.oi.getXbox().getX(Hand.kLeft);
    	
    	double outputSpeed = forwardSpeed - reverseSpeed;
    	
       	double leftOutput = outputSpeed;
    	double rightOutput = outputSpeed;
    	
    	if (turnAmount >= 0)
    	{
    		leftOutput =+ turnAmount;
    	} else {
    		rightOutput =+ -turnAmount;
    	}

    	Robot.driveTrain.openLoopDrive(leftOutput , rightOutput);
    	
    	SmartDashboard.putNumber("Forward Speed:", forwardSpeed);
    	SmartDashboard.putNumber("Reverse Speed:", reverseSpeed);
    	SmartDashboard.putNumber("Turn:", turnAmount);
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
