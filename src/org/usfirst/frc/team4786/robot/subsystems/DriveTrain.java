package org.usfirst.frc.team4786.robot.subsystems;

import org.usfirst.frc.team4786.robot.Robot;
import org.usfirst.frc.team4786.robot.RobotMap;
import org.usfirst.frc.team4786.robot.commands.OpenLoopDrive;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveTrain extends Subsystem {

	//CANTalon objects, there is a reason why they are private

	private CANTalon frontLeft = new CANTalon(RobotMap.frontLeftPort);
	private CANTalon frontRight = new CANTalon(RobotMap.frontRightPort);
	private CANTalon backLeft = new CANTalon(RobotMap.backLeftPort);
	private CANTalon backRight = new CANTalon(RobotMap.backRightPort);
	
	//Robot will drive as if the front side is the back when reversed is true
	private boolean reversed;
	
	//Get both motor outputs
	public double motorOutputLeft = frontLeft.getOutputVoltage() / frontLeft.getBusVoltage();
	public double motorOutputRight = frontRight.getOutputVoltage() / frontRight.getBusVoltage();
	
	public DriveTrain(){
		//Enable the Talons!
		frontLeft.enable();
		frontRight.enable();
		
		backLeft.changeControlMode(CANTalon.TalonControlMode.Follower);
		backRight.changeControlMode(CANTalon.TalonControlMode.Follower);
		backLeft.set(RobotMap.frontLeftPort);
		backRight.set(RobotMap.frontRightPort);
		frontLeft.setInverted(true);
	}
	
	@Override
	 public void initDefaultCommand() {
    	// Set the default command for a subsystem here.
        setDefaultCommand(new OpenLoopDrive());
    }
	
	public void brake(){
		//zero is to brake
    	frontRight.set(0);
    	frontLeft.set(0);
    }
	
	//Drive Command for Open Loop System;
	//Should be obsolete once PID is Implemented
	public void openLoopDrive(double leftInput, double rightInput) {
		//Change Talon modes to PercentVbus
		frontLeft.changeControlMode(TalonControlMode.PercentVbus);
		frontRight.changeControlMode(TalonControlMode.PercentVbus);

		double leftOutput = leftInput * RobotMap.openLoopSpeedScaling;
		double rightOutput = rightInput * RobotMap.openLoopSpeedScaling;
		frontLeft.set(leftOutput);
		frontRight.set(rightOutput);
			
		//Smartdashboard values
		SmartDashboard.putString("Front Side:", Robot.frontSide);

	}
	
	//Move Front Side Switcher Related Code
	public boolean isReversed(){
		return reversed;
	}
	
	public void switchFront(){
		frontLeft.setInverted(!frontLeft.getInverted());
		frontRight.setInverted(!frontRight.getInverted());
		reversed = !reversed;
	}

}
