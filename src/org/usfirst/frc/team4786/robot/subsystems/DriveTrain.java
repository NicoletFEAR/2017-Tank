package org.usfirst.frc.team4786.robot.subsystems;

import org.usfirst.frc.team4786.robot.Robot;
import org.usfirst.frc.team4786.robot.RobotMap;
import org.usfirst.frc.team4786.robot.commands.OpenLoopDrive;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveTrain extends Subsystem {

	//CANTalon objects, there is a reason why they are private

	TalonSRX frontLeft = new TalonSRX(RobotMap.frontLeftPort);
	TalonSRX frontRight = new TalonSRX(RobotMap.frontRightPort);
//	private TalonSRX backLeft = new TalonSRX(RobotMap.backLeftPort);
//	private TalonSRX backRight = new TalonSRX(RobotMap.backRightPort);
	
	//Robot will drive as if the front side is the back when reversed is true
	private boolean reversed;

	//Get both motor outputs
	public double motorOutputLeft = frontLeft.getMotorOutputVoltage();
	public double motorOutputRight = frontRight.getMotorOutputVoltage();
	
	public DriveTrain(){
		//Enable the Talons!
		frontLeft.set(ControlMode.PercentOutput, 0);
		frontRight.set(ControlMode.PercentOutput, 0);
	}
	
	@Override
	 public void initDefaultCommand() {
    	// Set the default command for a subsystem here.
        setDefaultCommand(new OpenLoopDrive());
    }
	
	public void brake(){
		//zero is to brake
    	frontRight.set(ControlMode.Current, 0);
    	frontLeft.set(ControlMode.Current, 0);
    }
	
	//Drive Command for Open Loop System;
	//Should be obsolete once PID is Implemented
	public void openLoopDrive(double leftInput, double rightInput) {
		//Change Talon modes to PercentVbus

		double leftOutput = leftInput * RobotMap.openLoopSpeedScaling;
		double rightOutput = rightInput * RobotMap.openLoopSpeedScaling;
		frontLeft.set(ControlMode.PercentOutput, leftOutput);
		frontRight.set(ControlMode.PercentOutput, rightOutput);
			
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
	
	public TalonSRX getLeftTalon(){
		return frontLeft;
	}
	
	public TalonSRX getRightTalon(){
		return frontRight;
	}

}
