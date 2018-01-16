package org.usfirst.frc.team4786.robot;


import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	//instantiate buttons, sensors, joysticks, and Xbox controllers here
	private Joystick leftDriveJoy;
    private Joystick rightDriveJoy;
    private final XboxController xbox;
	private static Button leftXboxTrigger;
	private static Button rightXboxTrigger;

    public OI(){
    	//Init the objects for all the buttons, sensors, joysticks, and Xbox controllers
    	//leftDriveJoy = new Joystick(0);
    	//rightDriveJoy = new Joystick(1);
    	
        xbox = new XboxController(0);
        
        //leftJoystickTrigger = new JoystickButton(leftDriveJoy, 1);
        //rightJoystickTrigger = new JoystickButton(rightDriveJoy, 1);
    }  
 
      //Tie our many buttons, sensors, joysticks, and Xbox controllers to robot commands
/*
    public Joystick getLeftDriveJoy() {
	    return leftDriveJoy;
	}
	public Joystick getRightDriveJoy() {
	    return rightDriveJoy;
	}
*/
    
    public XboxController getXbox() {
		return xbox;
	}
}